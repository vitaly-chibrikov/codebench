package events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by v.chibrikov on 07.01.2015.
 */
public class EventSource {
    private final Map<Class<? extends Event>, List<EventListener<? extends Event>>> eventSourceMap = new HashMap<>();

    public void subscribe(EventListener<? extends Event> listener) {
        Class<? extends Event> eventClass = listener.getEventClass();
        if (!eventSourceMap.containsKey(eventClass)) {
            eventSourceMap.put(eventClass, new ArrayList<>());
        }
        eventSourceMap.get(eventClass).add(listener);
    }

    public void unSubscribe(EventListener listener) {
        eventSourceMap.remove(listener.getEventClass());
    }

    @SuppressWarnings("unchecked")
    public void fire(Event event) {
        List<EventListener<? extends Event>> listeners = eventSourceMap.get(event.getClass());
        for (EventListener listener : listeners) {
            listener.handle(event);
        }
    }
}
