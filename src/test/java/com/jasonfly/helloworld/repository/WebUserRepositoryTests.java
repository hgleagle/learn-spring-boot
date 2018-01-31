package com.jasonfly.helloworld.repository;

import com.jasonfly.helloworld.domain.WebUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebUserRepositoryTests {
    @Resource
    private WebUserRepository userRepository;

    @Test
    public void test() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new WebUser("aa", "aa123456", "aa@126.com", "aa", formattedDate, 19));
        userRepository.save(new WebUser("bb", "bb123456", "bb@126.com", "bb", formattedDate, 19));
        userRepository.save(new WebUser("cc", "cc123456", "cc@126.com", "cc", formattedDate, 19));

        Assert.assertEquals(3, userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "bb@126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa"));
    }

    @Test
    public void testPageQuery() {
        int page = 1, size = 10;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        userRepository.findAll(pageable);
        userRepository.findByNickName("testName", pageable);
    }
}
