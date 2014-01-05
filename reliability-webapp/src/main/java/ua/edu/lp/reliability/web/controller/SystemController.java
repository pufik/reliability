package ua.edu.lp.reliability.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.edu.lp.reliability.facade.dto.message.MessageDTO;
import ua.edu.lp.reliability.facade.dto.message.MessageType;
import ua.edu.lp.reliability.web.util.Constants;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

	@RequestMapping(value = "/error/{errorCode}")
	@ResponseBody
	public MessageDTO handleException(@PathVariable(value = "errorCode") String errorCode, HttpServletRequest request) {
		// TODO: Implement custom error mapping for UI.
		MessageDTO messageData = new MessageDTO(MessageType.ERROR);

		String message = (String) request.getAttribute(Constants.HTTP_ERROR_MESSAGE_ATTR_NAME);

		if (StringUtils.isEmpty(message)) {
			// TODO: Fix getting message from Exception.
			Throwable exception = (Throwable) request.getAttribute(Constants.HTTP_ERROR_EXCEPTION_ATTR_NAME);
			message = (exception != null) ? exception.toString() : StringUtils.EMPTY;
		}

		messageData.setMessage(message);

		return messageData;
	}
}
