package ua.edu.lp.reliability.service.event;

import ua.edu.lp.reliability.model.event.ApplicationEvent;

public interface ApplicationEventPublisher {

	<T extends ApplicationEvent<?>> void publishEvent(T event);
}
