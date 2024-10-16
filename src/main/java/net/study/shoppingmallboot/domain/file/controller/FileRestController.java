package net.study.shoppingmallboot.domain.file.controller;


import lombok.RequiredArgsConstructor;
import net.study.shoppingmallboot.domain.file.service.FileService;
import net.study.shoppingmallboot.domain.file.vo.FileInfo;
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
    public ResponseEntity<List<FileInfo>> uploadFiles(@RequestBody MultipartFile[] files) {
        List<FileInfo> fileInfoList = fileService.uploadFiles(files);

        return ResponseEntity.ok().body(fileInfoList);

    }
}
