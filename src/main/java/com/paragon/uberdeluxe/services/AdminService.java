package com.paragon.uberdeluxe.services;

import com.paragon.uberdeluxe.data.dto.request.InviteAdminRequest;
import com.paragon.uberdeluxe.data.dto.response.ApiResponse;

import java.util.Set;

public interface AdminService {

    ApiResponse sendInviteRequest(Set<InviteAdminRequest> inviteAdminRequestSet);
}
