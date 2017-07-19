package edu.mum.cs.projects.attendance.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

//	@Autowired
//  private UserValidator userValidator;

//	@Autowired
//  private UserDetailsService userDetailsService;
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "home";
    }
	
	@RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String afterLogin(Model model) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			switch(grantedAuthority.getAuthority()) {
			case "ROLE_ADMIN":
				return "/admin/welcomeStaff";
			case "ROLE_FACULTY":
				return "/faculty/welcomeStaff";
			case "ROLE_STAFF":
				return "/staff/welcomeStaff";
			case "ROLE_STUDENT":
				return "/student/welcomeStaff";
			}
		}  

        return "home";
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
		if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
            return "home";
        }
        
        return "login";
    }
}
