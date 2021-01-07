package ru.itis.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class DeleteDocumentsProducer {
    private final static String EXCHANGE_NAME = "delete";
    private final static String EXCHANGE_TYPE = "topic";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine().trim();
                if (s.startsWith("downloaded/")) {
                    channel.basicPublish(EXCHANGE_NAME, "delete.downloaded", null, s.getBytes());
                } else if (s.startsWith("files/created")) {
                    channel.basicPublish(EXCHANGE_NAME, "delete.created", null, s.getBytes());
                }
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
