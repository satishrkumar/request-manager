package net.pay.you.back.request.manager.domain;

import lombok.*;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Email {
    private String from;
    private String to;
    private String name;
    private String subject;
    private String content;
    private Map<String, String> model;

}
