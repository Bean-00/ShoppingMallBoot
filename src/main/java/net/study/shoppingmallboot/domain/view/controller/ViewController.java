package net.study.shoppingmallboot.domain.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/"})
public class ViewController {

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    @GetMapping("/user-management")
    public String userManagement() {
        return "user/user-management";
    }

    @GetMapping("/my-information")
    public String myInformation() {
        return "user/myInfo";
    }

    @GetMapping("/my-purchase-log")
    public String myPurchaseLog() {
        return "purchase/purchase-log";
    }

    @GetMapping("/purchase-product")
    public String purchaseProduct() {
        return "product/purchase-product";
    }

    @GetMapping("/add-new-product")
    public String addNewProduct() {
        return "product/add-new-product";
    }
    
    @GetMapping("/purchase-management")
    public String purchaseManagement() {
        return "purchase/purchase-management";
    }
    

}
