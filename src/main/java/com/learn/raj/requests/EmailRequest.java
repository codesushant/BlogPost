package com.learn.raj.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailRequest {
    String from;
    String to;
    String subject;
    String content;
}
