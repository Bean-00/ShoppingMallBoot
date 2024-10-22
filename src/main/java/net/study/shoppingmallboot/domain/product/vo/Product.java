package net.study.shoppingmallboot.domain.product.vo;

import lombok.*;
import net.study.shoppingmallboot.domain.file.vo.FileInfo;

import java.sql.Date;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String manuDate;
    private int price;
    private String prodDetail;
    private String prodName;
    private int prodNo;
    private Date regDate;
    private List<FileInfo> uploadedFiles;

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

}