package net.study.shoppingmallboot.domain.file.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import net.study.shoppingmallboot.domain.file.service.FileService;
import net.study.shoppingmallboot.domain.file.vo.FileInfo;
import net.study.shoppingmallboot.domain.user.vo.User;
import net.study.shoppingmallboot.domain.util.vo.SessionUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    //Content-Disposition: inline -> 이미지인 경우 바로 보기
    //Content-Disposition: attachment -> 바로 다운로드
    @GetMapping("/image/{imgName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imgName) {
        Resource resource = fileService.getFile(imgName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
