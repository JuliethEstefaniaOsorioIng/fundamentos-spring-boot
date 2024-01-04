package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.entity.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE u.email=?1")
    Optional<Users> findMyUserByEmail(String email);

    @Query("select u from Users u where u.name like ?1%")
    List<Users> findByAndSort(String name, Sort sort);

    List<Users> findByName(String name);

    Optional<Users> findByNameAndEmail(String name, String email);
}
