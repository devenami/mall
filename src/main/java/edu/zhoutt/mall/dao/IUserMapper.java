package edu.zhoutt.mall.dao;

import edu.zhoutt.mall.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IUserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    @Insert({"INSERT INTO user (username, password, role, create_time, update_time) VALUES " +
            "(#{user.username}, #{user.password}, #{user.role}, #{user.createTime}, #{user.updateTime})"})
    void save(@Param("user") User user);

    @Select("SELECT id, username, role, create_time AS createTime, update_time AS updateTime FROM user where id = #{id}")
    User findById(@Param("id") Long id);

    @Select("SELECT id, username, password, role, create_time AS createTime, update_time AS updateTime " +
            "FROM user where username = #{username} and password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("SELECT COUNT(id) AS count FROM user WHERE username = #{username} AND role = #{role}")
    int countByUsernameAndRole(@Param("username") String username, @Param("role") int role);
}
