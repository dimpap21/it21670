package travel.guide.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MyErrorController implements ErrorController {
	@GetMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }
	@GetMapping("/user/error")
    public String handleError2() {
        //do something like logging
        return "error";
    }
	@RequestMapping(value = "/400")
	public String error400(Model model) {
		model.addAttribute("errorMsg", "User error handler 400");
		return "error";
	}

	@RequestMapping(value = "/404")
	public String error404(Model model) {
		model.addAttribute("errorMsg", "User error handler 404");
		return "error";
	}
	@RequestMapping(value = "/403")
	public String error403(Model model) {
		model.addAttribute("errorMsg", "User error handler 403");
		return "error";
	}

	@RequestMapping(value = "/500")
	public String error500(Model model) {
		model.addAttribute("errorMsg", "User error handler 500");
		return "error";
	}
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
