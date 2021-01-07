package ru.itis.consumer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.itis.model.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class DownloadFilesConsumer {
    private final static String EXCHANGE_NAME = "main_info";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String DEST_FOLDER = "files/downloaded/";
    private final Channel channel;

    public DownloadFilesConsumer() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Gson gson = new Gson();
        try {
            Connection connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.basicQos(3);
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "");
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                Message infoMessage = gson.fromJson(new String(message.getBody()), Message.class);
                {
                    infoMessage.getFilesPath().forEach(this::downloadFile);
                }
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void downloadFile(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
            File output = new File("downloaded/" + UUID.randomUUID().toString() + fileUrl.substring(fileUrl.lastIndexOf(".")));
            if (!output.getParentFile().exists())
                output.getParentFile().mkdirs();
            if (!output.exists())
                output.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(output);
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) {
        new DownloadFilesConsumer().downloadFile("https://www.bgoperator.ru/pr_img/1000512/20120217/2317047/spravka.pdf");
    }
}
