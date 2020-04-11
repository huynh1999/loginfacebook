/*
	@author:Quang Truong
	@date: Jan 31, 2020
*/

package com.jwatgroupb.controller.web;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jwatgroupb.entity.BillEntity;
import com.jwatgroupb.entity.CartEntity;
import com.jwatgroupb.entity.PayerEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.service.BillServiceImpl;
import com.jwatgroupb.service.CartService;
import com.jwatgroupb.service.CheckoutService;
import com.jwatgroupb.service.EmailServiceImpl;
import com.jwatgroupb.service.PaypalService;
import com.jwatgroupb.service.UserService;
import com.jwatgroupb.util.SecurityUtils;
import com.jwatgroupb.util.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

@Controller
@SessionAttributes({ "user", "cart", "bill", "billCode" })
public class CheckOutController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CheckoutService checkoutService;

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private BillServiceImpl billService;

	@Autowired
	private PaypalService paypalService;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

	@RequestMapping(value = "/checkout/getquotes")
	public ModelAndView getQuotes() {
		ModelAndView mav = new ModelAndView();
		if (SecurityUtils.isAuthenticanted() == true) {
			String username = SecurityUtils.getPrincipal().getUsername();
			UserEntity user = userService.findByUsername(username);
			mav.addObject("user", user);
			mav.setViewName("redirect:/checkout");
		} else {
			mav.setViewName("redirect:/checkout?notLogin");
		}
		return mav;
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkoutPage(@CookieValue(value = "cart_code", required = false) String cartCode) {
		ModelAndView mav = new ModelAndView("web/checkout");
		CartEntity cart;
		if (SecurityUtils.isAuthenticanted() == true) {// Da dang nhap
			String username = SecurityUtils.getPrincipal().getUsername();
			cart = cartService.findOneByUserName(username);
		} else {
			cart = cartService.findOneByCartCode(cartCode);
		}
		if (cart == null) {
			cart = new CartEntity();
		}
		BillEntity bill = new BillEntity();
		mav.addObject("bill", bill);
		mav.addObject("cart", cart);
		return mav;
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ModelAndView pay(@ModelAttribute("bill") @Valid BillEntity bill, BindingResult result,
			@ModelAttribute("cart") CartEntity cart, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity;
		if (result.hasErrors()) {
			mav.setViewName("web/checkout");
			return mav;
		} else {
			if (cart.getListCartItem().isEmpty()) {
				mav.setViewName("redirect:/checkout?cartisempty");
				return mav;
			} else {
				if (bill.getPaymentMethod().equals("COD")) {
					if (SecurityUtils.isAuthenticanted() == true) {
						String username = SecurityUtils.getPrincipal().getUsername();
						userEntity = userService.findByUsername(username);
						bill.setUserEntity(userEntity);
					}
					String billCode = payment(bill, cart,null);
					mav.addObject("billCode", billCode);
					mav.setViewName("web/paymentsuccess");
					return mav;
				} else {
					if (SecurityUtils.isAuthenticanted() == true) {
						String username = SecurityUtils.getPrincipal().getUsername();
						userEntity = userService.findByUsername(username);
						bill.setUserEntity(userEntity);
					}
					String cancelUrl = Utils.getBaseURL(request) + "/" + CANCEL_URL;
					String successUrl = Utils.getBaseURL(request) + "/" + SUCCESS_URL;
					Double total = (double) bill.getTotalMoney();
					try {
						Payment payment = paypalService.createPayment(total, "USD", "paypal", "sale", "pay for purchase",
								cancelUrl, successUrl);
						for (Links links : payment.getLinks()) {
							if (links.getRel().equals("approval_url")) {
								mav.setViewName("redirect:" + links.getHref());
								return mav;
							}
						}
					} catch (PayPalRESTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mav.setViewName("redirect:/checkout");
				}
				return mav;
			}
		}
	}

	@RequestMapping(CANCEL_URL)
	public String cancelPay() {
		return "redirect:/checkout?paymentFail";
	}

	@RequestMapping(SUCCESS_URL)
	public ModelAndView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,
			@SessionAttribute("bill") BillEntity bill, @SessionAttribute("cart") CartEntity cart) {
		ModelAndView mav = new ModelAndView();
		
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				Payment paymentDetail = paypalService.getPaymentDetails(paymentId);
				PayerInfo payer = paymentDetail.getPayer().getPayerInfo();
				Transaction transaction = paymentDetail.getTransactions().get(0);
				PayerEntity payerEntity= new PayerEntity();
				payerEntity.setFirstName(payer.getFirstName());
				payerEntity.setFirstName(payer.getLastName());
				payerEntity.setFirstName(payer.getEmail());
				mav.addObject("payer", payer);
				mav.addObject("transaction", transaction);
				String billCode = payment(bill, cart,payerEntity);
				mav.addObject("billCode", billCode);
				mav.setViewName("web/paymentsuccess");
				return mav;
			}
		} catch (PayPalRESTException e) {
			e.getMessage();
		}
		mav.setViewName("redirect:/checkout");
		return mav;
	}

	//Show Invoice
	@RequestMapping(value = "/bill/{billCode}")
	public void showInvoice(@PathVariable("billCode") String billCode, HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSrc = new JRBeanCollectionDataSource(billService.report(billCode));
		InputStream inputStream = this.getClass().getResourceAsStream("/report/Invoice.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> parameters = billService.getParameter(billCode);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSrc);
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();
	}

	public String payment(BillEntity bill, CartEntity cart, PayerEntity payer) {
		String billCode = checkoutService.addBill(bill,payer);
		checkoutService.addBillDetail(cart, bill);
		cartService.clearItemInCart(cart);
		cart = new CartEntity();
		bill.setBillCode(billCode);
		emailService.sendMailConfirmPayment(bill);
		return billCode;
	}
}
