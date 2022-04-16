package com.amr.project.model.dto.report;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
