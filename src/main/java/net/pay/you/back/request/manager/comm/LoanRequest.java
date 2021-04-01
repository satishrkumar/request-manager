package net.pay.you.back.request.manager.comm;

import lombok.*;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest extends BaseLoan{

    private String reasonForBorrow;

}
