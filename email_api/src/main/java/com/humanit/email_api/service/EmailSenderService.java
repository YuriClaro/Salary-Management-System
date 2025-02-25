package com.humanit.email_api.service;

import com.humanit.email_api.exception.email.EmailProcessingException;
import com.humanit.email_api.exception.email.EmailSendException;
import jakarta.mail.MessagingException;

public interface EmailSenderService {
    void sendEmailAllSalaries(String receiver, String subject, String text, byte[] attachment)
            throws MessagingException, EmailSendException, EmailProcessingException;
    void sendEmailAllSalariesByStatus(String receiver, String subject, String text, byte[] attachment)
            throws MessagingException, EmailSendException, EmailProcessingException;
    void sendEmailAllSalariesBetweenDates(String receiver, String subject, String text, byte[] attachment)
            throws MessagingException, EmailSendException, EmailProcessingException;
    void sendEmailAllSalariesByCollaboratorId(String receiver, String subject, String text, byte[] attachment)
            throws MessagingException, EmailSendException, EmailProcessingException;
    void sendEmailAllOwnSalaries(String receiver, String subject, String text, byte[] attachment)
            throws MessagingException, EmailSendException, EmailProcessingException;
}
