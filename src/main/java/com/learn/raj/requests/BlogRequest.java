package com.learn.raj.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogRequest {
    @JsonProperty
    @NotEmpty
    private String authorName;
    @JsonProperty
    @NotEmpty
    private String title;
    @JsonProperty
    @NotEmpty
    private String content;
}
