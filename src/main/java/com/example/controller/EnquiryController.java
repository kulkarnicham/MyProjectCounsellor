package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.EnquiryModel;
import com.example.service.IEnquiryService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EnquiryController {

	@Autowired
	private IEnquiryService eservice;

	@GetMapping("/addstudent")
	public String enquiry(Model model) {
		model.addAttribute("enquiry", new EnquiryModel());
		return "addenquiry";

	}

	@PostMapping("/registerstuent")
	public String addEnquiry(Model model, EnquiryModel enquiry, HttpServletRequest request) {
		Integer cid = (Integer) request.getSession(false).getAttribute("cid");
		Boolean status = eservice.registerStudent(enquiry, cid);

		if (status) {
			model.addAttribute("smsg", "Student Registraction Success");
		} else {
			model.addAttribute("emsg", "Student Registraction Failed");
		}
		model.addAttribute("enquiry", new EnquiryModel());
		return "addenquiry";
	}

	@GetMapping("/allenquiry")
	public String getAllStudents(Model model, HttpServletRequest request) {

		Integer cid = (Integer) request.getSession(false).getAttribute("cid");
		// initially no filter is required so empty Enquiry object is sending to form
		// filer.
		List<EnquiryModel> list = eservice.getStudents(new EnquiryModel(), cid);
		model.addAttribute("allstudents", list);
		// for form binding
		model.addAttribute("enquiry", new EnquiryModel());
		return "viewstudents";
	}

	@PostMapping("/filterenquiries")
	public String getFilteredStudents(@ModelAttribute("enquiry") EnquiryModel enquiry, Model model,
			HttpServletRequest request) {
		Integer cid = (Integer) request.getSession(false).getAttribute("cid");
		List<EnquiryModel> list = eservice.getStudents(enquiry, cid);
		model.addAttribute("allstudents", list);
		// model.addAttribute("enquiry", new EnquiryModel());
		return "viewstudents";
	}

	@GetMapping("/editstudent")
	public String editEnquiry(@RequestParam("sid") Integer sid, Model model) {
		EnquiryModel enquiryEdit = eservice.getEnquiry(sid);
		// becoz of form binding object data will be populated on form fields
		model.addAttribute("enquiry", enquiryEdit);
		return "addenquiry";
	}

}
