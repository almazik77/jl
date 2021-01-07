package ru.itis.consumer;

import com.google.gson.Gson;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;
import ru.itis.model.Message;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class CertificateCreateConsumer {
    private final static String EXCHANGE_NAME = "main_info";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String DEST_FOLDER = "files/email/";
    private final static String SRC = "files/С места работы.pdf";
    private final Channel channel;
    private final CertificateCreateProducer certificateCreateProducer;

    public CertificateCreateConsumer() {
        certificateCreateProducer = new CertificateCreateProducer();
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
                    String fileName = DEST_FOLDER + infoMessage.getName() + ".pdf";
                    boolean status = createFile(fileName, infoMessage);

                    if (status) {
                        certificateCreateProducer.sendEmail(infoMessage.getEmail(), System.getProperty("user.dir") + File.separator + fileName);
                        channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                    } else {
                        System.err.println("FAILED");
                        channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                    }
                }
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private boolean createFile(String fileName, Message message) {
        File file = new File(fileName);
        file.getParentFile().getParentFile().mkdirs();

        try {
            PdfReader pdfReader = new PdfReader(SRC);
            PdfWriter pdfWriter = new PdfWriter(fileName);
            PdfDocument doc = new PdfDocument(pdfReader, pdfWriter);
            PdfAcroForm form = PdfAcroForm.getAcroForm(doc, true);
            Map<String, PdfFormField> fields = form.getFormFields();
            fields.get("name").setValue(message.getName());
            fields.get("date").setValue(LocalDateTime.now().toString());
            doc.close();
        } catch (IOException e) {
            System.out.println("FAILED");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new CertificateCreateConsumer().createFile("files/created/asdasd.pdf", Message.builder().name("asd").email("musin.almaz33@gmail.com").build());
    }

    private static class CertificateCreateProducer {
        private final static String EXCHANGE_NAME = "certificate_email";
        private final static String EXCHANGE_TYPE = "direct";
        private final Channel channel;

        private CertificateCreateProducer() {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            try {
                Connection connection = connectionFactory.newConnection();
                channel = connection.createChannel();
                channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);


            } catch (IOException | TimeoutException e) {
                throw new IllegalArgumentException(e);
            }
        }

        private void sendEmail(String email, String filepath) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", email);
            jsonObject.put("filePath", filepath);
            try {
                channel.basicPublish(EXCHANGE_NAME, "email", null, jsonObject.toString().getBytes());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }

        }
    }
}
