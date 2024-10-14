package net.study.shoppingmallboot.domain.product.service.impl;

import net.study.shoppingmallboot.domain.product.dao.ProductMapper;
import net.study.shoppingmallboot.domain.product.service.ProductService;
import net.study.shoppingmallboot.domain.product.vo.Product;
import net.study.shoppingmallboot.domain.product.vo.ProductStatus;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public void addProduct(Product productVO) {
		productMapper.insertProduct(productVO);
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
        return productMapper.getProductByProdNo(productNo);
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
