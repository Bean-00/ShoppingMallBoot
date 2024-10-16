package net.study.shoppingmallboot.domain.file.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.File;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FileInfo {
    private int fileId;
    private String fileName;
    @JsonIgnore
    private String fileSysName;
    @JsonIgnore
    private String fileDirPath;
    private long fileSize;
    private Date fileUploadDate;
    private String fileMimeType;
    private String fileUserId;
    private boolean file_is_deleted;

    @JsonIgnore
    public String getSysFilePath() {
        StringBuilder sb = new StringBuilder();

        sb.append(fileDirPath);
        sb.append(File.separator);
        sb.append(fileSysName);

        return sb.toString();
    }
}
