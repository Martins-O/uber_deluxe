package com.paragon.uberdeluxe.services;

import com.paragon.uberdeluxe.data.dto.request.EmailNotificationRequest;
import com.paragon.uberdeluxe.data.dto.request.InviteAdminRequest;
import com.paragon.uberdeluxe.data.dto.response.ApiResponse;
import com.paragon.uberdeluxe.data.models.Admin;
import com.paragon.uberdeluxe.data.models.Recipient;
import com.paragon.uberdeluxe.data.repositories.AdminRepository;
import com.paragon.uberdeluxe.exception.BusinessLogicException;
import com.paragon.uberdeluxe.notification.MailService;
import com.paragon.uberdeluxe.utils.AppUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service

public class AdminServiceImpl implements AdminService{

    private final AdminRepository repository;
    private final MailService mailService;

    @Autowired
    public AdminServiceImpl(AdminRepository repository, MailService mailService) {
        this.repository = repository;
        this.mailService = mailService;
    }

    @Override
    public ApiResponse sendInviteRequest(Set<InviteAdminRequest> inviteAdminRequestSet) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        var recipient = inviteAdminRequestSet.stream()
                .map(inviteAdminRequest -> createAdminProfile(inviteAdminRequest))
                .map(inviteAdminRequest -> new Recipient(inviteAdminRequest.getUserDetails().getName(),
                        inviteAdminRequest.getUserDetails().getEmail()))
                .toList();
        request.getTo().addAll(recipient);

        String adminMail = AppUtilities.getAdminMailTemplate();
        request.setHtmlContent(String.format(adminMail, "admin", AppUtilities.generateVerificationLink(0L)));
        var response = mailService.sendHtmlMail(request);
        if (response!=null) return ApiResponse.builder()
                .message("invute request sent")
                .status(HttpStatus.OK.value()).build();
        throw new BusinessLogicException("invite requests sending failed");
    }

    private Admin createAdminProfile(InviteAdminRequest inviteAdminRequest){
        Admin admin = new Admin();
        admin.getUserDetails().setName(inviteAdminRequest.getName());
        admin.getUserDetails().setEmail(inviteAdminRequest.getEmail());
        repository.save(admin);
        return admin;
    }
}
