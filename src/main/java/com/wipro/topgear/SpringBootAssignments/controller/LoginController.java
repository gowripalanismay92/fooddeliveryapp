package com.wipro.topgear.SpringBootAssignments.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.wipro.topgear.SpringBootAssignments.transport.User;
import com.wipro.topgear.SpringBootAssignments.transport.UserForm;

/****
 * 
 * @author Go334146
 *
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	RestTemplate restTemplate;

	/***
	 * method will return the bank branchList details
	 * 
	 * @return branchList is view Name. It will call the branchList.jsp page
	 */
	@GetMapping("/")
	public String showWelcomePage() {
		logger.info("-----processing the index page----");
		return "index";
	}

	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		logger.info("-----processing the index page----");
		model.addAttribute("userForm", new UserForm());
		return "RegisterForm";
	}

	@PostMapping("/register")
	public String show(UserForm userForm, Model model) {
		logger.info("-----processing the registerForm-----");
		String url = "http://localhost:9090/api/users?emailId=" + userForm.getUsername() + "&password="
				+ userForm.getPassword();
		ResponseEntity<String> message = restTemplate.postForEntity(url, null, String.class);
		model.addAttribute("status", message.getBody());
		return "index";
	}

	@GetMapping("/signup")
	public String showSignUpPage(Model model) {
		logger.info("-----processing the index page----");
		model.addAttribute("userForm", new UserForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String getBankBranchList(UserForm userForm, Model model) {
		logger.info("-----processing the registerForm-----");
		String url="http://localhost:9090/api/users?emailId="+userForm.getUsername()+"&password="+userForm.getPassword();
		
		ResponseEntity<User>validation=restTemplate.getForEntity(url, User.class);
		if(validation.getBody() != null) {
			
		}else {
			model.addAttribute("s	tatus", "User not found");
			return "index";
		}
		return "restaurants";
	}

}
