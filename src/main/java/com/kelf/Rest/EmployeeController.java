package com.kelf.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller

public class EmployeeController {  

	@Autowired
	private ReCaptchaValidationService validator;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	 @GetMapping("/register")
	 public String showRegister(Model model)
	 {  
		 model.addAttribute("employee", new EmployeeEntity());
		 return "EmployeeRegister";
	  }
	 
	 @GetMapping("/")
	 public String showhome(Model model)
	 {  
		 model.addAttribute("employee", new EmployeeEntity());
		 return "aboutus";
	  }
	 
	 @GetMapping("/dob")
	 public String showdob(Model model)
	 {  
		 model.addAttribute("employee", new EmployeeEntity());
		 return "dob";
	  }
	 
	 @GetMapping("/learn")
	 public String showlearn(Model model)
	 {  
		 model.addAttribute("employee", new EmployeeEntity());
		 return "learn";
	  }
	 
	 @GetMapping("/predict")
	 public String showpredict(Model model)
	 {  
		 model.addAttribute("employee", new EmployeeEntity());
		 return "predict";
	  }
	 
	 @GetMapping("/adminlogin")
	 public String showadmin(Model model)
	 {  
		 model.addAttribute("employee", new EmployeeEntity());
		 return "adminlogin";
	  }
	 
	 @PostMapping("/save")
	 public String saveEmployee(@ModelAttribute("employee")
	 EmployeeEntity employee,  
	 
	 @RequestParam(name="g-recaptcha-response")
	 String captcha, Model model) 
	 {  
		 if(validator.validateCaptcha(captcha))
	        {    
			 employeeRepository.save(employee); 
			 model.addAttribute("employee", new EmployeeEntity());
			 model.addAttribute("message", "Employee added!!"); 
			 } 
		     else { 
		    	 model.addAttribute("message", "Please Verify Captcha");
		    	 }      
		 return "EmployeeRegister"; 
	}  
	 @PostMapping("adduser")
	 public ModelAndView addUser(HttpServletRequest request)
	 {
		 ModelAndView  mv=new ModelAndView();
		 String msg="";
		 try
		 {
			 String fname=request.getParameter("firstname");
			 String lname=request.getParameter("lastname");
			 String email=request.getParameter("email");
			 String phn=request.getParameter("phone");
			 String pwd=request.getParameter("password");
			 
			 EmployeeEntity e = new EmployeeEntity();
			 e.setFirstname(fname);
			 e.setLastname(lname);
			 e.setEmail(email);
			 e.setPhone(phn);
			 e.setPassword(pwd);
			 
			 msg= validator.signup(e);
			 System.out.println(msg);
			 mv.setViewName("EmployeeRegister");
		 }
		 catch(Exception e){
			 msg=e.getMessage();
			 mv.setViewName("EmployeeRegister");
			 System.out.println(msg);
		 }
		 return mv;
	 }
	 
	 @PostMapping("checkemployeelogin")
	 public ModelAndView checkemployeelogin(HttpServletRequest request)
	 {
		 ModelAndView  mv=new ModelAndView();

			 String username=request.getParameter("username");
			 String pwd=request.getParameter("password");
			 
			 EmployeeEntity emp = validator.checkadminlogin(username, pwd);
			 
			 if(emp!=null)
			 {
				 mv.setViewName("afterlogin");
			 }
			 else 
			 {
				 mv.setViewName("EmployeeRegister");
			 }
		 
		 return mv;
	 }
	 
	 @GetMapping("/all")
	 public String getAllEmployees(Model model)
	 {  
		 model.addAttribute("list", employeeRepository.findAll()); 
         return "EmployeeData"; 
         
	 } 
	 
	 
	} 