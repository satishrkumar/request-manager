package net.pay.you.back.request.manager.comm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestPayload {
    LoanRequest loanRequest;
}
