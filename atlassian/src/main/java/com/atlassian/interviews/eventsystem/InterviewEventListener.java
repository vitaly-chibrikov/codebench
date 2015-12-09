package com.atlassian.interviews.eventsystem;

/**
 * A simple event listener.
 */
public interface InterviewEventListener
{
    /**
     * Perform some action as a response to an Interview event. The EventManager will
     * ensure that this is only called if the class of the event type is one we
     * have declared that we handle in getHandledEventClasses.
     *
     * @param event some event triggered within an Interview
     */
    void handleEvent(InterviewEvent event);

    /**
     * Determine which event classes this listener is interested in.
     * 
     * <p>The EventManager performs rudimentary filtering of events by their class. If
     * you want to receive only a subset of events passing through the system, return
     * an array of the Classes you wish to listen for from this method.
     *
     * <p>For the sake of efficiency, only exact class matches are performed. Sub/superclassing
     * is not taken into account.
     *
     * @return An array of the event classes that this event listener is interested in,
     *         or an empty array if the listener should receive all events. <b>Must not</b>
     *         return null.
     */
    Class[] getHandledEventClasses();
}
