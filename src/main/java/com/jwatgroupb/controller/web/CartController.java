/*
	@author:Quang Truong
	@date: Jan 21, 2020
*/

package com.jwatgroupb.controller.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwatgroupb.entity.CartEntity;
import com.jwatgroupb.entity.CartItemEntity;
import com.jwatgroupb.service.CartService;
import com.jwatgroupb.util.RandomStringUtil;
import com.jwatgroupb.util.SecurityUtils;

@Controller

public class CartController {

	@Autowired
	private CartService cartService;
	
	

	@RequestMapping(value = "/cart/add/{productId}",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addProduct(@PathVariable("productId") Long productId,
			@CookieValue(value = "cart_code", required = false) String cartCode, HttpServletResponse response) {

		CartEntity cartEntity = cartService.findOneByCartCode(cartCode);
		if (SecurityUtils.isAuthenticanted() == true) {// Da dang nhap
			String username = SecurityUtils.getPrincipal().getUsername();
			cartService.updateOrCreateCartWithUsername(username, productId);
			System.out.println("Da dang nhap");
		} else if (cartEntity != null) {// Chua dang nhap, ton tai cookie cart
			cartService.updateCartItem(cartEntity, productId);
			System.out.println("Chua dang nhap, co cookie");
		} else {// Chua dang nhap, ko ton tai cookie cart
			System.out.println("chua dang nhap, k co cookie");

			// Random cart_code
			cartCode = RandomStringUtil.Random();
			System.out.println(cartCode);
			// Tao cookie cho cart
			Cookie cookie = new Cookie("cart_code", cartCode);
			cookie.setMaxAge(60 * 60 * 24 * 365);
			cookie.setPath("/");
			response.addCookie(cookie);

			// Tao moi cart
			cartService.saveCartWithCartCodeAndProductId(cartCode, productId);

		}
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView showCart(@CookieValue(value = "cart_code", required = false) String cartCode) {
		ModelAndView mav = new ModelAndView("web/cart");
		CartEntity cart;
		if (SecurityUtils.isAuthenticanted() == true) {// Da dang nhap
			String username = SecurityUtils.getPrincipal().getUsername();
			cart =cartService.findOneByUserName(username);
		} else {
			cart = cartService.findOneByCartCode(cartCode);
		}
		if(cart==null) {
			cart= new CartEntity();
		}
		mav.addObject("cart", cart);
		return mav;
	}
	
	
	@RequestMapping(value = "cart/plus",method = RequestMethod.GET)
	public @ResponseBody String plusQuantity(HttpServletRequest request) {
		Long cartItemId= Long.valueOf(request.getParameter("id"));
		cartService.upQuantityOfCartItem(cartItemId);
		CartItemEntity cartItem= cartService.findOneCartItem(cartItemId);
		ObjectMapper mapper= new ObjectMapper();
		String ajaxResponse= "";
		System.out.println(cartItem.getQuantity());
		try {
			ajaxResponse=mapper.writeValueAsString(cartItem.getQuantity());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = "cart/down",method = RequestMethod.GET)
	public @ResponseBody String downQuantity(HttpServletRequest request) {
		Long cartItemId= Long.valueOf(request.getParameter("id"));
		cartService.downQuantityOfCartItem(cartItemId);
		CartItemEntity cartItem= cartService.findOneCartItem(cartItemId);
		ObjectMapper mapper= new ObjectMapper();
		String ajaxResponse= "";
		System.out.println(cartItem.getQuantity());
		try {
			ajaxResponse=mapper.writeValueAsString(cartItem.getQuantity());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = "cart/remove",method = RequestMethod.GET)
	public @ResponseBody String removeCartItem(HttpServletRequest request) {
		Long cartItemId= Long.valueOf(request.getParameter("id"));
		cartService.removeCartItem(cartItemId);
		ObjectMapper mapper= new ObjectMapper();
		String ajaxResponse= "";
		String mess="Remove Successful";
		try {
			ajaxResponse=mapper.writeValueAsString(mess);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ajaxResponse;
	}
}
