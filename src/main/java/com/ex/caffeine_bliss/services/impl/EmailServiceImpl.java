package com.ex.caffeine_bliss.services.impl;

import com.ex.caffeine_bliss.DTOs.ReceiptEmailDTO;
import com.ex.caffeine_bliss.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Async
    public void sendReceiptEmail(String to, String subject, ReceiptEmailDTO data, byte[] headerImg, byte[] footerImg) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setSubject(subject);

            // Prepare Thymeleaf context
            Context context = new Context();
            context.setVariable("receiptId", data.getReceiptId());
            context.setVariable("orderId", data.getOrderId());
            context.setVariable("customerName", data.getCustomerName());
            context.setVariable("customerMobile", data.getCustomerMobile());
            context.setVariable("createdAt", data.getCreatedAt());
            context.setVariable("totalAmount", data.getTotalAmount());
            context.setVariable("items", data.getItems());

            String htmlContent = templateEngine.process("receipt-email", context);
            helper.setText(htmlContent, true);

            // Attach inline images (header and footer)
            helper.addInline("header", new ByteArrayResource(headerImg), "image/png");
            helper.addInline("footer", new ByteArrayResource(footerImg), "image/png");

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new com.ex.caffeine_bliss.exceptions.MessagingException("Email Failed!");
        }
    }

    @Async
    public void sendResetPasswordEmail(String to, String resetLink) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setSubject("Reset Your Password - Caffeine Bliss");

            Context context = new Context();
            context.setVariable("resetLink", resetLink);
            String htmlContent = templateEngine.process("forgot-password-email", context);
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new com.ex.caffeine_bliss.exceptions.MessagingException("Email Failed!");
        }
    }

}
