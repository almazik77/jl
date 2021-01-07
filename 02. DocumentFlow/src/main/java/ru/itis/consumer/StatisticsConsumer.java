package ru.itis.consumer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.itis.model.Message;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class StatisticsConsumer {
    private final static String EXCHANGE_NAME = "main_info";
    private final static String EXCHANGE_TYPE = "fanout";
    private final Channel channel;

    public StatisticsConsumer() {
        Gson gson = new Gson();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "");
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                Message infoMessage = gson.fromJson(new String(message.getBody()), Message.class);

                System.out.println("statistics " + infoMessage);
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
