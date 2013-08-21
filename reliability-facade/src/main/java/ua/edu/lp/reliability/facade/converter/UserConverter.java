package ua.edu.lp.reliability.facade.converter;

import ua.edu.lp.reliability.facade.dto.UserDTO;
import ua.edu.lp.reliability.model.annotation.spring.Converter;
import ua.edu.lp.reliability.model.user.User;

@Converter
public class UserConverter extends AbstractConverter<User, UserDTO> {

	@Override
	public UserDTO convert(User source) {
		return convert(source, new UserDTO());
	}

	@Override
	public UserDTO convert(User source, UserDTO target) {
		target.setUid(source.getUid());
		target.setLogin(source.getLogin());
		target.setPassword(source.getPassword());
		target.setFullname(source.getFullname());

		return target;
	}

}
