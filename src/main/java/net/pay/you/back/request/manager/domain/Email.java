package net.pay.you.back.request.manager.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Email implements Serializable {
    static final long serialVersionUID = 0x1122334455667788L;
    private String from;
    private String to;
    private String name;
    private String subject;
    private String content;
    private Map<String, String> model;

}
