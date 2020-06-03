package com.learn.raj.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogUpdateRequest {
    @JsonProperty
    private long blogId;
    @JsonProperty
    private String title;
    @JsonProperty
    private String content;
}
