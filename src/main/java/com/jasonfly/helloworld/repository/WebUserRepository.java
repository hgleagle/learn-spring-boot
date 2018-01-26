package com.jasonfly.helloworld.repository;

import com.jasonfly.helloworld.domain.WebUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface WebUserRepository extends JpaRepository<WebUser, Long> {
    @Query("select u from WebUser u")
    Page<WebUser> findList(Pageable pageable);
    Page<WebUser> findByNickName(String nickName, Pageable pageable);

    @Transactional(timeout = 10)
    @Modifying
    @Query("update WebUser set userName = ?1 where id = ?2")
    int modifyById(String userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from WebUser where id = ?1")
    void deleteById(Long id);

    @Query("select u from WebUser u where u.email = ?1")
    WebUser findByEmail(String email);

    WebUser findById(long id);
    WebUser findByUserName(String userName);
    WebUser findByUserNameOrEmail(String userName, String email);

}
