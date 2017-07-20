package edu.mum.cs.projects.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.cs.projects.attendance.domain.entity.User;
import edu.mum.cs.projects.attendance.service.IServiceFacade;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IServiceFacade serviceFacade;
	
	@GetMapping("/newUser")
	public String newUser(Model model) {
		return "admin/createUser";
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("user") User user) {
		if (serviceFacade.createUser(user) != null) {
			return "redirect:admin/userInfo";
		} else {
			return "admin/error";
		}
	}

	@GetMapping("/userInfo")
	public String userInfo(Model model) {
		List<User> userList = serviceFacade.findAllUsers();
		model.addAttribute("userList", userList);
		return "admin/userInfo";
	}

	@GetMapping("/userInfo/edit/{userName}")
	public String getUserInfoEdit(Model model, @PathVariable("userName") String userName) {
		User user = serviceFacade.findUser(userName);
		System.out.println(user.getUserName());
		model.addAttribute("user", user);
		return "admin/updateUser";
	}
	
	@GetMapping("/userInfo/delete/{userName}")
	public String Delete(@PathVariable("userName") String userName, Model model) {
		try {
			serviceFacade.deleteUserByUserName(userName);
		} catch (Exception e) {
			model.addAttribute("msg", "This user couldn't be deleted.");
			return "admin/error";
		}
		return "redirect:/admin/userInfo";
	}

//	@PostMapping("/updateUser")
//	public String updateUser(@ModelAttribute("user") User user) {
//		System.out.println("user is going to be updated.");
//		System.out.println("user name is: " + user.getUserName());
//		serviceFacade.updateUser(user);
//		System.out.println("user is updated");
//		return "redirect:/admin/userInfo";
//	}

	@GetMapping(value = "/search")
	public String searchByName(Model model, @RequestParam("userName") String userName) {
		System.out.println("searcing: "+userName);
		User user = serviceFacade.findUser(userName);
		if(user == null){
			return "admin/error";
		}
		System.out.println("-------------Searching user------------");
		System.out.println(user.getUserName());
		model.addAttribute("user", user);

		return "admin/searchView";
	}
}
