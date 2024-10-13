package net.study.shoppingmallboot.domain.product.controller;

import lombok.RequiredArgsConstructor;
import net.study.shoppingmallboot.domain.product.service.ProductService;
import net.study.shoppingmallboot.domain.product.vo.Product;
import net.study.shoppingmallboot.domain.product.vo.ProductStatus;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {

    @Value("${config.product-display-count}")
    int displayCount;
    @Value("${config.product-page-num-size}")
    int pageNumSize;

    private final ProductService productService;

    @PostMapping({"", "/"})
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {

        productService.addProduct(product);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{prodNo}")
    public ResponseEntity<Product> getProduct(@PathVariable int prodNo) {

        Product product = productService.getProductByProdNo(prodNo);

        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/totalCount")
    public ResponseEntity<Integer> getProductTotalCount(@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage,
                                                        @RequestParam(name = "searchSorting", required = false, defaultValue = "0") String searchSorting,
                                                        @RequestParam(name = "searchKeyword", required = false) String searchKeyword,
                                                        @RequestParam(name = "searchCondition", required = false) String searchCondition,
                                                        @RequestParam(name = "searchLowPrice", required = false) String searchLowPrice,
                                                        @RequestParam(name = "searchHighPrice", required = false) String searchHighPrice) {

        Search search  = setSearchField(currentPage, searchSorting, searchKeyword, searchCondition, searchLowPrice, searchHighPrice);

        int totalCount = productService.getAllProductCount(search);

        return ResponseEntity.ok().body(totalCount);
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<ProductStatus>> getProductList(@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage,
                                                              @RequestParam(name = "searchSorting", required = false, defaultValue = "0") String searchSorting,
                                                              @RequestParam(name = "searchKeyword", required = false) String searchKeyword,
                                                              @RequestParam(name = "searchCondition", required = false) String searchCondition,
                                                              @RequestParam(name = "searchLowPrice", required = false) String searchLowPrice,
                                                              @RequestParam(name = "searchHighPrice", required = false) String searchHighPrice) {

        Search search  = setSearchField(currentPage, searchSorting, searchKeyword, searchCondition, searchLowPrice, searchHighPrice);

        List<ProductStatus> productStatusList = productService.getProductWithStatusList(search);

        return ResponseEntity.ok().body(productStatusList);
    }

    private Search setSearchField(@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage, @RequestParam(name = "searchSorting", required = false, defaultValue = "0") String searchSorting, @RequestParam(name = "searchKeyword", required = false) String searchKeyword, @RequestParam(name = "searchCondition", required = false) String searchCondition, @RequestParam(name = "searchLowPrice", required = false) String searchLowPrice, @RequestParam(name = "searchHighPrice", required = false) String searchHighPrice) {
        Search search = new Search();
        search.setSearchSorting(searchSorting);
        search.setSearchKeyword(searchKeyword);
        search.setSearchCondition(searchCondition);
        search.setSearchLowPrice(searchLowPrice);
        search.setSearchHighPrice(searchHighPrice);

        search.setCurrentPage(currentPage);

        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        return search;
    }

    @GetMapping("/name")
    public ResponseEntity<List<String>> listProductName(@RequestParam(name = "searchKeyword", required = false) String searchKeyword) {
        //ToDo: js 호출 시 encodeURIComponent 사용
        List<String> productNameList = productService.getProductNameList(searchKeyword);

        return ResponseEntity.ok(productNameList);
    }
}
