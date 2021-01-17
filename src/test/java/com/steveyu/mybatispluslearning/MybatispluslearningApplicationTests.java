package com.steveyu.mybatispluslearning;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.steveyu.mybatispluslearning.entity.User;
import com.steveyu.mybatispluslearning.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatispluslearningApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void testAddUser() {
        userMapper.insert(
                new User()
                        .setName("yuyu")
                        .setAge(18)
                        .setEmail("yuyu@apple.com")
        );
    }

    @Test
    public void testSelectBatch() {
        userMapper.selectBatchIds(Arrays.asList(1, 2, 3))
                .forEach(System.out::println);
    }

    @Test
    public void testSelectMap() {
        Map map = new HashMap();
        map.put("name", "yuyu");
        userMapper.selectByMap(map)
            .forEach(System.out::println);
    }

    @Test
    public void testUpdate() {
        User happy = userMapper.selectById(1350831338015240194L)
                .setName("what");
        userMapper.updateById(happy);
    }

    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1, 2);
        Page<User> userPage = userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        userPage.getRecords().forEach(System.out::println);
    }

    @Test
    public void testDeleted() {
        userMapper.deleteById(1350837884711407618L);
        System.out.println(userMapper.selectById(1350837884711407618L));;
    }

    @Test
    public void selectRange() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt("age", 80);
        userMapper.selectList(userQueryWrapper)
            .forEach(System.out::println);
    }

    @Test
    public void selectLike() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id", "name", "age");
        userQueryWrapper.like("name", "%J%");
        userQueryWrapper.orderByDesc("age");
        userMapper.selectList(userQueryWrapper)
                .forEach(System.out::println);
    }
}
