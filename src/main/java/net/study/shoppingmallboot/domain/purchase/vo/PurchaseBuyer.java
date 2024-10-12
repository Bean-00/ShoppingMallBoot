package net.study.shoppingmallboot.domain.purchase.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("statusText")
    public String getStatusText() {
        return PurchaseStatus.getTextByCode(this.tranCode);
    }

}
