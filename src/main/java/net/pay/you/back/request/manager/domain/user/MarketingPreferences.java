package net.pay.you.back.request.manager.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketingPreferences {
    private boolean byEmail;
    private boolean byTelephone;
}
