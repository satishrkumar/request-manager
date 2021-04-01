package net.pay.you.back.request.manager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "userdata")
public class User {
    public static final String SEQUENCE_NAME = "userdata_sequence";
    @Id
    private Long id;
    @Indexed(unique = true)
    private String emailId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    private String nationality;
    private String phoneNumber;
    private Address address;

}
