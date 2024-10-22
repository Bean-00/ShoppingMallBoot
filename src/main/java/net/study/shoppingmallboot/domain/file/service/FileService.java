package net.study.shoppingmallboot.domain.file.service;

import net.study.shoppingmallboot.domain.file.vo.FileInfo;
import net.study.shoppingmallboot.domain.user.vo.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<FileInfo> uploadFiles(User owner, MultipartFile[] files);

    Resource getFile(String imgName);
}
