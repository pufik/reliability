package ua.edu.lp.reliability.facade.user;

import ua.edu.lp.reliability.facade.dto.UserDTO;

public interface UserFacade {
	UserDTO getUserInfoById(Long userId);
}
