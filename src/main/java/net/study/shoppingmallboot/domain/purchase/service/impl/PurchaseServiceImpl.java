package net.study.shoppingmallboot.domain.purchase.service.impl;

import net.study.shoppingmallboot.domain.purchase.dao.PurchaseMapper;
import net.study.shoppingmallboot.domain.purchase.service.PurchaseService;
import net.study.shoppingmallboot.domain.purchase.vo.Purchase;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getPurchaseList(Search search) {
        return purchaseMapper.getPurchaseList(search);
    }

    @Override
    public int getAllPurchaseCount(String buyerId) {
        return purchaseMapper.getPurchaseTotalCount(buyerId);
    }

    @Override
    public int checkPurchaseLog(String prodNo) {
        return purchaseMapper.checkPurchaseLog(prodNo);
    }

    @Override
    public void deletePurchase(int tranNo) {
        purchaseMapper.deletePurchase(tranNo);
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseMapper.addPurchase(purchase);
    }

    @Override
    public void updateTransCode(int prodNo) {
        purchaseMapper.updateTransCode(prodNo);
    }

    @Override
    public Purchase getPurchase(int tranNo) {
        return purchaseMapper.getPurchase(tranNo);
    }

}
