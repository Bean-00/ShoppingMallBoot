package net.study.shoppingmallboot.domain.file.dao;

import net.study.shoppingmallboot.domain.file.vo.FileInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FileMapper {

    void insertFileInfo(FileInfo fileInfo);
}
