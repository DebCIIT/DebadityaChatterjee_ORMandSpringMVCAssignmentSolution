package com.greatlearning.crmapp_demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greatlearning.crmapp_demo.entity.Customer;
import com.greatlearning.crmapp_demo.service.CustomerService;



@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService CustomerService;



	// add mapping for "/list"

	@RequestMapping("/list")
	public String listBooks(Model theModel) {

		System.out.println("request recieved");
		// get Books from db
		List<Customer> theCustomers = CustomerService.findAll();
		

		// add to the spring model
		theModel.addAttribute("Customers", theCustomers);

		return "list-Customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("Customer", theCustomer);

		return "Customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
			Model theModel) {

		// get the Book from the service
		Customer theCustomer = CustomerService.findById(theId);


		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("Customer", theCustomer);

		// send over to our form
		return "Customer-form";			
	}


	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id,
			@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email) {

		System.out.println(id);
		Customer theCustomer;
		if(id!=0)
		{
			theCustomer=CustomerService.findById(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setemail(email);
			}
		else
			theCustomer=new Customer(firstName, lastName, email);
		// save the Book
		CustomerService.save(theCustomer);


		// use a redirect to prevent duplicate submissions
		return "redirect:/customer/list";

	}


	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {

		// delete the Book
		CustomerService.deleteById(theId);

		// redirect to /Books/list
		return "redirect:/customer/list";

	}
}