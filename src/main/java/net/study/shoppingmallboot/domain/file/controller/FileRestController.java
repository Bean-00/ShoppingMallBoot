package net.study.shoppingmallboot.domain.file.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import net.study.shoppingmallboot.domain.file.service.FileService;
import net.study.shoppingmallboot.domain.file.vo.FileInfo;
import net.study.shoppingmallboot.domain.user.vo.User;
import net.study.shoppingmallboot.domain.util.vo.SessionUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<List<FileInfo>> uploadFiles(@RequestBody MultipartFile[] files, HttpServletRequest request) {
        User loginUser = SessionUtil.getLoginUser(request.getCookies()).orElseThrow( () -> new RuntimeException("UnAuthorization"));

        List<FileInfo> fileInfoList = fileService.uploadFiles(loginUser, files);

        return ResponseEntity.ok().body(fileInfoList);

    }
}
