package com.atlassian.interviews.eventsystem;

import com.atlassian.interviews.eventsystem.events.SimpleEvent;
import com.atlassian.interviews.eventsystem.events.SubEvent;
import com.atlassian.interviews.eventsystem.impl.DefaultEventManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDefaultEventManager {
    private EventManager eventManager = new DefaultEventManager();

    @Test
    public void testPublishNullEvent() {
        eventManager.publishEvent(null);
    }

    @Test
    public void testRegisterListenerAndPublishEvent() {
        MockEventListener mockEventListener = new MockEventListener(new Class[]{SimpleEvent.class});
        eventManager.registerListener("some.key", mockEventListener);
        eventManager.publishEvent(new SimpleEvent(this));
        assertTrue(mockEventListener.isCalled());
    }

    @Test
    public void testListenerWithoutMatchingEventClass() {
        MockEventListener mockEventListener = new MockEventListener(new Class[]{SubEvent.class});
        eventManager.registerListener("some.key", mockEventListener);
        eventManager.publishEvent(new SimpleEvent(this));
        assertFalse(mockEventListener.isCalled());
    }

    @Test
    public void testUnregisterListener() {
        MockEventListener mockEventListener = new MockEventListener(new Class[]{SimpleEvent.class});
        MockEventListener mockEventListener2 = new MockEventListener(new Class[]{SimpleEvent.class});

        eventManager.registerListener("some.key", mockEventListener);
        eventManager.registerListener("another.key", mockEventListener2);
        eventManager.unregisterListener("some.key");

        eventManager.publishEvent(new SimpleEvent(this));
        assertFalse(mockEventListener.isCalled());
        assertTrue(mockEventListener2.isCalled());
    }


    /**
     * Check that registering and unregistering listeners behaves properly.
     */
    @Test
    public void testRemoveNonexistentListener() {
        DefaultEventManager dem = (DefaultEventManager) eventManager;
        assertEquals(0, dem.getListeners().size());
        eventManager.registerListener("some.key", new MockEventListener(new Class[]{SimpleEvent.class}));
        assertEquals(1, dem.getListeners().size());
        eventManager.unregisterListener("this.key.is.not.registered");
        assertEquals(1, dem.getListeners().size());
        eventManager.unregisterListener("some.key");
        assertEquals(0, dem.getListeners().size());
    }

    /**
     * Registering duplicate keys on different listeners should only fire the most recently added.
     */
    @Test
    public void testDuplicateKeysForListeners() {
        MockEventListener mockEventListener = new MockEventListener(new Class[]{SimpleEvent.class});
        MockEventListener mockEventListener2 = new MockEventListener(new Class[]{SimpleEvent.class});

        eventManager.registerListener("some.key", mockEventListener);
        eventManager.registerListener("some.key", mockEventListener2);

        eventManager.publishEvent(new SimpleEvent(this));

        assertTrue(mockEventListener2.isCalled());
        assertFalse(mockEventListener.isCalled());

        mockEventListener.resetCalled();
        mockEventListener2.resetCalled();

        eventManager.unregisterListener("some.key");
        eventManager.publishEvent(new SimpleEvent(this));

        assertFalse(mockEventListener2.isCalled());
        assertFalse(mockEventListener.isCalled());
    }

    /**
     * Attempting to register a null with a valid key should result in an illegal argument exception
     */
    @Test
    public void testAddValidKeyWithNullListener() {
        try {
            eventManager.registerListener("bogus.key", null);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testEmptyClassArray() {
        MockEventListener mockEventListener = new MockEventListener(new Class[]{});
        eventManager.registerListener("some.key", mockEventListener);

        MockEventListener mockEventListenerSimple = new MockEventListener(new Class[]{SimpleEvent.class});
        eventManager.registerListener("some.key.simple", mockEventListenerSimple);

        eventManager.publishEvent(new SimpleEvent(this));
        assertTrue(mockEventListener.isCalled());
    }
}
