package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.CounsellorModel;
import com.example.model.EnquiryDashboard;
import com.example.service.ICounsellorService;
import com.example.service.IEnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	@Autowired
	private ICounsellorService cservice;
	@Autowired
	private IEnquiryService eservice;

	@GetMapping("/")
	public String counsellorLogin(Model model) {
		// sending empty object to UI for form binding to display form along with
		// binding object.
		// (map the java class object to form fields so while submitting form, form data
		// will coming to object directly)
		// We no need to write logic to retrieve form data
		model.addAttribute("counsellor", new CounsellorModel());
		return "index";
	}

	@PostMapping("/login")
	public String validateLogin(Model model, CounsellorModel counsellor, HttpServletRequest request) {

		CounsellorModel counsellorFromDb = cservice.getCounsellor(counsellor.getCemail(), counsellor.getCpwd());
		if (counsellorFromDb != null) {
			// true -> new session obj is created for every user
			// false ->Don't create new session and get existed session for particular user
			HttpSession session = request.getSession(true);
			session.setAttribute("cid", counsellorFromDb.getCid());

			return "redirect:dashboard";
		} else {
			model.addAttribute("emsg", "Invalid Credentials");
			model.addAttribute("counsellor", new CounsellorModel());
			return "index";
		}

	}
	
	@GetMapping("/dashboard")
	public String viewDashbord(Model model,HttpServletRequest request) {
		Integer cid = (Integer) request.getSession(false).getAttribute("cid");
		EnquiryDashboard dashboardInfo = eservice.getDashBoard(cid);
		model.addAttribute("dashboard", dashboardInfo);
		model.addAttribute("smsg", "Login Successfull");
		return "dashboard";
	}

	@GetMapping("/logout")
	public String logoutCounsellor(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();

		return "redirect:/";
	}

	@GetMapping("/register")
	public String viewRegister(Model model) {
		model.addAttribute("counsellor", new CounsellorModel());
		return "register";
	}

	@PostMapping("/addcounsellor")
	public String CounsellorRegiser(Model model, CounsellorModel counsellor) {
		Boolean regStatus = cservice.registerCounsellor(counsellor);
		if (regStatus) {
			model.addAttribute("smsg", "Registraction Successfull");
			model.addAttribute("counsellor", new CounsellorModel());
		} else {
			model.addAttribute("emsg", "Registraction Failed");
			model.addAttribute("counsellor", new CounsellorModel());
		}
		return "register";
	}

}
