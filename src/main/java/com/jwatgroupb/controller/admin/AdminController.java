/*
	@author:Quang Truong
	@date: Jan 20, 2020
*/

package com.jwatgroupb.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jwatgroupb.entity.ProductEntity;
import com.jwatgroupb.entity.RoleUserEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.ProductRepository;
import com.jwatgroupb.repository.RoleUserRepository;
import com.jwatgroupb.repository.UserRepository;
import com.jwatgroupb.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	public AdminService adminService;
	@Autowired
	public RoleUserRepository roleUserRepository;
	
	  @Autowired
	  public ProductRepository productRepository;
	  @Autowired
	  public UserRepository userRepository;
	
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("admin/login");
		return mav;
	}

	@RequestMapping(value = "/admin/Home", method = RequestMethod.GET)
	public ModelAndView homeAdmin() {
		return new ModelAndView("admin/home");
	}

	@RequestMapping(value = "/adminAccessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/adminlogin?accessDenied");
	}

	@RequestMapping(value = "admin/LoginSuccessful", method = RequestMethod.GET)
	public ModelAndView loginSuccessful() {
		return new ModelAndView("redirect:/admin/listUser/1");
	}

	@RequestMapping(value = "admin/private", method = RequestMethod.GET)
	public ModelAndView privatePage() {
		return new ModelAndView("admin/Private");
	}

	//CRUD

	/// Get amount of page
	  public ArrayList<Integer> paging (String input)
	  {
		  ArrayList<Integer> listPage = new ArrayList<Integer>();

		  if (input == "user")
		  {
			  int totalUser = (int) userRepository.countTotalRecords();
			  int totalPage =(totalUser%10!=0)? (totalUser /10+1)  : totalUser/10;
			  for ( int i = 1; i<=totalPage;i++) 
			  listPage.add(i);
		  }
		  else if (input == "product")
		  {
			  int totalProduct = (int) productRepository.countTotalRecords();
			  int totalPage =(totalProduct%10!=0)? (totalProduct /10+1)  : totalProduct/10;
			  for ( int i = 1; i<=totalPage;i++) 
			  listPage.add(i);  
		  }
		  
		  return listPage;
	  }
	  
	  
	  
	  ///-----LIST USER || LIST PRODUCT ------
	  @RequestMapping(value = "admin/listProduct/{page}", method = RequestMethod.GET)
		public ModelAndView findAllProduct(@PathVariable (value="page") int page) {
		  ModelAndView mav = new ModelAndView("admin/listProduct");
		  List<ProductEntity> listProduct=adminService.find10Products(page);
		  ArrayList<Integer> listPage = paging("product");
		  mav.addObject("listProduct", listProduct);
		  mav.addObject("listPage", listPage);
			return mav;
		}
	  @RequestMapping(value = "admin/listUser/{page}", method = RequestMethod.GET)
	 	public ModelAndView findAllUser(@PathVariable (value="page") int page) {	  	
	 		  ModelAndView mav = new ModelAndView("admin/listUser");
			  List<UserEntity> listUser=adminService.find10Users(page);
			  ArrayList<Integer> listPage = paging("user");
			  mav.addObject("listPage", listPage);
			  mav.addObject("listUser", listUser);
			  return mav;
	 	}
	  
	  ///-----SEARCH USER  || SEARCH PRODUCT
	  @RequestMapping("admin/listUser/search")
	  public ModelAndView searchUser(@RequestParam String keyword) {
		  System.out.println(keyword);
	      List<UserEntity> result = adminService.searchUser(keyword);
	      ModelAndView mav = new ModelAndView("admin/search");
	      mav.addObject("result", result);
	   
	      return mav;    
	  }
	  
	  @RequestMapping("admin/listProduct/search")
	  public ModelAndView searchProduct(@RequestParam String keyword) {
		  System.out.println(keyword);
	      List<ProductEntity> result = adminService.searchProduct(keyword);
	      ModelAndView mav = new ModelAndView("admin/searchproduct");
	      mav.addObject("result", result);
	      return mav;    
	  }
	 //// ADD  USER - ADD PRODUCT
	  @RequestMapping(value = "admin/adduser", method = RequestMethod.GET)
	 	public ModelAndView displayNewUserForm(UserEntity user) {
	 		ModelAndView mav = new ModelAndView("admin/adduser");
	 		mav.addObject("user", user);
	 		return mav;
	 	}
	  
	  @RequestMapping(value = "admin/saveNewUser", method = RequestMethod.POST)
		public ModelAndView saveNewUser(@RequestParam("roleUserEntity") Long roleid, @ModelAttribute("user") UserEntity user, BindingResult result ) {
			ModelAndView mav = new ModelAndView();
			RoleUserEntity role = roleUserRepository.findOne(roleid);
			user.setRoleUserEntity(role);
			if (adminService.findUserByUserName(user.getUserName()) == null) {
			adminService.saveUser(user,roleid);
			mav.setViewName("redirect:/admin/listUser/1");}
			else { 
				System.out.println("User da ton tai");
		        mav.setViewName("redirect:/admin/adduser");
		        String message="Username exists";   
		        mav.addObject("message", message);     
					}
			return mav;
		}
	  
	  
	  @RequestMapping(value = "admin/addproduct", method = RequestMethod.GET)
	 	public ModelAndView displayNewProductForm(ProductEntity product) {
	 		ModelAndView mav = new ModelAndView("admin/addproduct");
	 		mav.addObject("product", product);
	 		return mav;
	 	}
	  @RequestMapping(value = "admin/saveNewProduct", method = RequestMethod.POST)
		public ModelAndView saveNewProduct(@ModelAttribute("product") ProductEntity product, BindingResult result ) {
			ModelAndView mav = new ModelAndView("redirect:/admin/listProduct/1");
			adminService.saveProduct(product);
			return mav;
		}

		////--- DELETE USER || DELETE PRODUCT

	  @RequestMapping(value="admin/listUser/delete/{id}",method = RequestMethod.GET)    
	  public ModelAndView deleteUser(@PathVariable("id") Long id){    
		  adminService.deleteUser(id);   
	      return new ModelAndView ("redirect:/admin/listUser/1");    
	  }  
	  
	  @RequestMapping(value="admin/listProduct/delete/{id}",method = RequestMethod.GET)    
	  public ModelAndView deleteProductById(@PathVariable("id") Long id){    
		  adminService.deleteProduct(id);   
	      return new ModelAndView ("redirect:/admin/listProduct/1");    
	  }  

	  @RequestMapping(value="admin/listProduct/delete",method = RequestMethod.POST)    
	  public ModelAndView deleteProduct(@RequestParam(value = "checkbox", required =false) List<Long> checkbox,HttpServletRequest request){   
		  ModelAndView mav = new ModelAndView("redirect:/admin/listProduct/1");
		  for (long i: checkbox) {
			  adminService.deleteProduct(i);
	  }
	      return mav;   
	  }   
	  
	  ///EDIT USER || EDIT PRODUCT
	  @RequestMapping(value="admin/editUser/{userName}")
	  public ModelAndView userUpdate(@PathVariable("userName") String userName){
	   ModelAndView mav = new ModelAndView("admin/userEditForm");
	   UserEntity user = adminService.findUserByUserName(userName);
	   mav.addObject("userEditForm",user);
	   System.out.println("USER EDIT FORM LOADED");
	   return mav;
	  }    
	  
	  @RequestMapping(value="admin/editUser/saveEditUser", method=RequestMethod.POST)
	  public ModelAndView userUpdateSave(@RequestParam("userName") String userName, @RequestParam("roleUserEntity.id") Long roleid, @ModelAttribute("userEditForm") UserEntity user ){
		  RoleUserEntity role = roleUserRepository.findOne(roleid);
		  user.setRoleUserEntity(role);
		  adminService.userUpdate(user);	
	   return new ModelAndView("redirect:/admin/listUser/1");
	}
	  
	  @RequestMapping(value="admin/editProduct/{id}")
	  public ModelAndView productUpdate(@PathVariable("id") long id){
	   ModelAndView mav = new ModelAndView("admin/productEditForm");
	   ProductEntity product = adminService.findOneProduct(id);
	   mav.addObject("productEditForm", product);
	   System.out.println("PRODUCT EDIT FORM LOADED");
	   return mav;
	  }    

	  @RequestMapping(value="admin/editProduct/saveEditProduct", method=RequestMethod.POST)
	  public ModelAndView productUpdateSave(@RequestParam("id") long id,@ModelAttribute("productEditForm") ProductEntity product){	 ;
		  adminService.productUpdate(product,id);
	   return new ModelAndView("redirect:/admin/listProduct/1");
	}
}
