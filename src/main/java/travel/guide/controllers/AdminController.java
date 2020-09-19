package travel.guide.controllers;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import travel.guide.GetDataFromApi.GetData;
import travel.guide.entities.City;
import travel.guide.entities.Traveller;
import travel.guide.entities.User;
import travel.guide.service.CityService;
import travel.guide.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	CityService cityService;
	@Autowired
	UserService userService;
	@Autowired
	 PasswordEncoder passwordEncode;
	@GetMapping("/AdminEntry")
	public String AdminEntry(){
		System.out.println("pap");
		return"AdminEntry";
	}
	@GetMapping("/Cities")
	public String CitiesList(Model model){
		model.addAttribute("cities", cityService.findAll());
		return"Cities";
	}
	@GetMapping("/DeleteCity/{id}")
	public String DeleteCity(@PathVariable("id")  int id,Principal  user,final RedirectAttributes redirectAttributes) {
		cityService.delete(id);
		 redirectAttributes.addFlashAttribute("message", "deleted");
		 return "redirect:/admin/Cities";
	}
	@PostMapping("/AddCity")
	public String AddCity( @RequestParam("name")String CityName,final RedirectAttributes redirectAttributes) throws IOException {
		if(cityService.findByName(CityName)!=null) {
			 redirectAttributes.addFlashAttribute("message", "city-exists");
		}else {
			CityName = CityName.substring(0,1).toUpperCase() + CityName.substring(1).toLowerCase();
			City new_city=GetData.RetrieveData(CityName);
			if(new_city==null) {
				 redirectAttributes.addFlashAttribute("message", "city-notfound");
			}else {
				cityService.save(new_city);
				 redirectAttributes.addFlashAttribute("message", "city-new");
			}
		}
        return "redirect:/admin/Cities";
	}
	@GetMapping("/Users")
	public String UsersList(Model model){
		model.addAttribute("users", userService.findAll());
        model.addAttribute("reqUser", new User());
		return"Users";
	}
    @RequestMapping(value = {"/AddUser"}, method = RequestMethod.POST)
    public String register(@ModelAttribute("reqUser") User reqUser,
                           final RedirectAttributes redirectAttributes) {
        User user = userService.findByUserName(reqUser.getUsername());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-name");
            return "redirect:/admin/Users";
        }
        user = userService.findByEmail(reqUser.getEmail());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            return "redirect:/admin/Users";
        }
		reqUser.setPassword(passwordEncode.encode(reqUser.getPassword()));
        reqUser.setRole("ROLE_USER");
        System.out.println(reqUser);
        if (userService.save(reqUser) != null) {
            redirectAttributes.addFlashAttribute("saveUser", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/admin/Users";
    }
    @GetMapping("/DeleteUser/{id}")
	public String DeleteUser(@PathVariable("id")  int id,final RedirectAttributes redirectAttributes) {
		userService.delete(id);
		 redirectAttributes.addFlashAttribute("message", "deleted");
		 return "redirect:/admin/Users";
	}
}
