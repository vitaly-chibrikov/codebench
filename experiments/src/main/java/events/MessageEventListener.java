package events;

/**
 * Created by v.chibrikov on 07.01.2015.
 */
public class MessageEventListener implements EventListener<MessageEvent> {
    @Override
    public void handle(MessageEvent event) {
        System.out.print(event.getMessage());
    }

    @Override
    public Class<MessageEvent> getEventClass() {
        return MessageEvent.class;
    }
}
