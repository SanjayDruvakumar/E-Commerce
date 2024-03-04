package com.sanjay.myshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sanjay.myshop.Dto.Product;
import com.sanjay.myshop.Service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;

	@GetMapping
	public String loadDashboard(HttpSession session) {
		return adminService.loadDashboard(session);
	}
	
	@GetMapping("/add-product")
	public String loadAddProduct(HttpSession session,ModelMap map) {
		return adminService.loadAddProduct(session,map);
	}
	
	@PostMapping("/add-products")
	public String addProduct(@Valid Product product,BindingResult result, @RequestParam MultipartFile picture,HttpSession session,ModelMap map) {
		
		return adminService.addProduct(product,result,picture,session,map);
	}
	
	@GetMapping("/manage-products")
	public String manageProducts(HttpSession session,ModelMap map) {
		return adminService.manageProducts(session,map);
	}
	
	
	
	 @GetMapping("/delete-product/{id}")
	    public String deleteProduct(HttpSession session,@PathVariable int id,ModelMap map) {
	       return adminService.deleteProduct(session,id,map);
	    }
	 
	 @GetMapping("/edit-product/{id}")
	 public String editPage(@PathVariable int id,HttpSession session,ModelMap map) {
		 return adminService.editPage(id,session,map);
	 }
	 
	 @PostMapping("/edit-product")
	 public String editProduct(@Valid Product product,HttpSession session,@RequestParam MultipartFile picture,BindingResult result) {
		 return adminService.editProduct(product,session,picture,result);
	 }
	 
	 @GetMapping("/create-admin/{email}/{password}")
		public String createAdmin(@PathVariable String email, @PathVariable String password,HttpSession session) {
			return adminService.createAdmin(email,password,session);
		}
}
