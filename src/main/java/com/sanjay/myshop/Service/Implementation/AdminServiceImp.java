package com.sanjay.myshop.Service.Implementation;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.sanjay.myshop.Dao.CustomerDao;
import com.sanjay.myshop.Dao.ProductDao;
import com.sanjay.myshop.Dto.Customer;
import com.sanjay.myshop.Dto.Product;
import com.sanjay.myshop.Helper.AES;
import com.sanjay.myshop.Service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Service
public class AdminServiceImp implements AdminService {

	@Autowired
	Product product;
	
	@Autowired
	CustomerDao customerDao;

	@Autowired
	ProductDao productDao;

	public String loadDashboard(HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		} else {
			if (customer.getRole().equals("ADMIN"))
				return "AdminDashBoard";
			else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}
	}

	@Override
	public String loadAddProduct(HttpSession session, ModelMap map) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		} else {
			if (customer.getRole().equals("ADMIN")) {
				map.put("product", product);
				return "AddProduct";
			} else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}
	}


	@Override
	public String addProduct(Product product, BindingResult result, MultipartFile picture, HttpSession session,
			ModelMap map) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		} else {
			if (customer.getRole().equals("ADMIN")) {
				if (productDao.checkName(product.getName()))
					result.rejectValue("name", "error.name", "Product with Same Name Already Exists");

				if (result.hasErrors())
					return "AddProduct";
				else {
//					try {
//						byte[] image = new byte[picture.getInputStream().available()];
//						picture.getInputStream().read(image);
//
//						product.setImage(image);
					product.setImagePath("/images/" + product.getName() + ".jpg");
					productDao.save(product);

					File file = new File("src/main/resources/static/images");
					if (!file.isDirectory())
						file.mkdir();

					try {
						Files.write(Paths.get("src/main/resources/static/images", product.getName() + ".jpg"),
								picture.getBytes());
					} catch (IOException e) {
						session.setAttribute("failMessage", "You are Unauthorized to access his URL");
						return "redirect:/";
					}
					session.setAttribute("successMessage", "Product Added Success");
					return "redirect:/admin";

//					} catch (IOException e) {
//						session.setAttribute("failMessage", "You are Unauthorized to access his URL");
//						return "redirect:/";
//					}
				}
			} else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}
	}


	@Override
	public String manageProducts(HttpSession session, ModelMap map) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		} else {
			if (customer.getRole().equals("ADMIN")) {
				List<Product> products = productDao.fetchAll();
				if (products.isEmpty()) {
					session.setAttribute("failMessage", "No Products Present");
					return "redirect:/admin";
				} else {
					map.put("products", products);
					return "ManageProducts";
				}
			} else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}
	}

	@Override
	public String deleteProduct(HttpSession session, int id,ModelMap map) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		} else {
			if (customer.getRole().equals("ADMIN")) {
				Product product = productDao.findById(id);
				File file = new File("src/main/resources/static" + product.getImagePath());
				if (file.exists())
					file.delete();
				productDao.delete(product);
				session.setAttribute("successMessage", "Product Deleted Success");
				return "redirect:/admin/manage-products";
			} else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}
	}

	@Override
	public String editPage(int id, HttpSession session, ModelMap map) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		} else {
			if (customer.getRole().equals("ADMIN")) {
				Product product = productDao.findById(id);
				map.put("product", product);
				return "Edit";
			} else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}

	}

	@Override
	public String editProduct(@Valid Product product, HttpSession session, MultipartFile picture,
			BindingResult result) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			session.setAttribute("failMessage", "Invalid Session");
			return "redirect:/signin";
		} else {
			if (customer.getRole().equals("ADMIN")) {
				
				if (result.hasErrors())
					return "Edit";
				else {
					product.setImagePath("/images/" + product.getName() + ".jpg");
					productDao.save(product);

					File file = new File("src/main/resources/static/images");
					if (!file.isDirectory())
						file.mkdir();

					try {
						Files.write(Paths.get("src/main/resources/static/images", product.getName() + ".jpg"),
								picture.getBytes());
					} catch (IOException e) {
						session.setAttribute("failMessage", "You are Unauthorized to access his URL");
						return "redirect:/";
					}
					session.setAttribute("successMessage", "Product Updated Success");
					return "redirect:/admin";
				}
			} else {
				session.setAttribute("failMessage", "You are Unauthorized to access his URL");
				return "redirect:/";
			}
		}
}

	@Override
	public String createAdmin(String email, String password, HttpSession session) {
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setPassword(AES.encrypt(password, "123"));
		customer.setRole("ADMIN");
		customer.setVerified(true);
		customerDao.save(customer);
		session.setAttribute("successMessage", "Admin Account creation success");
		return "redirect:/";
	}

	}

