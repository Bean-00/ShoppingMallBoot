package net.study.shoppingmallboot.domain.purchase.controller;

import jakarta.servlet.http.HttpServletRequest;
import net.study.shoppingmallboot.domain.product.service.ProductService;
import net.study.shoppingmallboot.domain.product.vo.Product;
import net.study.shoppingmallboot.domain.purchase.service.PurchaseService;
import net.study.shoppingmallboot.domain.purchase.vo.Purchase;
import net.study.shoppingmallboot.domain.purchase.vo.constant.PurchaseStatus;
import net.study.shoppingmallboot.domain.user.vo.User;
import net.study.shoppingmallboot.domain.util.vo.PageMaker;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
    @Autowired
    @Qualifier("purchaseServiceImpl")
    PurchaseService purchaseService;

    @Autowired
    @Qualifier("productServiceImpl")
    ProductService productService;

    @Value("${config.display-count}")
    //@Value("#{commonProperties['pageUnit'] ?: 3}")
    int displayCount;

    @Value("${config.page-num-size}")
    //@Value("#{commonProperties['pageSize'] ?: 2}")
    int pageNumSize;

    @RequestMapping("/addPurchaseView")
    public ModelAndView addPurchaseView(@ModelAttribute("prodNo") String prodNo, HttpServletRequest request, Model model) {
        System.out.println("/addPurchaseView");

        Product product = productService.getProductByProdNo(Integer.parseInt(prodNo));
        User user = (User)request.getSession().getAttribute("user");

        model.addAttribute("user", user);
        model.addAttribute("product", product);

        return new ModelAndView("forward:/purchase/addPurchaseView.jsp");
    }

    @RequestMapping("/addPurchase")
    public ModelAndView addPurchase(@ModelAttribute Purchase purchase, @ModelAttribute("prodNo") String prodNo, HttpServletRequest request, Model model) {
        System.out.println("/addPurchase");

        if (purchaseService.checkPurchaseLog(prodNo) != 0)
            return new ModelAndView("redirect:/product/listProduct");

        User user = (User) request.getSession().getAttribute("user");
        purchase.setDlvyDate(Date.valueOf(request.getParameter("dlvyDate")));
        purchase.setOrderDate(Date.valueOf(LocalDate.now()));
        purchase.setPurchaseProd(productService.getProductByProdNo(Integer.parseInt(prodNo)));
        purchase.setStatus(PurchaseStatus.PURCHASED.getCode());
        purchase.setBuyer(user);

        purchaseService.addPurchase(purchase);

        model.addAttribute("purchase", purchase);

        return new ModelAndView("forward:/purchase/addPurchase.jsp");
    }

    @RequestMapping("/listPurchase")
    public ModelAndView listPurchase(@ModelAttribute("search") Search search, @ModelAttribute("buyerId") String buyerId, Model model) {
        System.out.println("/listPurchase");


        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        int totalCount = purchaseService.getAllPurchaseCount(buyerId);

        List<Purchase> purchaseList = purchaseService.getPurchaseList(search);

        model.addAttribute("pageInfo", new PageMaker(search.getCurrentPage(), totalCount, search.getPageNumSize(), search.getDisplayCount()));
        model.addAttribute("list", purchaseList);

        return new ModelAndView("forward:/purchase/listPurchase.jsp");

    }

    @RequestMapping("/updateTranCode")
    public ModelAndView updateTranCode(@ModelAttribute("prodNo") String prodNo, @ModelAttribute("page") String pageNum,
                                            @ModelAttribute("role") String role, HttpServletRequest request) {
        System.out.println("/updateTranCode");

        int page = Integer.parseInt(pageNum);

        purchaseService.updateTransCode(Integer.parseInt(prodNo));
        if (Objects.isNull(role)) {
            return new ModelAndView("redirect:/product/listProduct?menu=manage&page=" + page);
        }
        if (role.equals("Buyer")){
            StringBuilder path = new StringBuilder("redirect:/purchase/listPurchase?buyerId=");
            path.append(request.getParameter("buyerId"));
            path.append("&page=");
            path.append(page);
            return new ModelAndView(path.toString());
        }
        return new ModelAndView("redirect:/product/listProduct?menu=manage&page=" + page);
    }


}
