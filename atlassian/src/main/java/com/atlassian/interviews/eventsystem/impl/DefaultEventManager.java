package com.atlassian.interviews.eventsystem.impl;

import com.atlassian.interviews.eventsystem.EventManager;
import com.atlassian.interviews.eventsystem.InterviewEvent;
import com.atlassian.interviews.eventsystem.InterviewEventListener;
import com.atlassian.interviews.eventsystem.events.Creation;

import java.util.*;

/**
 * Manages the firing and receiving of events.
 * <p/>
 * <p>Any event passed to {@link #publishEvent} will be passed through to "interested" listeners.
 * <p/>
 * <p>Event listeners can register to receive events via
 * {@link #registerListener(String, com.atlassian.interviews.eventsystem.InterviewEventListener)}
 */
public class DefaultEventManager implements EventManager {
    private Map<String, InterviewEventListener> listeners = new HashMap<String, InterviewEventListener>();
    private Map<Class<? extends InterviewEvent>, List<InterviewEventListener>> listenersByClass = new HashMap<Class<? extends InterviewEvent>, List<InterviewEventListener>>();
    private Set<InterviewEventListener> listenersToAllEvent = new HashSet<>();

    public void publishEvent(InterviewEvent event) {
        if (event == null) {
            System.err.println("Null event fired?");
            return;
        }

        sendEventTo(event, calculateListeners(event.getClass()));
        sendEventTo(event, listenersToAllEvent);
    }

    private Collection calculateListeners(Class eventClass) {
        return (Collection) listenersByClass.get(eventClass);
    }

    public void registerListener(String listenerKey, InterviewEventListener listener) {
        if (listenerKey == null || listenerKey.equals(""))
            throw new IllegalArgumentException("Key for the listener must not be null: " + listenerKey);
        //SelfDescribing
        if (listener == null)
            throw new IllegalArgumentException("The listener must not be null: " + listener);

        if (listeners.containsKey(listenerKey))
            unregisterListener(listenerKey);

        Class[] classes = listener.getHandledEventClasses();
        if (classes.length == 0) {
            listenersToAllEvent.add(listener);
        } else {
            for (int i = 0; i < classes.length; i++)
                addToListenerList(classes[i], listener);
        }
        listeners.put(listenerKey, listener);
    }

    public void unregisterListener(String listenerKey) {
        InterviewEventListener listener = (InterviewEventListener) listeners.get(listenerKey);

        if (listener == null) {
            return;
        }

        for (Iterator it = listenersByClass.values().iterator(); it.hasNext(); ) {
            List list = (List) it.next();
            list.remove(listener);
        }
        listenersToAllEvent.remove(listener);

        listeners.remove(listenerKey);
    }

    private void sendEventTo(InterviewEvent event, Collection listeners) {
        if (listeners == null || listeners.size() == 0)
            return;

        for (Iterator it = listeners.iterator(); it.hasNext(); ) {
            InterviewEventListener eventListener = (InterviewEventListener) it.next();
            eventListener.handleEvent(event);
        }
    }

    private void addToListenerList(Class<? extends InterviewEvent> aClass, InterviewEventListener listener) {
        List<InterviewEventListener> listeners = listenersByClass.putIfAbsent(aClass, new ArrayList<InterviewEventListener>());
        if (listeners == null) {
            listenersByClass.get(aClass).add(listener);
        } else {
            listeners.add(listener);
        }
    }

    public Map getListeners() {
        return listeners;
    }
}
