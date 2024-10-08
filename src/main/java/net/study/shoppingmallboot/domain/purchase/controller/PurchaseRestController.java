package net.study.shoppingmallboot.domain.purchase.controller;

import net.study.shoppingmallboot.domain.product.service.ProductService;
import net.study.shoppingmallboot.domain.product.vo.Product;
import net.study.shoppingmallboot.domain.purchase.service.PurchaseService;
import net.study.shoppingmallboot.domain.purchase.vo.Purchase;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/purchases")
public class PurchaseRestController {
    @Autowired
    @Qualifier("purchaseServiceImpl")
    PurchaseService purchaseService;

    @Autowired
    @Qualifier("productServiceImpl")
    ProductService productService;

    @Value("${config.display-count}")
    int displayCount;

    @Value("${config.page-num-size}")
    int pageNumSize;

    @PostMapping({ "", "/"})
    public ResponseEntity<Void> addPurchase(@RequestBody Purchase purchase) {
        purchaseService.addPurchase(purchase);

        return ResponseEntity.ok().build();
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Map<String, Object>> listPurchase(@RequestParam(name="buyerId") String buyerId,
                                                            @RequestParam(name="currentPage", required = false, defaultValue = "1") int currentPage) {

        Search search = new Search();
        search.setCurrentPage(currentPage);
        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);
        search.setBuyerId(buyerId);

        int totalCount = purchaseService.getAllPurchaseCount(buyerId);
        List<Purchase> purchaseList = purchaseService.getPurchaseList(search);

        Map<String, Object> response = new HashMap<>();

        response.put("totalCount", totalCount);
        response.put("purchaseList", purchaseList);

        return ResponseEntity.ok(response);
    }

    @PatchMapping({"", "/"})
    public ResponseEntity<Void> updateTranCode(@RequestParam(name = "prodNo") int prodNo) {

        purchaseService.updateTransCode(prodNo);

        return ResponseEntity.ok().build();
    }


}
