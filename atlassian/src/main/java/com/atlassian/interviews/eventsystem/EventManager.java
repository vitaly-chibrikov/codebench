package com.atlassian.interviews.eventsystem;

public interface EventManager
{
    /**
     * Publish an InterviewEvent that will be consumed by any listener which has
     * registered to receive it.
     *
     * @param event the event to publish
     */
    void publishEvent(InterviewEvent event);

    /**
     * Register a listener to receive InterviewEvents. If you register a listener with the
     * same key as an existing listener, the previous listener with that key will be unregistered.
     *
     * @param listenerKey A unique key for this listener. If the listener is a plugin module, use the
     *        modules complete key
     * @param listener The listener that is being registered
     */
    void registerListener(String listenerKey, InterviewEventListener listener);

    /**
     * Unregister a listener so that it will no longer receive events. If no listener is
     * registered under this key, nothing will happen.
     *
     * @param listenerKey the key under which the listener was registered.
     */
    void unregisterListener(String listenerKey);
}
