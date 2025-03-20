package vlad_zuev.message_transport;

import vlad_zuev.message_transport.brocker.MessageBroker;
import vlad_zuev.message_transport.consumer.MessageConsumerTask;
import vlad_zuev.message_transport.model.MessageFactory;
import vlad_zuev.message_transport.producer.MessageProducerTask;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int brokerSize = 15;
        MessageBroker messageBroker = new MessageBroker(brokerSize);
        MessageFactory messageFactory = new MessageFactory();

        Thread writeMessage1 = new Thread(new MessageProducerTask(messageBroker, messageFactory));
        Thread writeMessage2 = new Thread(new MessageProducerTask(messageBroker, messageFactory));
        Thread writeMessage3 = new Thread(new MessageProducerTask(messageBroker, messageFactory));

        Thread readMessage1 = new Thread(new MessageConsumerTask(messageBroker));
        Thread readMessage2 = new Thread(new MessageConsumerTask(messageBroker));
        Thread readMessage3 = new Thread(new MessageConsumerTask(messageBroker));

        startThreads(writeMessage1, writeMessage2, writeMessage3, readMessage1, readMessage2, readMessage3);
    }

    private static void startThreads(final Thread... threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }
}
