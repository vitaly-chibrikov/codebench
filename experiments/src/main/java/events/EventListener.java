package events;

/**
 * Created by v.chibrikov on 07.01.2015.
 */
public interface EventListener<T extends Event> {
    void handle(T event);

    Class<T> getEventClass();
}
