package com.jasonfly.helloworld.mapper;

import com.jasonfly.helloworld.entity.UserEntity;
import com.jasonfly.helloworld.enums.UserSexEnum;
import com.jasonfly.helloworld.param.UserParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users")
    @Results({
        @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
        @Result(property = "nickName", column = "nick_name")
    })
    List<UserEntity> getAll();

    @SelectProvider(type = UserSql.class, method = "getList")
    List<UserEntity> getList(UserParam userParam);

    @SelectProvider(type = UserSql.class, method = "getCount")
    int getCount(UserParam userParam);

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    UserEntity getOne(Long id);

    @Select("SELECT * FROM users WHERE userName = #{name}")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<UserEntity> getByName(String name);

    @Insert("INSERT INTO users(userName, passWord, user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(UserEntity user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id = #{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(Long id);
}
