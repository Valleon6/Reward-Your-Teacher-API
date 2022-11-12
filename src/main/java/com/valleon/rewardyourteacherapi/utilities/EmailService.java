package com.valleon.rewardyourteacherapi.utilities;

import com.valleon.rewardyourteacherapi.usecase.payload.request.EmailDetailsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.mail.internet.MimeMessage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${val.ekechukwu@gmail.com}")
    private String sender;

    public String sendSimpleMail(EmailDetailsRequest details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }


    public String sendMailWithAttachment(EmailDetailsRequest details) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        if (details.getAttachment() == null) {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        } else {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        }
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(details.getRecipient());
        mimeMessageHelper.setText(details.getMsgBody(), true);
        mimeMessageHelper.setSubject(details.getSubject());

        BufferedImage bufferedImage = null;
        InputStream inputStream;
        if (details.getAttachment() != null) {
            URLConnection openConnection = new URL(details.getAttachment()).openConnection();
            openConnection.addRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
            inputStream = openConnection.getInputStream();
            mimeMessageHelper.addAttachment("NIN.jpg", new ByteArrayResource(compressImage(inputStream)));
        }

        // Sending the mail
        javaMailSender.send(mimeMessage);
        return "Mail sent Successfully";
    }

    private static byte[] compressImage(InputStream imageInputStream) throws Exception {
        float imageQuality = 0.3f;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //Create the buffered image
        BufferedImage bufferedImage = ImageIO.read(imageInputStream);
        //Get image writers
        Iterator<ImageWriter> imageWriters = ImageIO.getImageWritersByFormatName("jpg");
        if (!imageWriters.hasNext())
            throw new IllegalStateException("Writers Not Found!!");
        ImageWriter imageWriter = (ImageWriter) imageWriters.next();
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
        imageWriter.setOutput(imageOutputStream);
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        //Set the compress quality metrics
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(imageQuality);
        //Created image
        imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
        byte[] compressImageBytes = outputStream.toByteArray();
        // close all streams
        imageInputStream.close();
        outputStream.close();
        imageOutputStream.close();
        imageWriter.dispose();
        return compressImageBytes;
    }

    public String buildVerificationEmail(String name, String link) {
        return EmailBody.emailVerificationBody(name, link);
    }

    public String WalletFundingEmail(String name, String amount) {
        return EmailBody.walletFundingEmailBody(name, amount);
    }
    public String sendFundsEmail(String teacherName,String StudentName, String amount)  {
        return EmailBody.sendFunds(teacherName,StudentName,amount);
    }
    public String receiveFundsEmail(String teacherName,String StudentName, String amount) {
        return EmailBody.ReceiveFunds(teacherName, StudentName, amount);
    }
}
