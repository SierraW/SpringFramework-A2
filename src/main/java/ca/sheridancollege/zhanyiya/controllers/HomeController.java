package ca.sheridancollege.zhanyiya.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.zhanyiya.beans.User;
import ca.sheridancollege.zhanyiya.repositories.RoleRepository;
import ca.sheridancollege.zhanyiya.repositories.UserRepository;
import lombok.AllArgsConstructor;

/*
 * 
 * IMPORTANT: PORT CHANGED TO: 8443
 * 
 */

@Controller
@AllArgsConstructor
public class HomeController {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	private String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	@GetMapping("/")
	public String index() {
		return "redirect:/forum";
	}
	
	@GetMapping("/secure")
	public String secureIndex(Authentication authentication, Model model) {
		
		String name = authentication.getName();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga: authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		model.addAttribute("name", name);
		model.addAttribute("roleList", roleList);
		
		return "/secure/index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "/error/index";
	}
	
	@GetMapping("/register")
	public String goRegistration () {
		return "register";
	}
	
	@PostMapping("/register")
	public String doRegistration(@RequestParam String username, @RequestParam String password) {
		
		User user = new User(username, encodePassword(password), true);
		user.getRoleList().add(roleRepository.findByRolename("ROLE_USER"));
		userRepository.save(user);
		
		return "redirect:/";
	}

}
