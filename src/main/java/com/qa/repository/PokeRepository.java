package com.qa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.entity.User;

@Repository
public interface PokeRepository extends JpaRepository<User, Long>{

}
