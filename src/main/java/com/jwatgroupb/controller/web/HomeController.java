/*
	@author:Quang Truong
	@date: Jan 14, 2020
*/

package com.jwatgroupb.controller.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jwatgroupb.entity.*;
import com.jwatgroupb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.repository.CategoryRepository;
import com.jwatgroupb.repository.ProductRepository;
import com.jwatgroupb.repository.RoleUserRepository;
import com.jwatgroupb.util.SecurityUtils;
import com.jwatgroupb.validator.UserValidator;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	@Autowired 
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	private EmailServiceImpl email;


	//--Phong
	@RequestMapping(value = { "/", "/HomePage" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		int page = 1;
		//		List<ProductEntity> listProduct = productService.findAllProduct();
//		List<CategoryEntity> listCategory = categoryService.listCategory();
//		mav.addObject("listCategory", listCategory);
//		mav.addObject("listProduct", listProduct);
		int totalProduct =(int) productRepository.count();
		int totalPage = (totalProduct%6!=0)?(totalProduct/6+1):totalProduct/6;
		List<ProductEntity> listProduct = productService.find10Product(page);
		List<CategoryEntity> listCategory = categoryService.listCategory();
		mav.addObject("listProduct", listProduct);
		mav.addObject("listCategory", listCategory);
		if(page==(totalPage)) {
			page=totalPage-1;
		};
		System.out.println("Name:"+SecurityUtils.getPrincipal().getName());
		System.out.println("Email"+SecurityUtils.getPrincipal().getEmail());
		mav.addObject("totalPage", totalPage);
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping(value =  {"/HomePage/{page}","/{page}"} , method = RequestMethod.GET)
	public ModelAndView homePage01(HttpServletRequest request ,@PathVariable (value = "page") int page) {
		ModelAndView mav = new ModelAndView("web/home");
		if (page==0) {
			page=1;
		} ;
		int totalProduct =(int) productRepository.count();
		int totalPage = (totalProduct%6!=0)?(totalProduct/6+1):totalProduct/6;
		List<ProductEntity> listProduct = productService.find10Product(page);
		List<CategoryEntity> listCategory = categoryService.listCategory();
		mav.addObject("listProduct", listProduct);
		mav.addObject("listCategory", listCategory);
		if(page==(totalPage)) {
			page=totalPage-1;
		};
		mav.addObject("totalPage", totalPage);
		mav.addObject("page", page);
		return mav;
	}
	@RequestMapping(value = {"/ProductCategory/{categoryName}" , "/HomePage/ProductCategory/{categoryName}"}, method = RequestMethod.GET)
	public ModelAndView category(@PathVariable("categoryName") String categoryName) {
		ModelAndView mav = new ModelAndView("web/productByCategory");
		List<ProductEntity> listProductByCategory = categoryService.findAllProduct(categoryName);
		mav.addObject("listProductByCategory", listProductByCategory);
		List<CategoryEntity> listCategory = categoryService.listCategory();
		mav.addObject("listCategory", listCategory);
		return mav;
	}
	
	@RequestMapping(value = {"search"},method = RequestMethod.POST)
	public ModelAndView search(@RequestParam String keyword) {
		System.out.println(keyword);
		ModelAndView mav = new ModelAndView("web/search");
		List<ProductEntity> listProduct = productService.findAllProduct();
		List<CategoryEntity> listCategory = categoryService.listCategory();
		List<ProductEntity> result = productService.search(keyword);
		mav.addObject("listCategory", listCategory);
		mav.addObject("listProduct", listProduct);
		mav.addObject("result", result);

		return mav;
	}
	
	@RequestMapping(value = "/product-details/{id}",method = RequestMethod.GET)
	public ModelAndView productdetails(HttpServletRequest request,@PathVariable (value = "id") Long id){
		ModelAndView mav =new ModelAndView("web/product-details");
		ProductEntity product = productService.findByOneProductId(id);
		List<CategoryEntity> listCategory = categoryService.listCategory();
		mav.addObject("listCategory", listCategory);
		mav.addObject("product", product);
		return mav;
	}
	
	//---

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("web/login");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/HomePage/1");
	}

	@RequestMapping(value = "/customerAccessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/login?accessDenied");
	}



	@RequestMapping(value = "/LoginSuccessful")
	public ModelAndView loginSuccessful(@CookieValue(value = "cart_code", required = false) String cartCode) {
		String username = SecurityUtils.getPrincipal().getUsername();
		if (cartCode != null) {
			cartService.addCartAfterLogin(username, cartCode);
		}
		return new ModelAndView("redirect:/HomePage/1");
	}

	// Hai update

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@ModelAttribute("userForm")
	public UserEntity getUser() {
		return new UserEntity();
	}

	// Register
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new UserEntity());
		return "login";
	}

	// Register
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") UserEntity userForm, BindingResult bindingResult,
			Model model,@RequestParam("fullName") String fullName) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "web/login";
		}
		userForm.setActive(SystemConstant.ACTIVE_STATUS);
		RoleUserEntity roleUser = roleUserRepository.findOneById(2);
		userForm.setRoleUserEntity(roleUser);

		ProfileUserEntity profileUser = new ProfileUserEntity();
		profileUser.setName(fullName);
		profileUser.setUserEntity(userForm);
		userService.saveProfileUser(profileUser);

		userService.saveUser(userForm);
		cartService.addCartAfterReg(userForm.getUserName());//Tao gio hang cho user moi
		
		return "notice/registerSuccess";
	}


	@ModelAttribute("infoForm")
	public ProfileUserEntity getProfileUser() {
		return new ProfileUserEntity();
	}

	// Format Date
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}
	
	
	
}
