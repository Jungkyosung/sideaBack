package sidea.version.nudge.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventTestListener implements ApplicationListener<DomainEvent> {

    @Override
    public void onApplicationEvent(DomainEvent event) {
        log.info("name={}", event.getName(), "age={}",event.getAge());
        log.info("Handling Context started event.");
    }
}
