package com.example.crash.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserLoginPostRequestBody(@NotEmpty String username, @NotEmpty String password) {
}
