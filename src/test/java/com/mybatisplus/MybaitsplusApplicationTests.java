package com.mybatisplus;

import com.mybatisplus.mapper.TopicMapper;
import com.mybatisplus.pojo.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;


@SpringBootTest
class MybaitsplusApplicationTests {

    @Autowired
    private TopicMapper topicMapper;

    @Test
    void contextLoads() {
        List<Topic> topics = topicMapper.selectList(null);
        topics.forEach(System.out::println);
    }


    @Test
    public void test_insert() {
        Topic topic = new Topic();
        topic.setTitle("TEST_java_python");
        topic.setContent("helloworld");
        topic.setTag("tag111");
        topic.setInTime(new Date());

        int i = topicMapper.insert(topic);
        System.out.println(i);
        System.out.println(topic);
    }


    @Test
    public void TestUpdateById() {
        Topic topic = new Topic();
        topic.setId(28);
        topic.setTitle("heihei");
        topic.setContent("test");
        topic.setTag("mytag");

        int i = topicMapper.updateById(topic);
        System.out.println(i);
        System.out.println(topic);

    }

    public void TestUpdate() {

    }

    //测试乐观锁成功
    @Test
    public void testOptimisticLocker() {
        //查询用户信息
        Topic topic = topicMapper.selectById(1);
        //修改用户信息
        topic.setContent("测试乐观锁的信息");
        //执行更新操作
        topicMapper.updateById(topic);

    }

    //测试乐观锁失败 多线程的情况下
    @Test
    public void testOptimisticLocker1() {
        //查询用户信息
        Topic topic = topicMapper.selectById(2);
        //修改用户信息
        topic.setContent("测试乐观锁的信息");

        Topic topic1 = topicMapper.selectById(2);
        //修改用户信息
        topic1.setContent("测试乐观锁的信息1111");

        //执行更新操作  多线程情况下一定要使用CAS  自旋锁来进行操作
        topicMapper.updateById(topic1);
        topicMapper.updateById(topic);


    }


}
