package com.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mybatisplus.pojo.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TopicMapper extends BaseMapper<Topic> {
    List<Topic> selectByTitle(@Param("title") String title);

    List<Map> selectByTitle1(@Param("title") String title);

    IPage<Map> selectMapWithPage(IPage iPage);
}
