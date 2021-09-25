package controller;

import java.util.List;
import java.util.Optional;

import Response.Payload;
import Response.UpdatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;


import model.User;
import services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("/{id}/submitDetails")
	private User submitDetails(@RequestBody Payload payload,@PathVariable("id") Integer id) {

		return userService.submitDetails(payload,id);
	}
	@PostMapping("")
	private User submitUser(@RequestBody User user) {
		return userService.submitMetaDataOfUsers(user);
	}


	@GetMapping("/email={email}")
	private User returnUser(@PathVariable("email") String email){
		return userService.returnUser(email);
	}

	@GetMapping("/{id}")
	private User getUserDetails(@PathVariable("id") Integer id) {
		return userService.displayUserMetaData(id);
		
	}

	@PostMapping ("/update/{id}")
	private User updateUser(@RequestBody UpdatePayload user, @PathVariable("id") Integer id){
		return (User) userService.updateUser(user,id);
	}


	@GetMapping("/{id}/result")
	private List<User>getAllUsers(@PathVariable("id") Integer id, @RequestParam(required = false) Integer budgetMin,@RequestParam(required = false) Integer budgetMax,@RequestParam(required = false) String location) {
		return userService.getAllUsers(id,budgetMin,budgetMax,location);
	}
	@GetMapping("/sendMail")
	private String sendEmail(@RequestParam(required = true)String to_email,@RequestParam(required = true)String to_name,@RequestParam(required = true)String self){
		return userService.sendEmail(to_email,to_name,self);
	}
}

