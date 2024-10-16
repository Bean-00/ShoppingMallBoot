package net.study.shoppingmallboot.domain.file.service;


import net.study.shoppingmallboot.domain.file.vo.FileInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service("fileService")

public class FileServiceImpl implements FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public List<FileInfo> uploadFiles(MultipartFile[] files) {
        List<FileInfo> fileInfoList = new ArrayList<>();

        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) continue;

            FileInfo fileInfo = convertMultipartFileIntoFileInfo(multipartFile);

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
            //TODO: fileInfo DB insert insert 한 번에 진행. 1. bulk Insert mybatis의 for each 이용하거나, sql 만들어서 실행. 2. batch insert executeBatch connect 열고 대량으로 commit. seq id 세팅하는 mybatis 기능. 한 방에 리스트 전달해서 DB에 넣기. xml

        return fileInfoList;
    }

    private FileInfo convertMultipartFileIntoFileInfo(MultipartFile multipartFile) {

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
                .build();
    }

}
