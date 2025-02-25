package com.humanit.email_api.service.impl;

import com.humanit.email_api.dto.EmailWithAttachmentDTO;
import com.humanit.email_api.exception.email.EmailProcessingException;
import com.humanit.email_api.exception.email.EmailSendException;
import com.humanit.email_api.service.EmailHandlerService;
import com.humanit.email_api.service.EmailSenderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailHandlerServiceImpl implements EmailHandlerService {
    private final EmailSenderService emailSenderService;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.topic.email_all_salaries}",
            groupId = "${spring.kafka.consumer.group.email}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleEmailRequestAllSalaries(EmailWithAttachmentDTO emailWithAttachmentDTO)
            throws MessagingException, EmailSendException, EmailProcessingException {
        log.info("Received email request for all salaries from Kafka topic to send to {}",
                emailWithAttachmentDTO.getReceiver());
        emailSenderService.sendEmailAllSalaries(emailWithAttachmentDTO.getReceiver(),
                "\uD83D\uDCCA Comprehensive All Salary Report Available for Review",
                "Hello,\n\n"
                        + "We hope this email finds you well!\n\n"
                        + "Attached, you’ll find the updated comprehensive salary report, ready for your review. "
                        + "If you have any questions or require further assistance, please don’t hesitate to reach out.\n\n"
                        + "**Summary of the attached report:**\n"
                        + "- A complete list of salaries.\n"
                        + "- Organized and up-to-date information including IDs, dates, and salary details.\n\n"
                        + "Thank you for your attention, and we wish you a productive day!\n\n"
                        + "Best regards,\n"
                        + "HumanIT",
                emailWithAttachmentDTO.getExcelFile());
        log.info("Email sent successfully to {}", emailWithAttachmentDTO.getReceiver());
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.topic.email_all_salaries_status}",
            groupId = "${spring.kafka.consumer.group.email}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleEmailRequestAllSalariesByStatus(EmailWithAttachmentDTO emailWithAttachmentDTO)
            throws MessagingException, EmailSendException, EmailProcessingException {
        log.info("Received email request for salary report by status from Kafka topic to send to {}",
                emailWithAttachmentDTO.getReceiver());
        emailSenderService.sendEmailAllSalariesByStatus(emailWithAttachmentDTO.getReceiver(),
                "\uD83D\uDCCA Comprehensive Salary by status Report Available for Review",
                "Hello,\n\n"
                        + "We hope this email finds you well!\n\n"
                        + "Attached, you’ll find the updated comprehensive salary report, ready for your review. "
                        + "If you have any questions or require further assistance, please don’t hesitate to reach out.\n\n"
                        + "**Summary of the attached report:**\n"
                        + "- A complete list of all salaries by selected status.\n"
                        + "- Organized and up-to-date information including IDs, dates, and salary details.\n\n"
                        + "Thank you for your attention, and we wish you a productive day!\n\n"
                        + "Best regards,\n"
                        + "HumanIT",
                emailWithAttachmentDTO.getExcelFile());
        log.info("Email sent successfully to {}", emailWithAttachmentDTO.getReceiver());
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.topic.email_all_salaries_dates}",
            groupId = "${spring.kafka.consumer.group.email}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleEmailRequestAllSalariesBetweenDates(EmailWithAttachmentDTO emailWithAttachmentDTO)
            throws MessagingException, EmailSendException, EmailProcessingException {
        log.info("Received email request for salary report between dates from Kafka topic to send to {}",
                emailWithAttachmentDTO.getReceiver());
        emailSenderService.sendEmailAllSalariesBetweenDates(emailWithAttachmentDTO.getReceiver(),
                "\uD83D\uDCCA Comprehensive Salary between dates Report Available for Review",
                "Hello,\n\n"
                        + "We hope this email finds you well!\n\n"
                        + "Attached, you’ll find the updated comprehensive salary report, ready for your review. "
                        + "If you have any questions or require further assistance, please don’t hesitate to reach out.\n\n"
                        + "**Summary of the attached report:**\n"
                        + "- A complete list of all salaries within the selected date range.\n"
                        + "- Organized and up-to-date information including IDs, dates, and salary details.\n\n"
                        + "Thank you for your attention, and we wish you a productive day!\n\n"
                        + "Best regards,\n"
                        + "HumanIT",
                emailWithAttachmentDTO.getExcelFile());
        log.info("Email sent successfully to {}", emailWithAttachmentDTO.getReceiver());
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.topic.email_salary_collaborator_id}",
            groupId = "${spring.kafka.consumer.group.email}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleEmailRequestAllSalariesByCollaboratorId(EmailWithAttachmentDTO emailWithAttachmentDTO)
            throws MessagingException, EmailSendException, EmailProcessingException {
        log.info("Received email request for salary report by collaborator from Kafka topic to send to {}",
                emailWithAttachmentDTO.getReceiver());
        emailSenderService.sendEmailAllSalariesByCollaboratorId(emailWithAttachmentDTO.getReceiver(),
                "\uD83D\uDCCA Comprehensive Salary between dates Report Available for Review",
                "Hello,\n\n"
                        + "We hope this email finds you well!\n\n"
                        + "Attached, you’ll find the updated comprehensive salary report, ready for your review. "
                        + "If you have any questions or require further assistance, please don’t hesitate to reach out.\n\n"
                        + "**Summary of the attached report:**\n"
                        + "- A complete list of all salaries by collaborator.\n"
                        + "- Organized and up-to-date information including IDs, dates, and salary details.\n\n"
                        + "Thank you for your attention, and we wish you a productive day!\n\n"
                        + "Best regards,\n"
                        + "HumanIT",
                emailWithAttachmentDTO.getExcelFile());
        log.info("Email sent successfully to {}", emailWithAttachmentDTO.getReceiver());
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.topic.email_all_own_salaries}",
            groupId = "${spring.kafka.consumer.group.email}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleEmailRequestAllOwnSalaries(EmailWithAttachmentDTO emailWithAttachmentDTO)
            throws MessagingException, EmailSendException, EmailProcessingException {
        log.info("Received email request for own user's salary report from Kafka topic to send to {}",
                emailWithAttachmentDTO.getReceiver());
        emailSenderService.sendEmailAllOwnSalaries(emailWithAttachmentDTO.getReceiver(),
                "\uD83D\uDCCA Your Comprehensive Salaries Report Available",
                "Hello,\n\n"
                        + "We hope this email finds you well!\n\n"
                        + "Attached, you’ll find your updated comprehensive salary report, ready for your review. "
                        + "If you have any questions or require further assistance, please don’t hesitate to reach out.\n\n"
                        + "**Summary of the attached report:**\n"
                        + "- A complete list of all your salaries by collaborator.\n"
                        + "- Organized and up-to-date information including IDs, dates, and salary details.\n\n"
                        + "Thank you for your attention, and we wish you a productive day!\n\n"
                        + "Best regards,\n"
                        + "HumanIT",
                emailWithAttachmentDTO.getExcelFile());
        log.info("Email sent successfully to {}", emailWithAttachmentDTO.getReceiver());
    }
}
