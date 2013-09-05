package ua.edu.lp.reliability.facade.user;

import javax.annotation.Resource;

import ua.edu.lp.reliability.facade.converter.Converter;
import ua.edu.lp.reliability.facade.dto.UserDTO;
import ua.edu.lp.reliability.model.annotation.spring.Facade;
import ua.edu.lp.reliability.model.user.User;
import ua.edu.lp.reliability.service.user.UserService;

@Facade("userFacade")
public class DefaultUserFacade implements UserFacade {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "userConverter")
	private Converter<User, UserDTO> userConverter;

	@Override
	public UserDTO getUserInfoById(Long userId) {
		User user = userService.getUserById(userId);
		UserDTO userDto = userConverter.convert(user);

		return userDto;
	}
}
