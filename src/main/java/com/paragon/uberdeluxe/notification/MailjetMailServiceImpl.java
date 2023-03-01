package com.paragon.uberdeluxe.notification;

import com.paragon.uberdeluxe.config.MailConfig;
import com.paragon.uberdeluxe.data.dto.request.EmailNotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class MailjetMailServiceImpl implements MailService{

    private final MailConfig mailConfig;

    @Override
    public String sendHtmlMail(EmailNotificationRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", mailConfig.getApiKey());
        HttpEntity<EmailNotificationRequest> requestHttpEntity =
                new HttpEntity<>(request, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(mailConfig.getMailUrl(), requestHttpEntity, String.class);
        log.info("res->{}", response);
        return response.getBody();
    }
}
