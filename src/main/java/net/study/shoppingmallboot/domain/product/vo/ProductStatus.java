package net.study.shoppingmallboot.domain.product.vo;


import lombok.Getter;
import lombok.Setter;
import net.study.shoppingmallboot.domain.purchase.vo.constant.PurchaseStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class ProductStatus {

    private int prodNo;
    private String productName;
    private int price;
    private Date regDate;
    private PurchaseStatus status;
    private int rowNum;
    private String fileName;

    public String getTranText() {
        return status.getText();
    }

    public void setStatus(String code) {
        this.status = PurchaseStatus.getByCode(code);
    }
    public String getRegDateString() {
        if (Objects.isNull(this.regDate)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        return sdf.format(this.regDate);
    }
}
