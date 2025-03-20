package vlad_zuev.message_transport.brocker;

import vlad_zuev.message_transport.model.Message;

import java.util.ArrayDeque;
import java.util.Queue;

public class MessageBroker {
    private Queue<Message> messages;
    private int maxMessage;

    public MessageBroker(int maxMessage) {
        this.messages = new ArrayDeque<>(maxMessage);
        this.maxMessage = maxMessage;
    }

    public synchronized void produce(Message message) {
        while (messages.size() >= maxMessage) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.messages.add(message);
        super.notifyAll();
    }

    public synchronized Message consume() {
        while (messages.isEmpty()) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message poll = this.messages.poll();
        super.notifyAll();
        return poll;
    }
}
