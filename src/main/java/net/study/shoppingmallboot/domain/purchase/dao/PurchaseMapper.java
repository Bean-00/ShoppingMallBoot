package net.study.shoppingmallboot.domain.purchase.dao;

import net.study.shoppingmallboot.domain.purchase.vo.Purchase;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseMapper {
    void addPurchase(Purchase purchaseVO);

    List<Purchase> getPurchaseList(Search search);

    int checkPurchaseLog(String prodNo);

    int getPurchaseTotalCount(String buyerId);

    void updateTransCode(int prodNo);

    void deletePurchase(int prodNo);

    Purchase getPurchase(int tranNo);
}
