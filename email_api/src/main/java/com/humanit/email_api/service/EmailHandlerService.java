package com.humanit.email_api.service;

import com.humanit.email_api.dto.EmailWithAttachmentDTO;
import com.humanit.email_api.exception.email.EmailProcessingException;
import com.humanit.email_api.exception.email.EmailSendException;
import jakarta.mail.MessagingException;

public interface EmailHandlerService {
    void handleEmailRequestAllSalaries(EmailWithAttachmentDTO emailWithAttachmentDTO)
            throws MessagingException, EmailSendException, EmailProcessingException;
    void handleEmailRequestAllSalariesByStatus(EmailWithAttachmentDTO emailWithAttachmentDTO)
            throws MessagingException, EmailSendException, EmailProcessingException;
    void handleEmailRequestAllSalariesBetweenDates(EmailWithAttachmentDTO emailWithAttachmentDTO)
            throws MessagingException, EmailSendException, EmailProcessingException;
    void handleEmailRequestAllSalariesByCollaboratorId(EmailWithAttachmentDTO emailWithAttachmentDTO)
            throws MessagingException, EmailSendException, EmailProcessingException;
    void handleEmailRequestAllOwnSalaries(EmailWithAttachmentDTO emailWithAttachmentDTO)
            throws MessagingException, EmailSendException, EmailProcessingException;
}
