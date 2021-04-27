package com.tsoyDmitriy.spendMoneyControl.repository;

import com.tsoyDmitriy.spendMoneyControl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
