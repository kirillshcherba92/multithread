package vlad_zuev.message_transport.model;

public class MessageFactory {
    private static final int messIndex = 1;
    private String templateMessage = " Message#%d";


    private int nextMessageIndex;

    public MessageFactory() {
        this.nextMessageIndex = messIndex;
    }

    public Message create() {
        return new Message(String.format(templateMessage, findAndIncrementNExtMessageIndex()));
    }

    private synchronized int findAndIncrementNExtMessageIndex() {
        return nextMessageIndex++;
    }
}
