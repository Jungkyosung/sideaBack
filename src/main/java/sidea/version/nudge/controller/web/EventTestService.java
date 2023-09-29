package sidea.version.nudge.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventTestService {

    private final ApplicationEventPublisher eventPublisher;

    public EventTestService(ApplicationEventPublisher eventPublisher){
        this.eventPublisher = eventPublisher;
    }

    public void customEvent(){
        DomainEvent event = new DomainEvent(this, "name", 15);
        eventPublisher.publishEvent(event);
    }
}
