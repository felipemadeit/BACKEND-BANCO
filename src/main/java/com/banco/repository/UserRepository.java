package com.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banco.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository <User, Long> {


    Optional<User> findByUserDni(String userDni);

}
