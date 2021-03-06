package ru.itis.consumer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DeleteDownloadConsumer {
    public static void main(String[] args) {
        new DeleteDownloadConsumer();
    }
    private final static String EXCHANGE_NAME = "delete";
    private final static String EXCHANGE_TYPE = "topic";
    private final Channel channel;

    public DeleteDownloadConsumer() {
        Gson gson = new Gson();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "*.downloaded");
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                String filePath = message.toString();

                archiveAndSave(filePath);
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }


    private void archiveAndSave(String filename) {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("archive/downloaded/" + filename.substring(filename.lastIndexOf("/")) + ".tar");

            ZipOutputStream zout = new ZipOutputStream(fout);

            // Создание объекта File object архивируемой директории
            File fileSource = new File(filename);

            addDirectory(zout, fileSource);

            // Закрываем ZipOutputStream
            zout.close();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

    private void addDirectory(ZipOutputStream zout, File fileSource)
            throws Exception {

        FileInputStream fis = new FileInputStream(fileSource);

        zout.putNextEntry(new ZipEntry(fileSource.getPath()));

        byte[] buffer = new byte[4048];
        int length;
        while ((length = fis.read(buffer)) > 0)
            zout.write(buffer, 0, length);
        // Закрываем ZipOutputStream и InputStream
        zout.closeEntry();
        fis.close();
    }
}
