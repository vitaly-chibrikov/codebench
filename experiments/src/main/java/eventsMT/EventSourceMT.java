package eventsMT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by v.chibrikov on 07.01.2015.
 */
public class EventSourceMT {
    private final Map<Class<? extends Event>, List<EventListener<? extends Event>>> eventSourceMap = new ConcurrentHashMap<>();

    public void subscribe(EventListener<? extends Event> listener) {
        Class<? extends Event> eventClass = listener.getEventClass();
        List<EventListener<? extends Event>> listeners = eventSourceMap.putIfAbsent(eventClass, new CopyOnWriteArrayList<>());
        if (listeners == null)
            eventSourceMap.get(eventClass).add(listener);
        else
            listeners.add(listener);
    }

    public void unSubscribe(EventListener listener) {
        eventSourceMap.remove(listener.getEventClass());
    }

    @SuppressWarnings("unchecked")
    public void fire(Event event) {
        List<EventListener<? extends Event>> listeners = eventSourceMap.get(event.getClass());
        if (listeners != null)
            for (EventListener listener : listeners) {
                listener.handle(event);
            }
    }
}
