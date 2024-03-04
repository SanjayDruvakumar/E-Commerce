package com.sanjay.myshop.Service;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.sanjay.myshop.Dto.Product;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

public interface AdminService {

	String loadDashboard(HttpSession session);

	String loadAddProduct(HttpSession session,ModelMap map);

	String addProduct(Product product, BindingResult result, MultipartFile picture, HttpSession session, ModelMap map);

	String manageProducts(HttpSession session, ModelMap map);

	String deleteProduct(HttpSession session, int id, ModelMap map);

	String editPage(int id, HttpSession session, ModelMap map);

	String editProduct(@Valid Product product, HttpSession session, MultipartFile picture, BindingResult result);

	String createAdmin(String email, String password, HttpSession session);

	
}
