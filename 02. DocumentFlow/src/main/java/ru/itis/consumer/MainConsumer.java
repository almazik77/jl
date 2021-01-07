package ru.itis.consumer;

public class MainConsumer {
    public static void main(String[] args) {
        CertificateCreateConsumer certificateCreateConsumer = new CertificateCreateConsumer();
        StatisticsConsumer statisticsConsumer = new StatisticsConsumer();
        DownloadFilesConsumer downloadFilesConsumer = new DownloadFilesConsumer();
        CertificateSendConsumer certificateSendConsumer = new CertificateSendConsumer();


        DeleteDownloadConsumer deleteDownloadConsumer = new DeleteDownloadConsumer();
        DeleteCreateConsumer deleteCreateConsumer = new DeleteCreateConsumer();
    }
}
