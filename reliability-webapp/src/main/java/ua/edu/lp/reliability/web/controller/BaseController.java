package ua.edu.lp.reliability.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.edu.lp.reliability.facade.dto.message.MessageDTO;
import ua.edu.lp.reliability.facade.dto.message.MessageType;

@Controller
public class BaseController {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody
	MessageDTO handleExeption(Exception exeption) {
		MessageDTO messageData = new MessageDTO(MessageType.ERROR);

		// TODO: Should be fixed to message handling
		messageData.setMessage(exeption.toString());

		return messageData;
	}
}
