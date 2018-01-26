package com.jasonfly.helloworld.repository;

import com.jasonfly.helloworld.domain.UserDetail;
import com.jasonfly.helloworld.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepositoryTests {
    @Resource
    private UserDetailRepository userDetailRepository;

    @Test
    public void testUserInfo() {
        userDetailRepository.save(new UserDetail(2L, "test", "football"));
        List<UserInfo> userInfos = userDetailRepository.findUserInfo("football");

        for (UserInfo userInfo: userInfos) {
            System.out.println("address: " + userInfo.getAddress());
        }
    }
}
