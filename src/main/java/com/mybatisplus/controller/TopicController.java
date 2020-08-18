package com.mybatisplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mybatisplus.pojo.Topic;
import com.mybatisplus.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/getall")
    public Object list() {
        return topicService.list();
    }

    @GetMapping("/get/{id}")
    public Topic find(@PathVariable int id) {
        return topicService.find(id);
    }

    @PostMapping("/addTopic")
    public Boolean addTopic(@RequestBody @Valid Topic topic, BindingResult result) {
        if (result.hasErrors()) {
            String message = getErrors(result);
        }
        boolean resp = topicService.add(topic);
        return resp;
    }

    @GetMapping("/selectByTitle")
    public List<Topic> selectByTitle(@RequestParam(value = "title") String title) {
        return topicService.selectByTitle(title);
    }

    @GetMapping("/selectByTitle1")
    public List<Map> selectByTitle1(@RequestParam(value = "title") String title) {
        return topicService.selectByTitle1(title);
    }

    @GetMapping("/selectByPage")
    public List<Topic> selectWithPage(@RequestParam int pageNo, @RequestParam int pageSize) {
        return topicService.selectWithPage(pageNo, pageSize);
    }

    @GetMapping("/selectMapWithPage")
    public IPage<Map> selectMapWithPage(@RequestParam int pageNo, @RequestParam int pageSize){
        return topicService.selectMapWithPage(pageNo, pageSize);
    }

    /**
     * 获取验证错误信息
     *
     * @param result
     * @return
     */
    public static String getErrors(BindingResult result) {
        List<ObjectError> allErrors = result.getAllErrors();
        StringBuffer message = new StringBuffer();
        for (int i = 0; i < allErrors.size(); i++) {
            ObjectError objectError = allErrors.get(i);
            if (i == allErrors.size() - 1) {
                message.append(objectError.getDefaultMessage());
            } else {
                message.append(objectError.getDefaultMessage()).append(",");
            }
        }
        return message.toString();
    }
}
