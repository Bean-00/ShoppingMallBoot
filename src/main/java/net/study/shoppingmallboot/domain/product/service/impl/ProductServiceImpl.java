package net.study.shoppingmallboot.domain.product.service.impl;

import lombok.RequiredArgsConstructor;
import net.study.shoppingmallboot.domain.product.dao.ProductMapper;
import net.study.shoppingmallboot.domain.product.service.ProductService;
import net.study.shoppingmallboot.domain.product.vo.Product;
import net.study.shoppingmallboot.domain.product.vo.ProductStatus;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productServiceImpl")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;


    @Override
	@Transactional
    public void addProduct(Product productVO) {
        productMapper.insertProduct(productVO);
        if (!productVO.getUploadedFiles().isEmpty()) {
            productMapper.insertProductFiles(productVO);
        }
    }

    @Override
    public void updateProduct(Product productVO) {
        productMapper.updateProduct(productVO);
    }

    @Override
    public void deleteProductByName(String productName) {
        productMapper.deleteProduct(productName);
    }

    @Override
    public Product getProductByProdNo(int productNo) {
        Product product = productMapper.getProductByProdNo(productNo);
        return product;
    }


    @Override
    public List<ProductStatus> getProductWithStatusList(Search search) {
        return productMapper.getProductWithStatusList(search);
    }

    @Override
    public int getAllProductCount(Search search) {
        return productMapper.getTotalProductCount(search);
    }

    @Override
    public List<String> getProductNameList(String searchKeyword) {
        return productMapper.getProductNameList(searchKeyword);
    }

}
