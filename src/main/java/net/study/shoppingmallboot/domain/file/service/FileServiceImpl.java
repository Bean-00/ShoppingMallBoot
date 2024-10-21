package net.study.shoppingmallboot.domain.file.service;


import lombok.RequiredArgsConstructor;
import net.study.shoppingmallboot.domain.file.dao.FileMapper;
import net.study.shoppingmallboot.domain.file.vo.FileInfo;
import net.study.shoppingmallboot.domain.user.vo.User;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service("fileService")
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    @Transactional
    public List<FileInfo> uploadFiles(User owner, MultipartFile[] files) {
        List<FileInfo> fileInfoList = new ArrayList<>();

        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) continue;

            FileInfo fileInfo = convertMultipartFileIntoFileInfo(owner, multipartFile);

            File dir = new File(fileInfo.getFileDirPath());

            if (!dir.exists())
                dir.mkdirs();

            File newFile = new File(fileInfo.getSysFilePath());
            try {
                multipartFile.transferTo(newFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fileInfoList.add(fileInfo);
        }
        for (FileInfo fileInfo : fileInfoList) {
            fileMapper.insertFileInfo(fileInfo);
        }

        return fileInfoList;
    }

    private FileInfo convertMultipartFileIntoFileInfo(User owner, MultipartFile multipartFile) {

        StringBuilder sysFileName = new StringBuilder();
        sysFileName.append(UUID.randomUUID());
        sysFileName.append(".");
        sysFileName.append(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));

        return FileInfo.builder()
                .fileDirPath(uploadDir)
                .fileName(multipartFile.getOriginalFilename())
                .fileSysName(sysFileName.toString())
                .fileSize(multipartFile.getSize())
                .fileMimeType(multipartFile.getContentType())
                .fileUserId(owner.getUserId())
                .build();
    }

}
