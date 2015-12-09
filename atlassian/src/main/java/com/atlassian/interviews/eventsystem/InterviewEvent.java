package com.atlassian.interviews.eventsystem;

/**
 * A marker interface for all interview events.
 */
public interface InterviewEvent
{
    Object getSource();
}