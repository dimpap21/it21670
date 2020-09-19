package travel.guide.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import travel.guide.GetDataFromApi.GetData;
import travel.guide.entities.Business;
import travel.guide.entities.City;
import travel.guide.entities.Tourist;
import travel.guide.entities.Traveller;
import travel.guide.entities.User;
import travel.guide.service.CityService;
import travel.guide.service.TravellerService;
import travel.guide.service.UserService;
import travel.guide.Files.*;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	CityService cityService;
	@Autowired
	TravellerService travellerService;

	@GetMapping("/UserEntry")
	public String UserEntry(Model model) {
		model.addAttribute("cities", cityService.findAll());
		return "UserEntry";
	}

	@GetMapping("/MyDestinations")
	public String UsersDestinations(Model model, Principal user) {
		User current_user = userService.findByUserName(user.getName());
		model.addAttribute("travellers", travellerService.findByUser_idl(current_user.getId()));
		return "MyDestinations";
	}

	@PostMapping("/AddCity")
	public String AddCity(@RequestParam("name") String CityName, final RedirectAttributes redirectAttributes)
			throws IOException {
		if (cityService.findByName(CityName) != null) {
			redirectAttributes.addFlashAttribute("message", "city-exists");
		} else {
			CityName = CityName.substring(0, 1).toUpperCase() + CityName.substring(1).toLowerCase();
			City new_city = GetData.RetrieveData(CityName);
			if (new_city == null) {
				redirectAttributes.addFlashAttribute("message", "city-notfound");
			} else {
				cityService.save(new_city);
				redirectAttributes.addFlashAttribute("message", "city-new");
			}
		}
		return "redirect:/user/UserEntry";
	}

	@GetMapping("/FreeTicket")
	public String FreeTicket(Model model) {
		model.addAttribute("cities", cityService.findAll());
		return "FreeTicket";
	}

	@GetMapping("/Project")
	public String Project(Model model) throws IOException {
		HashMap<String, String> map = SaveFile.FromFileToHashMap();
		model.addAttribute("items", map);
		return "Project";
	}

	@GetMapping("/FreeTicket/GetCity/{id}")
	public String GetCityFreeTicket(@PathVariable("id") int id, Principal user,
			final RedirectAttributes redirectAttributes) {
		User current_user = userService.findByUserName(user.getName());
		City city = cityService.findById(id);
		System.out.println(city);
		Traveller traveller = new Traveller();
		traveller = traveller.freeTicket(travellerService.findAll(), city);
		if (traveller.getUser() == current_user) {
			redirectAttributes.addFlashAttribute("message", "winner");
		} else {
			redirectAttributes.addFlashAttribute("message", "loser");
		}
		return "redirect:/user/FreeTicket";
	}

	@PostMapping(value = "/SaveTraveller")
	public String SaveTraveller(@RequestParam("city") String cityName, @RequestParam("role") String role,
			@RequestParam("prefWeather") String preferedWeather, @RequestParam("prefMuseums") int preferedMuseums,
			@RequestParam("prefCafes") int preferedCafesRestaurantsBars,
			@RequestParam("prefCities") ArrayList<String> preferedCities, @RequestParam("rainy") String rainy,
			Principal user, Model model, final RedirectAttributes redirectAttributes) throws IOException {
		City visit_city = new City();
		ArrayList<City> cities = new ArrayList<City>();
		for (int i = 0; i < preferedCities.size(); i++) {
			String city_name = preferedCities.get(i);
			City city = cityService.findByName(city_name);
			cities.add(city);
		}
		if (role.equals("Traveller")) {
			Traveller traveller = new Traveller(cityName, preferedWeather, preferedMuseums,
					preferedCafesRestaurantsBars, preferedCities, null);
			User current_user = userService.findByUserName(user.getName());
			traveller.setUser(current_user);
			System.out.println(traveller);
			if (traveller.GetTravellersCityCoordinates() == null) {
				redirectAttributes.addFlashAttribute("message", "current_city-notfound");
				return "redirect:/user/UserEntry";
			} else {
				traveller.GetTravellersCityCoordinates();
			}
			System.out.println(traveller);
			if (rainy.equals("No")) {
				visit_city = traveller.CompareCities(cities);
				traveller.setVisit(visit_city.getName());
				System.out.println(traveller);
				travellerService.save(traveller);
			} else {
				visit_city = traveller.CompareCities(cities, true);
				traveller.setVisit(visit_city.getName());
				travellerService.save(traveller);
			}
		}
		if (role.equals("Business")) {
			Business traveller = new Business(cityName, preferedWeather, preferedMuseums, preferedCafesRestaurantsBars,
					preferedCities, null);
			User current_user = userService.findByUserName(user.getName());
			traveller.setUser(current_user);
			if (traveller.GetTravellersCityCoordinates() == null) {
				redirectAttributes.addFlashAttribute("message", "current_city-notfound");
				return "redirect:/user/UserEntry";
			} else {
				traveller.GetTravellersCityCoordinates();
			}
			if (rainy.equals("No")) {
				visit_city = traveller.CompareCities(cities);
				traveller.setVisit(visit_city.getName());
				travellerService.save(traveller);
			} else {
				visit_city = traveller.CompareCities(cities, true);
				traveller.setVisit(visit_city.getName());
				travellerService.save(traveller);
			}

		}
		if (role.equals("Tourist")) {
			Tourist traveller = new Tourist(cityName, preferedWeather, preferedMuseums, preferedCafesRestaurantsBars,
					preferedCities, null);
			User current_user = userService.findByUserName(user.getName());
			traveller.setUser(current_user);
			if (traveller.GetTravellersCityCoordinates() == null) {
				redirectAttributes.addFlashAttribute("message", "current_city-notfound");
				return "redirect:/user/UserEntry";
			} else {
				traveller.GetTravellersCityCoordinates();
			}
			if (rainy.equals("No")) {
				visit_city = traveller.CompareCities(cities);
				traveller.setVisit(visit_city.getName());
				travellerService.save(traveller);
			} else {
				visit_city = traveller.CompareCities(cities, true);
				traveller.setVisit(visit_city.getName());
				travellerService.save(traveller);
			}
		}
		String image = GetData.GetCityImage(visit_city.getName());
		System.out.println(image);
		model.addAttribute("image", image);
		model.addAttribute("visit_city", visit_city);
		return "results";
	}

	@PostMapping(value = "/GetCities")
	public String GetCities1(@RequestParam("name") String CityName, final RedirectAttributes redirectAttributes)
			throws IOException {
		CityName = CityName.substring(0, 1).toUpperCase() + CityName.substring(1).toLowerCase();
		City new_city = GetData.RetrieveData(CityName);
		if (new_city == null) {
			redirectAttributes.addFlashAttribute("message", "city-notfound");
		} else {
			SaveFile.saveToFile(CityName + ":" + GetData.GetCitysDay(CityName), "citiesFile.txt");
			redirectAttributes.addFlashAttribute("message", "city-new");
		}
		return "redirect:/user/Project";

	}

	@GetMapping(value = "GetSmallerDayCity")
	public String GetSmallerDayCity(final RedirectAttributes redirectAttributes) throws IOException {
		redirectAttributes.addFlashAttribute("city", SaveFile.GetSmallerDayCity());
		return "redirect:/user/Project";
	}
	@GetMapping(value = "ClearFile")
	public String ClearFile(final RedirectAttributes redirectAttributes) throws IOException {
		SaveFile.clearFile();
		redirectAttributes.addFlashAttribute("file","deleted");
		return "redirect:/user/Project";
	}
}
