package com.atlassian.interviews.eventsystem;

class MockEventListener implements InterviewEventListener
{
    private boolean called;
    Class[] classes;
    public int count;

    public MockEventListener(Class[] classes)
    {
        this.classes = classes;
    }

    public void handleEvent(InterviewEvent event)
    {
        called = true;
        count++;
    }

    public void resetCalled()
    {
        called = false;
    }

    public boolean isCalled()
    {
        return called;
    }

    public Class[] getHandledEventClasses()
    {
        return classes;
    }
}