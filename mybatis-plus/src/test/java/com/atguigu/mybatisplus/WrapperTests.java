package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void test01(){
        //查询名字中包含n，年龄大于等于10且小于等于20，email不为空的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("name","n")
                .between("age",10,20)
                .isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);

    }

    @Test
    public void test02(){
    //按年龄降序查询用户，如果年龄相同则按id降序排列
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("age")
                .orderByDesc("id");

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void test03() {
        //删除email为空的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int delete = userMapper.delete(queryWrapper);
        System.out.println("受影响行数："+delete);
    }

    @Test
    public void test04() {
        //查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("name","n")
                .and(i->i.lt("age",18).or().isNull("email"));
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int update = userMapper.update(user, queryWrapper);
        System.out.println("受影响行数："+update);
    }

    @Test
    public void test05() {
        //查询所有用户的用户名和年龄
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age");
        List<Map<String, Object>> lists = userMapper.selectMaps(queryWrapper);
        lists.forEach(System.out::println);
    }

    @Test
    public void test06() {
        //查询id不大于3的所有用户的id列表
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id","select id from t_user where id <= 3");
        //但上面的方式容易引发sql注入
        //可是使用下面的查询方式替换
        //queryWrapper.in("id", 1, 2, 3 );
        //或
        //queryWrapper.le("id", 3 );


        //selectObjs的使用场景：只返回一列
        List<Object> objects = userMapper.selectObjs(queryWrapper);
        objects.forEach(System.out::println);
    }

    //UpdateWrapper

    @Test
    public void test07() {
        //查询名字中包含n，且（年龄小于20或email为空的用户），
        // 并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set("age",18)
                .set("email","user@atguigu.com")
                .like("name","n")
                .and(i->i.lt("age",18).or().isNull("email"));

        User user = new User();
        int updateResult = userMapper.update(user, updateWrapper);
        System.out.println("受影响行数："+updateResult);

    }

    @Test
    public void test08() {
        //动态组装查询条件
        //查询名字中包含n，年龄大于10且小于20的用户，查询条件来源于用户输入，是可选的
        //定义查询条件，有可能为null（用户未输入）
        String name = null;
        Integer ageBegin = 10;
        Integer ageEnd = 20;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank("name"),"name","n");
        queryWrapper.ge(ageBegin != null,"age",10);
        queryWrapper.le(ageEnd != null,"age",20);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test09() {
        //查询名字中包含n，年龄大于10且小于20的用户，查询条件来源于用户输入，是可选的
        //使用LambdaXxxWrapper
        String name = null;
        Integer ageBegin = 10;
        Integer ageEnd = 20;

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank("name"),User::getName,"n");
        lambdaQueryWrapper.ge(ageBegin != null,User::getAge,10);
        lambdaQueryWrapper.le(ageEnd != null,User::getAge,20);

        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test10() {
        //Update - 需求同例4
        ////查询名字中包含n，且（年龄小于20或email为空的用户），
        // 并将这些用户的年龄设置为20，邮箱设置为 user@atguigu222.com
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper
                .set(User::getAge,20)
                .set(User::getEmail,"user@atguigu222.com")
                .like(User::getName,"n")
                .and(i->i.lt(User::getAge,20).or().isNull(User::getEmail));

        User user = new User();
        int lambdaUpdate = userMapper.update(user, lambdaUpdateWrapper);
        System.out.println("受影响行数："+lambdaUpdate);
    }

}