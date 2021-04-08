package net.pay.you.back.request.manager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "docVersion")
public class DocVersion {
    public static final String SEQUENCE_NAME = "docversion_sequence";
    @Id
    private Long id;
    private String documentVersion;
    private String mimeType;
}
