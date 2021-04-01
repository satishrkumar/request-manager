package net.pay.you.back.request.manager.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Builder
@Data
@ToString
public class Email {
    private String from;
    private String to;
    private String name;
    private String subject;
    private String content;
    private Map<String, String> model;

}
