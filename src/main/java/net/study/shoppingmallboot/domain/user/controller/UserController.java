package net.study.shoppingmallboot.domain.user.controller;


import jakarta.servlet.http.HttpSession;
import net.study.shoppingmallboot.domain.user.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Value("${config.display-count}")
    //@Value("#{commonProperties['pageUnit'] ?: 3}")
    int displayCount;

    @Value("${config.page-num-size}")
    //@Value("#{commonProperties['pageSize'] ?: 2}")
    int pageNumSize;

    @RequestMapping("/addUserView")
    public String addUserView() {
        System.out.println("/addUserView");

        return "redirect:/user/addUserView.jsp";
    }

    @RequestMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) throws Exception {
        System.out.println("/addUser");

        userService.addUser(user);

        return "redirect:/user/loginView.jsp";
    }

    @RequestMapping("/getUser")
    public String getUser(@RequestParam("userId") String userId, Model model) throws Exception {
        System.out.println("/getUser");

        User user = userService.getUserByUserId(userId);

        model.addAttribute("user", user);

        return "forward:/user/getUser.jsp";
    }

    @RequestMapping("/updateUserView")
    public String userUpdateView(@RequestParam("userId") String userId, Model model) throws Exception {
        System.out.println("/updateUserView");

        User user = userService.getUserByUserId(userId);

        model.addAttribute("user", user);

        return "forward:/user/updateUser.jsp";
    }

    @RequestMapping("updateUser")
    public String updateUser(@ModelAttribute("user") User user, HttpSession session) throws Exception {
        System.out.println("/updateUser");

        userService.updateUser(user);

        String sessionId = ((User) session.getAttribute("user")).getUserId();
        if (sessionId.equals(user.getUserId())) {
            session.setAttribute("user", user);
        }

        return "redirect:/user/getUser?userId=" + user.getUserId();
    }

    @RequestMapping("/loginView")
    public String loginView() {
        System.out.println("/loginView");

        return "forward:/user/loginView.jsp";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("user") User user, HttpSession session) throws Exception {
        System.out.println("/login");

        User loginUser = userService.loginUser(user);

        if (Objects.isNull(loginUser)) {
            return "forward:/user/fail-loginView.jsp";
        }
        session.setAttribute("user", loginUser);
        return "redirect:/index.jsp";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("/logout");

        session.invalidate();

        return "redirect:/index.jsp";
    }

    @RequestMapping("/checkDuplication")
    public String checkDuplication(@RequestParam("userId") String userId, Model model) throws Exception {
        boolean result = userService.checkDuplication(userId);

        model.addAttribute("result", Boolean.valueOf(result));
        model.addAttribute("userId", userId);

        return "forward:/user/checkDuplication.jsp";
    }

    @RequestMapping("/listUser")
    public String listUser(@ModelAttribute("search") Search search, Model model) throws Exception {
        System.out.println("/listUser");


        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        List<User> userList = userService.getUserList(search);
        int totalCount = userService.getTotalUserCount(search);

        PageMaker pageInfo = new PageMaker(search.getCurrentPage(), totalCount, search.getPageNumSize(), search.getDisplayCount());

        model.addAttribute("userList", userList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("search", search);

        return "forward:/user/listUser.jsp";
    }
}

