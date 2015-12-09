package events;

/**
 * Created by v.chibrikov on 07.01.2015.
 */
public class MessageEvent implements Event {
    private final String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
