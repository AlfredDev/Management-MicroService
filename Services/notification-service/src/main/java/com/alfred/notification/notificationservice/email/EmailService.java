package com.alfred.notification.notificationservice.email;

import com.alfred.notification.notificationservice.kafka.appintment.AppointmentStatus;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import static com.alfred.notification.notificationservice.email.EmailTemplates.APPOINTMENT_CONFIRMATION;
import static com.alfred.notification.notificationservice.email.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public  void  sendAppointmentConfirmationEmail(
            String destination,
            String patientName,
            LocalDateTime date,
            AppointmentStatus status,
            String notes,
            Double price,
            String reference
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED, UTF_8.name());
        mimeMessageHelper.setFrom("alfred-coontact@gmail.com");
        log.info(patientName);
        final String templateName = APPOINTMENT_CONFIRMATION.getTemplate();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("patientName", patientName);
        variables.put("amount", price);
        variables.put("orderReference", reference);
        variables.put("appointmentDate",date);
        variables.put("status", status);
        variables.put("notes", notes);

        Context context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(APPOINTMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);

            mimeMessageHelper.setTo(destination);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destination, templateName));
        } catch (MessagingException e) {
            log.warn("WARNING - Cannot send Email to {} ", destination);
        }
    }

    @Async
    public void sendPaymentSuccessEmail(
            String destination,
            String patientName,
            BigDecimal amount
    ) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED, UTF_8.name());
        mimeMessageHelper.setFrom("alfred-coontact@gmail.com");
        log.info(patientName);
        final String templateName = PAYMENT_CONFIRMATION.getTemplate();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("patientName", patientName);
        variables.put("amount", amount);
        variables.put("orderReference", "Appointment");


        Context context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);

            mimeMessageHelper.setTo(destination);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destination, templateName));
        } catch (MessagingException e) {
            log.warn("WARNING - Cannot send Email to {} ", destination);
        }
    }
}
