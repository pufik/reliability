package ua.edu.lp.reliability.service.event;

import org.springframework.context.ApplicationListener;

import ua.edu.lp.reliability.model.event.ApplicationEvent;

public abstract class BaseApplicationEventListener<SOURCE, EVENT extends ApplicationEvent<SOURCE>> implements ApplicationEventListener<EVENT>,
		ApplicationListener<EVENT> {

	@Override
	public void onApplicationEvent(EVENT event) {
		handle(event);
	}
}
