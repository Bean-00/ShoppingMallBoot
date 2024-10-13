package net.study.shoppingmallboot.domain.purchase.service;

import net.study.shoppingmallboot.domain.purchase.vo.Purchase;
import net.study.shoppingmallboot.domain.util.vo.Search;

import java.util.List;

public interface PurchaseService {

    List<Purchase> getPurchaseList(Search search);

    int getAllPurchaseCount(String buyerId);

    int checkPurchaseLog(String prodNo);

    void deletePurchase(int tranNo);

    void addPurchase(Purchase purchase);

    void updateTransCode(int prodNo);

    Purchase getPurchase(int tranNo);
}
