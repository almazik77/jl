package ru.itis.producer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.model.Message;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {

    private final static String EXCHANGE_NAME = "main_info";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        Gson gson = new Gson();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);


            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String[] s = scanner.nextLine().trim().split(" ");
                Message message = Message.builder().name(s[0] + " " + s[1] + " " + s[2]).email(s[3]).filesPath(Arrays.asList(s[4].split(",").clone())).build();
                String info = gson.toJson(message);
                channel.basicPublish(EXCHANGE_NAME, "", null, info.getBytes());
            }


        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static byte[][] convertToBytes(String[] strings) {
        byte[][] data = new byte[strings.length][];
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            data[i] = string.getBytes(Charset.defaultCharset());
        }
        return data;
    }
}
