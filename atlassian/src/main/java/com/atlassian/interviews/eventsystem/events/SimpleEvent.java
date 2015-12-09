package com.atlassian.interviews.eventsystem.events;

import com.atlassian.interviews.eventsystem.InterviewEvent;

public class SimpleEvent implements InterviewEvent
{
    private Object source;

    public SimpleEvent(Object source)
    {
        this.source = source;
    }

    public Object getSource()
    {
        return source;
    }
}