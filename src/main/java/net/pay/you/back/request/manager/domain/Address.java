package net.pay.you.back.request.manager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String flatNumber;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String state;
    private String country;
    private String postcode;

}
