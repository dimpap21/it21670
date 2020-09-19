package travel.guide.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import travel.guide.entities.City;
import travel.guide.entities.User;
import travel.guide.service.CityService;
import travel.guide.service.UserService;

@Controller 
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    CityService cityService;
	@Autowired
	 PasswordEncoder passwordEncode;
	
	  @GetMapping("/")
	    public String Home(Model model) {
	        model.addAttribute("reqUser", new User());
	        return "login";
	    }
    @GetMapping("/login")
    public String Userlogin(Model model) {
        model.addAttribute("reqUser", new User());
        return "login";
    }
    @RequestMapping("/register")
    public String UserRegister(Model model) {
        model.addAttribute("reqUser", new User());
        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String register(@ModelAttribute("reqUser") User reqUser,
                           final RedirectAttributes redirectAttributes) {

        User user = userService.findByUserName(reqUser.getUsername());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-name");
            return "redirect:/login";
        }
        user = userService.findByEmail(reqUser.getEmail());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            return "redirect:/login";
        }
		reqUser.setPassword(passwordEncode.encode(reqUser.getPassword()));
        reqUser.setRole("ROLE_USER");
        System.out.println(reqUser);
        if (userService.save(reqUser) != null) {
            redirectAttributes.addFlashAttribute("saveUser", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}
  
}
