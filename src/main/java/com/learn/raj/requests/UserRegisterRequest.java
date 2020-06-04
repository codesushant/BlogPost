package com.learn.raj.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @NotEmpty
    @JsonProperty
    private String username;
    @NotEmpty
    @JsonProperty
    private String password;
    @NotEmpty
    @JsonProperty
    private String email;
}