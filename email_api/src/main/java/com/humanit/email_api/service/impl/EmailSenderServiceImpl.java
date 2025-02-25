package com.humanit.email_api.service.impl;

import com.humanit.email_api.exception.email.EmailProcessingException;
import com.humanit.email_api.exception.email.EmailSendException;
import com.humanit.email_api.model.Email;
import com.humanit.email_api.repository.EmailRepository;
import com.humanit.email_api.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.humanit.email_api.enumerator.EmailStatus.SENT;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendEmailAllSalaries(
            String receiver, String subject, String text, byte[] attachment)
            throws MessagingException, EmailSendException, EmailProcessingException {
        log.info("Preparing to send email to {}", receiver);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        try {
            log.info("Configuring MimeMessage for receiver: {}", receiver);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment("all_salaries_report.xlsx", new ByteArrayResource(attachment));

            Email emailModel =
                    new Email(sender, receiver, subject, text, attachment, LocalDateTime.now(), SENT, 0L);
            emailRepository.save(emailModel);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}: {}", receiver, e.getMessage());
            throw new EmailSendException();
        } catch (Exception e) {
            log.error("An unexpected error occurred while sending email to {}: {}", receiver, e.getMessage());
            throw new EmailProcessingException();
        }
    }

    @Override
    public void sendEmailAllSalariesByStatus(String receiver, String subject, String text, byte[] attachment)
            throws MessagingException, EmailSendException, EmailProcessingException {
        log.info("Preparing to send email to {}", receiver);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        try {
            log.info("Configuring MimeMessage for receiver: {}", receiver);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment("all_salaries_by_status_report.xlsx", new ByteArrayResource(attachment));

            Email emailModel =
                    new Email(sender, receiver, subject, text, attachment, LocalDateTime.now(), SENT, 0L);
            emailRepository.save(emailModel);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}: {}", receiver, e.getMessage());
            throw new EmailSendException();
        } catch (Exception e) {
            log.error("An unexpected error occurred while sending email to {}: {}", receiver, e.getMessage());
            throw new EmailProcessingException();
        }
    }

    @Override
    public void sendEmailAllSalariesBetweenDates(String receiver, String subject, String text, byte[] attachment)
            throws MessagingException, EmailSendException, EmailProcessingException {
        log.info("Preparing to send email to {}", receiver);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        try {
            log.info("Configuring MimeMessage for receiver: {}", receiver);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment(
                    "all_salaries_between_dates_report.xlsx", new ByteArrayResource(attachment));

            Email emailModel =
                    new Email(sender, receiver, subject, text, attachment, LocalDateTime.now(), SENT, 0L);
            emailRepository.save(emailModel);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}: {}", receiver, e.getMessage());
            throw new EmailSendException();
        } catch (Exception e) {
            log.error("An unexpected error occurred while sending email to {}: {}", receiver, e.getMessage());
            throw new EmailProcessingException();
        }
    }

    @Override
    public void sendEmailAllSalariesByCollaboratorId(String receiver, String subject, String text, byte[] attachment)
            throws MessagingException, EmailSendException, EmailProcessingException {
        log.info("Preparing to send email to {}", receiver);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        try {
            log.info("Configuring MimeMessage for receiver: {}", receiver);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment(
                    "all_salaries_by_collaborator_report.xlsx", new ByteArrayResource(attachment));

            Email emailModel =
                    new Email(sender, receiver, subject, text, attachment, LocalDateTime.now(), SENT, 0L);
            emailRepository.save(emailModel);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}: {}", receiver, e.getMessage());
            throw new EmailSendException();
        } catch (Exception e) {
            log.error("An unexpected error occurred while sending email to {}: {}", receiver, e.getMessage());
            throw new EmailProcessingException();
        }
    }

    @Override
    public void sendEmailAllOwnSalaries(String receiver, String subject, String text, byte[] attachment)
            throws MessagingException, EmailSendException, EmailProcessingException {
        log.info("Preparing to send email to {}", receiver);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        try {
            log.info("Configuring MimeMessage for receiver: {}", receiver);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment(
                    "your_salaries_report.xlsx", new ByteArrayResource(attachment));

            Email emailModel =
                    new Email(sender, receiver, subject, text, attachment, LocalDateTime.now(), SENT, 0L);
            emailRepository.save(emailModel);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}: {}", receiver, e.getMessage());
            throw new EmailSendException();
        } catch (Exception e) {
            log.error("An unexpected error occurred while sending email to {}: {}", receiver, e.getMessage());
            throw new EmailProcessingException();
        }
    }
}
