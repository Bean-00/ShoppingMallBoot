package net.study.shoppingmallboot.domain.product.vo;

import lombok.*;

import java.sql.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String fileName;
    private String manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private int prodNo;
    private Date regDate;

    public void setManuDate(String manuDate) {
        String[] strArr = manuDate.split("-");
        StringBuilder sb = new StringBuilder();
        sb.append(strArr[0]);
        if (strArr.length == 3) {
            sb.append(strArr[1]);
            sb.append(strArr[2]);
        }

        this.manuDate = sb.toString();
    }

    // Override
    public String toString() {
        return "ProductVO : [fileName]" + fileName
                + "[manuDate]" + manuDate + "[price]" + price + "[prodDetail]" + prodDetail
                + "[prodName]" + prodName + "[prodNo]" + prodNo;
    }

}