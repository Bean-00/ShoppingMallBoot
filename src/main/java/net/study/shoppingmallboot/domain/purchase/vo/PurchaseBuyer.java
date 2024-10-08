package net.study.shoppingmallboot.domain.purchase.vo;

import lombok.Getter;
import lombok.Setter;
import net.study.shoppingmallboot.domain.purchase.vo.constant.PurchaseStatus;

@Getter
@Setter
public class PurchaseBuyer {
    private int rowNum;
    private String buyerId;
    private String buyerName;
    private String buyerPhone;
    private String tranCode;
    private String prodNo;

    public String getTranText() {
        return PurchaseStatus.getTextByCode(this.tranCode);
    }

}
