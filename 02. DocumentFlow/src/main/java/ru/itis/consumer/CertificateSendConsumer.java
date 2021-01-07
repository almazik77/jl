package ru.itis.consumer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

public class CertificateSendConsumer {
    private final static String EXCHANGE_NAME = "certificate_email";
    private final static String EXCHANGE_TYPE = "direct";

    public CertificateSendConsumer() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Gson gson = new Gson();
        Object monitor = new Object();
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(1);
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "email");
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                Message infoMessage = gson.fromJson(new String(message.getBody()), Message.class);
                System.out.println("sending message to " + infoMessage.email + ", " + "file " + infoMessage.filePath);
                sendMessage(infoMessage);
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                synchronized (monitor) {
                    monitor.notify();
                }
            };
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
            });
            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @SneakyThrows
    private void sendMessage(Message message) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("musin.almaz33@gmail.com", "rjhjkm1rjhjkm1");
            }
        });
        MimeMessage emailMessage = new MimeMessage(session);
        emailMessage.setFrom(new InternetAddress("musin.almaz33@gmail.com"));
        emailMessage.setRecipients(
                javax.mail.Message.RecipientType.TO, InternetAddress.parse(message.email));
        emailMessage.setSubject("Mail Subject");

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        File file = new File(message.filePath);
        while (!file.exists());
        attachmentBodyPart.attachFile(file);


        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(attachmentBodyPart);

        emailMessage.setContent(multipart);

        Transport.send(emailMessage);
    }

    public static void main(String[] args) {
        new CertificateSendConsumer();
    }

    @Data
    @Builder
    private static class Message {
        String email;
        String filePath;
    }
}
