package com.paragon.uberdeluxe.data.dto.request;

import com.paragon.uberdeluxe.data.models.Recipient;
import com.paragon.uberdeluxe.data.models.Sender;

import java.util.List;

public class EmailNotificationRequest {

    private final Sender sender = new Sender("uber_deluxe", "noreply@uberdeluxe.net");
    private List<Recipient> to;
    private final String subject = "Welcome to uber_deluxe";
    private final String htmlContent = "<p> Hello, Welcome to uber deluxe</p>";
}
