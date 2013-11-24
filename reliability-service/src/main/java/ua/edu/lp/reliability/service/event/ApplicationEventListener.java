package ua.edu.lp.reliability.service.event;

import ua.edu.lp.reliability.model.event.ApplicationEvent;

public interface ApplicationEventListener<EVENT extends ApplicationEvent<?>> {

	void handle(EVENT event);
}
