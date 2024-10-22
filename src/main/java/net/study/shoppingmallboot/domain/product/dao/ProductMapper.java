package net.study.shoppingmallboot.domain.product.dao;

import net.study.shoppingmallboot.domain.file.vo.FileInfo;
import net.study.shoppingmallboot.domain.product.vo.Product;
import net.study.shoppingmallboot.domain.product.vo.ProductStatus;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    int getTotalProductCount(Search search);

    void insertProduct(Product product);

    Product getProduct(int prodNo);

    List<ProductStatus> getProductWithStatusList (Search search);

    Product getProductByProdNo(int prodNo);

    void updateProduct(Product product);
    
    void deleteProduct(String prodName);

    List<String> getProductNameList(String searchKeyword);

    void insertProductFiles(Product product);
}