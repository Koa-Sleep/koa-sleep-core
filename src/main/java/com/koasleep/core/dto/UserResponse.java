package com.koasleep.core.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserResponse {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID id;
    private String email;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String fullName;
    private String displayName;
}