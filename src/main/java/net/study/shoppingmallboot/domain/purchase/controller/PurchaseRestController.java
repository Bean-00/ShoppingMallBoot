package net.study.shoppingmallboot.domain.purchase.controller;

import lombok.RequiredArgsConstructor;
import net.study.shoppingmallboot.domain.product.service.ProductService;
import net.study.shoppingmallboot.domain.purchase.service.PurchaseService;
import net.study.shoppingmallboot.domain.purchase.vo.Purchase;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseRestController {
    private final PurchaseService purchaseService;

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
    public ResponseEntity<Map<String, Object>> listPurchase(@RequestParam(name="currentPage", required = false, defaultValue = "1") int currentPage,
                                                            @RequestParam(name="buyerId", required = false, defaultValue = "") String buyerId) {

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

    @GetMapping("/{tranNo}")
    public ResponseEntity<Purchase> getPurchase(@PathVariable int tranNo) {

        Purchase purchase = purchaseService.getPurchase(tranNo);

        return ResponseEntity.ok().body(purchase);
    }

    @PatchMapping({"", "/"})
    public ResponseEntity<Void> updateTranCode(@RequestParam(name = "tranNo") int tranNo) {

        purchaseService.updateTransCode(tranNo);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{tranNo}")
    public ResponseEntity<Void> updatePurchase(@PathVariable int tranNo, @RequestBody Purchase purchase) {
        purchase.setTranNo(tranNo);
        purchaseService.updatePurchase(purchase);

        return ResponseEntity.ok().build();
    }


}
