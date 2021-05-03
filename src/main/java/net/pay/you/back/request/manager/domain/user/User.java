package net.pay.you.back.request.manager.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String username;
    private String firstName;
    private String middleName;
    private String birthday;
    private String lastName;
    private String password;
    private String nationality;
    private String phone;
    private String flatnumber;
    private String address1;
    private String address2;
    private String town;
    private String country;
    private String postalcode;
    private UserAgreement userAgreement;
}
