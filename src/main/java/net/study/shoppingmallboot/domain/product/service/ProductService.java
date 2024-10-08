package net.study.shoppingmallboot.domain.product.service;

import net.study.shoppingmallboot.domain.product.vo.Product;
import net.study.shoppingmallboot.domain.product.vo.ProductStatus;
import net.study.shoppingmallboot.domain.util.vo.Search;

import java.util.List;


public interface ProductService {
	void addProduct (Product productVO);

	void updateProduct (Product productVO);

	void deleteProductByName (String prodName);

	Product getProductByProdNo(int productNo);
	
	List<ProductStatus> getProductWithStatusList (Search search);

	int getAllProductCount (Search search);

	List<String> getProductNameList(String searchKeyword);
}