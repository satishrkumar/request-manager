package net.pay.you.back.request.manager.domain.enums;

public enum RepaymentFrequency {

    Daily("Daily"), Monthly ("Monthly"), Quarterly("Quarterly"), Annualy("Annualy");
    public final String value;

    private RepaymentFrequency(String value) {
        this.value = value;
    }
}
