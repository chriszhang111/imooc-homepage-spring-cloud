package com.imooc.homepage.DAO;

import com.imooc.homepage.Entity.HomepageUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomepageUserDao extends JpaRepository<HomepageUser, Long> {
    HomepageUser findByUsername(String username);
}
