package net.pay.you.back.request.manager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAgreement {
    private MarketingPreferences marketingPreferences;
    private ContactPreferences contactPreferences;
    private boolean activateOneTouch;
    private boolean marketingCommNotif;
    private boolean usrAgrAndPrivPolicy;
}
