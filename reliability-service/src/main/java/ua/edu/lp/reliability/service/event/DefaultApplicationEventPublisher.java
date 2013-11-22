package ua.edu.lp.reliability.service.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ua.edu.lp.reliability.model.event.ApplicationEvent;

@Service("defaultApplicationEventPublisher")
public class DefaultApplicationEventPublisher implements ApplicationEventPublisherAware, ua.edu.lp.reliability.service.event.ApplicationEventPublisher {
	
	private Logger LOG = LoggerFactory.getLogger(DefaultApplicationEventPublisher.class);

	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	@Async
	public <T extends ApplicationEvent<?>> void publishEvent(T event) {
			LOG.info("Publish event: " + event);
			
			publisher.publishEvent(event);
	}
}
