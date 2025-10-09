package com.example.BackEndSpring.repository.bookMyShow;

import com.example.BackEndSpring.model.bookMyShow.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBMSRepository extends JpaRepository<User, String> {
}
