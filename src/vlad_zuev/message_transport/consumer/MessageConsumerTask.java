package vlad_zuev.message_transport.consumer;

import vlad_zuev.message_transport.brocker.MessageBroker;
import vlad_zuev.message_transport.model.Message;

public class MessageConsumerTask implements Runnable {

    MessageBroker messageBroker;

    public MessageConsumerTask(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message consume = messageBroker.consume();
            System.out.println(String.format("Consumed is %s", consume));
        }
    }
}
