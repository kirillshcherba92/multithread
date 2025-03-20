package vlad_zuev.message_transport.producer;

import vlad_zuev.message_transport.brocker.MessageBroker;
import vlad_zuev.message_transport.model.Message;
import vlad_zuev.message_transport.model.MessageFactory;

public class MessageProducerTask implements Runnable {

    MessageBroker messageBroker;
    MessageFactory messageFactory;

    public MessageProducerTask(MessageBroker messageBroker, MessageFactory messageFactory) {
        this.messageBroker = messageBroker;
        this.messageFactory = messageFactory;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Message message = messageFactory.create();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            messageBroker.produce(message);
            System.out.println(String.format("Message \"%s\" is produced", message));
        }
    }
}
