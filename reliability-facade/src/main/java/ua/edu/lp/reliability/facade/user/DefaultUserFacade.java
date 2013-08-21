package ua.edu.lp.reliability.facade.user;

import org.springframework.beans.factory.annotation.Autowired;

import ua.edu.lp.reliability.facade.converter.Converter;
import ua.edu.lp.reliability.facade.dto.UserDTO;
import ua.edu.lp.reliability.model.annotation.spring.Facade;
import ua.edu.lp.reliability.model.user.User;
import ua.edu.lp.reliability.service.user.UserService;

@Facade
public class DefaultUserFacade implements UserFacade {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Converter<User, UserDTO> userConverter;

	public UserDTO getUserInfoById(Long userId) {
		User user = userService.getUserById(userId);
		UserDTO userDto = userConverter.convert(user);
		
		return userDto;
	}

}
