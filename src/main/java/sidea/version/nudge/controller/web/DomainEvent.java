package sidea.version.nudge.controller.web;

import org.springframework.context.ApplicationEvent;

public class DomainEvent extends ApplicationEvent {

    private final String name;
    private final int age;

    public DomainEvent(Object object, String name, int age){
        super(object);
        this.name = name;
        this.age = age;
    }

    public String getName() { return this.name;}

    public int getAge() { return this.age; }
}
