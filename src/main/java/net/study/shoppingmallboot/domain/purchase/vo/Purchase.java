package net.study.shoppingmallboot.domain.purchase.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.study.shoppingmallboot.domain.product.vo.Product;
import net.study.shoppingmallboot.domain.purchase.vo.constant.PurchaseStatus;
import net.study.shoppingmallboot.domain.user.vo.User;

import java.sql.Date;

@Getter
@Setter
public class Purchase {
    private User buyer;
    private String dlvyAddr;
    private Date dlvyDate;
    private String dlvyRequest;
    private Date orderDate;
    private String paymentOption;
    private Product purchaseProd;
    private String receiverName;
    private String receiverPhone;
    private PurchaseStatus status;
    private int tranNo;
    private int rowNum;
    private int prodNo;
    private String buyerId;

    public void setStatus(String code) {
        this.status = PurchaseStatus.getByCode(code);
    }

    @JsonProperty("statusText")
    public String getStatusText() {
        return status.getText();
    }
}