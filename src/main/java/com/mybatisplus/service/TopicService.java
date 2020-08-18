package com.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mybatisplus.mapper.TopicMapper;
import com.mybatisplus.pojo.Topic;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    public List<Topic> list() {
        return topicMapper.selectList(new QueryWrapper<>());
    }

    public Topic find(int id) {
        return topicMapper.selectById(id);
    }

    public Boolean add(Topic topic) {
        int result = topicMapper.insert(topic);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Topic> selectByTitle(String title) {
        return topicMapper.selectByTitle(title);
    }

    public List<Map> selectByTitle1(String title) {
        return topicMapper.selectByTitle1(title);
    }

    public List<Topic> selectWithPage(int pageNo, int pageSize) {
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        Page<Topic> Page = new Page<>(pageNo, pageSize);
        topicMapper.selectPage(Page, null);
        List<Topic> records = Page.getRecords();
        return records;
    }

    public IPage<Map> selectMapWithPage(int pageNo, int pageSize) {
        IPage<Map> iPage = new Page<>(pageNo, pageSize);
        IPage<Map> page = topicMapper.selectMapWithPage(iPage);
        return page;
    }

}
