package com.jasonfly.helloworld.repository.test1;

import com.jasonfly.helloworld.domain.WebUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserTest1Repository extends JpaRepository<WebUser, Long> {

    @Query("select u from WebUser u")
    Page<WebUser> findList(Pageable pageable);

    WebUser findByUserName(String userName);
    WebUser findByUserNameOrEmail(String username, String email);
    WebUser findById(long id);
}
