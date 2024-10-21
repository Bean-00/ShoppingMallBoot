package net.study.shoppingmallboot.domain.user.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.study.shoppingmallboot.domain.user.service.UserService;
import net.study.shoppingmallboot.domain.user.vo.User;
import net.study.shoppingmallboot.domain.util.vo.Search;
import net.study.shoppingmallboot.domain.util.vo.SessionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @Value("${config.display-count}")
    int displayCount;

    @PostMapping("/")
    public ResponseEntity<Void> addUser(@RequestBody User user) throws Exception {
        userService.addUser(user);

        return ResponseEntity.ok().build();
    }

    @Value("${config.page-num-size}")
    int pageNumSize;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) throws Exception {

        User user = userService.getUserByUserId(userId);

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable String userId, @RequestBody User user) throws Exception {
        user.setUserId(userId);
        userService.updateUser(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user, HttpSession session, HttpServletResponse response) throws Exception {
        User loginUser = userService.loginUser(user);

        if (Objects.nonNull(user)) {

            final String sessionId = session.getId();

            session.setAttribute("loginUser", loginUser);
            SessionUtil.addSession(sessionId, session);
            Cookie cookie = SessionUtil.createSessionCookie(sessionId);
            response.addCookie(cookie);
        }

        return ResponseEntity.ok().body(loginUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        SessionUtil.removeSession(session.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login-user")
    public ResponseEntity<User> loginUser(HttpServletRequest request) {
       return ResponseEntity.ok().body(SessionUtil.getLoginUser(request.getCookies()).orElse(null));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Map<String, Object>> listUser(@RequestParam(name="currentPage", required = false, defaultValue = "1") int currentPage,
                                                        @RequestParam(name= "searchKeyword", required = false) String searchKeyword,
                                                        @RequestParam(name= "searchCondition", required = false) String searchCondition) throws Exception {

        Search search = new Search();
        search.setSearchCondition(searchCondition);
        search.setSearchKeyword(searchKeyword);
        search.setCurrentPage(currentPage);
        search.setDisplayCount(this.displayCount);
        search.setPageNumSize(this.pageNumSize);

        List<User> userList = userService.getUserList(search);
        int totalCount = userService.getTotalUserCount(search);

        Map<String, Object> response = new HashMap<>();

        response.put("userList", userList);
        response.put("totalCount", totalCount);

        return ResponseEntity.ok(response);
    }
}

