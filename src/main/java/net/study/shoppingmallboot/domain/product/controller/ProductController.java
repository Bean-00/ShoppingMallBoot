package net.study.shoppingmallboot.domain.product.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.study.shoppingmallboot.domain.product.service.ProductService;
import net.study.shoppingmallboot.domain.product.vo.Product;
import net.study.shoppingmallboot.domain.product.vo.ProductStatus;
import net.study.shoppingmallboot.domain.purchase.service.PurchaseService;
import net.study.shoppingmallboot.domain.user.vo.User;
import net.study.shoppingmallboot.domain.util.vo.PageMaker;
import net.study.shoppingmallboot.domain.util.vo.Search;
import net.study.shoppingmallboot.domain.util.vo.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.StringJoiner;

//@Controller
@RequestMapping("/product/*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final PurchaseService purchaseService;

    @Value("${config.display-count}")
    int displayCount;

    @Value("${config.page-num-size}")
    int pageNumSize;

    @RequestMapping("/addProductView")
    public String addProductView() {
        System.out.println("/addProductView");

        return "redirect:/product/addProductView.jsp";
    }

    @RequestMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") Product product) {
        System.out.println("/addProduct");

        productService.addProduct(product);

        return "redirect:/product/listProduct?menu=manage";
    }

    @RequestMapping("/getProduct")
    public String getProduct(@ModelAttribute("prodNo") String prodNo, Model model, HttpServletRequest request, HttpServletResponse response) {

        String cookieValue = SessionUtil.getCookieValue(request.getCookies(), SessionUtil.HISTORY_NAME)
                .map(str -> {
                    StringJoiner sj = new StringJoiner("|");
                    sj.add(str);
                    sj.add(prodNo);
                    return sj.toString();
                })
                .orElse(prodNo);

        Cookie cookie = SessionUtil.createCookie(SessionUtil.HISTORY_NAME, cookieValue);

        Product product = productService.getProductByProdNo(Integer.parseInt(prodNo));

        response.addCookie(cookie);

        User user = (User) request.getSession().getAttribute("user");

        model.addAttribute("product", product);
        model.addAttribute("user", user);

        return "forward:/product/getProduct.jsp";
    }

    @RequestMapping("/listProduct")
    public String listProduct(@ModelAttribute("search") Search search, Model model, HttpServletRequest request) {
        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        User user = (User) request.getSession().getAttribute("user");

        List<ProductStatus> productStatusList = productService.getProductWithStatusList(search);
        int totalCount = productService.getAllProductCount(search);

        PageMaker pageInfo = new PageMaker(search.getCurrentPage(), totalCount, search.getPageNumSize(), search.getDisplayCount());

        model.addAttribute("list", productStatusList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("search", search);
        model.addAttribute("user", user);

        return "forward:/product/listProduct.jsp";
    }

    @RequestMapping("/updateProductView")
    public String updateProductView(@ModelAttribute("prodNo") String prodNo, Model model) {
        Search search = new Search();
        Product product = productService.getProductByProdNo(Integer.parseInt(prodNo));
        search.setSearchKeyword(String.valueOf(product.getProdNo()));
        search.setSearchCondition("0");
        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);
        String status = productService.getProductWithStatusList(search).get(0).getStatus().getCode();

        model.addAttribute("product", product);
        model.addAttribute("status", status);

        return "forward:/product/updateProductView.jsp";
    }

//    @RequestMapping("/updateProduct")
//    public String updateProduct(HttpServletRequest request) throws Exception {
//        Product product = parseMultipartFormdata(request);
//        productService.updateProduct(product);
//
//        return "redirect:/product/getProduct?prodNo=" + product.getProdNo();
//    }

//    private Product parseMultipartFormdata(HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException {
//        if (FileUpload.isMultipartContent(request)) {
//            //TODO: property 로 관리
//            String filePath = "/Users/bean/Development/ShoppingMall/src/main/webapp/images/uploadFiles";
//
//            DiskFileUpload fileUpload = new DiskFileUpload();
//            fileUpload.setSizeMax(1024 * 1024 * 10);
//            fileUpload.setSizeThreshold(1024 * 100);
//
//            if (request.getContentLength() < fileUpload.getSizeMax()){
//                Product product = new Product();
//
//                StringTokenizer token = null;
//
//                List<FileItem> fileItemList = fileUpload.parseRequest(request);
//
//                for (FileItem fileItem : fileItemList) {
//                    if (fileItem.isFormField()) {
//                        if (fileItem.getFieldName().equals("manuDate")) {
//                            String manuDate = fileItem.getString("UTF-8");
//                            if (manuDate.contains("-")) {
//                                token = new StringTokenizer(manuDate, "-");
//                                manuDate = token.nextToken() + token.nextToken() + token.nextToken();
//                            }
//                            product.setManuDate(manuDate);
//                        } else if (fileItem.getFieldName().equals("prodName")) {
//                            product.setProdName(fileItem.getString("utf-8"));
//                        } else if (fileItem.getFieldName().equals("prodDetail")) {
//                            product.setProdDetail(fileItem.getString("utf-8"));
//                        } else if (fileItem.getFieldName().equals("price")){
//                            product.setPrice(Integer.parseInt(fileItem.getString("utf-8")));
//                        } else if (fileItem.getFieldName().equals("prodNo")) {
//                            product.setProdNo(Integer.parseInt(fileItem.getString("utf-8")));
//                        }
//                    } else {
//                        if (fileItem.getSize() > 0) {
//                            int idx = fileItem.getName().lastIndexOf("/");
//                            if (idx == -1) {
//                                idx = fileItem.getName().lastIndexOf("\\");
//                            }
//                            String fileName = fileItem.getName().substring(idx + 1);
//                            product.setFileName(fileName);
//                            try {
//                                File uploadFile = new File(filePath, fileName);
//                                fileItem.write(uploadFile);
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        } else {
//                            product.setFileName("/Users/bean/Development/ShoppingMall/src/main/webapp/images/empty.GIF");
//                        }
//                    }
//                }
//
//                return product;
//            } else {
//                //TODO: response 로 줘야 함
//                int overSize = (request.getContentLength() / 1000000);
//                throw new RuntimeException("파일의 크기는 1MB까지 입니다. 올리신 파일 용량은 " + overSize + "MB입니다");
//            }
//        } else {
//            throw new RuntimeException("인코딩 타입이 multipart/form-data가 아닙니다");
//        }
//    }
}
