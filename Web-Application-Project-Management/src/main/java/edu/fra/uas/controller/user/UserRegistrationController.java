package edu.fra.uas.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.fra.uas.service.user.UserService;
import edu.fra.uas.userdto.UserRegistrationDto;

@Controller

@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	// getting the "user" ModellAttribute from Thymeleaf
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {

		return new UserRegistrationDto();
	}

	// Show the registration page
	@GetMapping
	public String showRegistrationForm() {

		return "registration";

	}

	// Get the entered values and set this Value in to UserRegistrationDto and this
	// will be saved after encryption in to Database
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}

}