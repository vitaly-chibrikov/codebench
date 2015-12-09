package events;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Created by v.chibrikov on 07.01.2015.
 */
public class EventSourceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void fireTest() {
        EventSource source = new EventSource();
        source.subscribe(new MessageEventListener());
        final String message = "Hello!";
        source.fire(new MessageEvent(message));
        assertTrue(outContent.toString().contains(message));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
