package net.pay.you.back.request.manager.domain;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

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
