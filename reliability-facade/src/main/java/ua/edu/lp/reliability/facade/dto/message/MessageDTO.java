package ua.edu.lp.reliability.facade.dto.message;

public class MessageDTO {

	private final MessageType type;

	private String message;
	
	public MessageDTO() {
		this.type = MessageType.INFO;
	}

	public MessageDTO(MessageType type) {
		this.type = type;
	}

	public MessageDTO(MessageType type, String message) {
		this.type = type;
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
