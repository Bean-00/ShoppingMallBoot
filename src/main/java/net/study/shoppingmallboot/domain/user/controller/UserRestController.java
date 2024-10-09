package net.study.shoppingmallboot.domain.user.controller;

import lombok.RequiredArgsConstructor;
import net.study.shoppingmallboot.domain.user.service.UserService;
import net.study.shoppingmallboot.domain.user.vo.User;
import net.study.shoppingmallboot.domain.util.vo.Search;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        userService.updateUser(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws Exception {

        User loginUser = userService.loginUser(user);

        return ResponseEntity.ok().body(loginUser);
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

