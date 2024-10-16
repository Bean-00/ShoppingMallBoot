package net.study.shoppingmallboot.domain.file.service;

import net.study.shoppingmallboot.domain.file.vo.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<FileInfo> uploadFiles(MultipartFile[] files);
}
