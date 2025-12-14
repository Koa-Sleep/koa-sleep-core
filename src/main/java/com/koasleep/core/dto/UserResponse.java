package com.koasleep.core.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class UserResponse {
    private UUID id;
    private String email;
    private String fullName;
    private String displayName;

}