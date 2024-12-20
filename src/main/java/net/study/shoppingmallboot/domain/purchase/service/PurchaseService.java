package net.study.shoppingmallboot.domain.purchase.service;

import net.study.shoppingmallboot.domain.purchase.vo.Purchase;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PurchaseService {

    List<Purchase> getPurchaseList(Search search);

    int getAllPurchaseCount(String buyerId);

    int checkPurchaseLog(String prodNo);

    void deletePurchase(int tranNo);

    void addPurchase(Purchase purchase);

    void updateTransCode(int tranNo);

    Purchase getPurchase(int tranNo);

    void updatePurchase(Purchase purchase);
}
