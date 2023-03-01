package com.paragon.uberdeluxe.notification;

import com.paragon.uberdeluxe.data.dto.request.EmailNotificationRequest;

public interface MailService {

    String sendHtmlMail(EmailNotificationRequest request);
}
