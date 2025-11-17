package com.example.wishBirthDaySB.repository;




import com.example.wishBirthDaySB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE MONTH(u.birthday) = :month AND DAY(u.birthday) = :day")
    List<User> findByBirthdayMonthAndDay(@Param("month") int month, @Param("day") int day);
}
