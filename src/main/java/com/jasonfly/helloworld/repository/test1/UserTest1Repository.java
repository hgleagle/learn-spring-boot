package com.jasonfly.helloworld.repository.test1;

import com.jasonfly.helloworld.domain.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTest1Repository extends JpaRepository<WebUser, Long> {
    WebUser findByUserName(String userName);
    WebUser findByUserNameOrEmail(String username, String email);
}
