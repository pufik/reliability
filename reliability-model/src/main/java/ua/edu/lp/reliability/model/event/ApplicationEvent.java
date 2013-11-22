package ua.edu.lp.reliability.model.event;

public abstract class ApplicationEvent<SOURCE> extends org.springframework.context.ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public ApplicationEvent(SOURCE source) {
		super(source);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public SOURCE getSource(){
		return (SOURCE) super.getSource();
	}
}
