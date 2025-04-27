package com.manage.quanlykytucxa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.manage.quanlykytucxa.domain.Room;
import com.manage.quanlykytucxa.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

  boolean existsByEmail(String email);

  User findByEmail(String email);

  List<User> findByRoomId(Long roomId);

  @Query("SELECT u FROM User u LEFT JOIN Vehicle v ON v.user = u WHERE v IS NULL")
  List<User> findUsersWithoutVehicles();

  @Query(value = """
          SELECT MONTH(FROM_UNIXTIME(UNIX_TIMESTAMP(create_at))) AS month,
                 COUNT(*) AS total
          FROM user
          WHERE MONTH(FROM_UNIXTIME(UNIX_TIMESTAMP(create_at))) IN (:month1, :month2)
            AND YEAR(FROM_UNIXTIME(UNIX_TIMESTAMP(create_at))) = :year
          GROUP BY MONTH(FROM_UNIXTIME(UNIX_TIMESTAMP(create_at)))
      """, nativeQuery = true)
  List<Object[]> countUserByMonths(
      @Param("month1") int month1,
      @Param("month2") int month2,
      @Param("year") int year);

  @Modifying
  @Transactional
  @Query("UPDATE User u SET u.room = null WHERE u.room.id = :roomId")
  void clearRoomByRoomId(@Param("roomId") Long roomId);
}
