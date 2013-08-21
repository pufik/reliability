package ua.edu.lp.reliability.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.edu.lp.reliability.facade.dto.UserDTO;
import ua.edu.lp.reliability.facade.user.UserFacade;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserFacade userFacade;

	@RequestMapping(value = "/info/{userId}", method = RequestMethod.GET)
	public String getUserInfo(@PathVariable(value = "userId") Long userId, Model model) {
		UserDTO userDto = userFacade.getUserInfoById(userId);
		model.addAttribute("user", userDto);

		return "user/user-info";
	}
}
