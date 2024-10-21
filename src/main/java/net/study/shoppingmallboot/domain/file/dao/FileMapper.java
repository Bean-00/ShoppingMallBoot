package net.study.shoppingmallboot.domain.file.dao;

import net.study.shoppingmallboot.domain.file.vo.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    void insertFileInfo(FileInfo fileInfo);
}
