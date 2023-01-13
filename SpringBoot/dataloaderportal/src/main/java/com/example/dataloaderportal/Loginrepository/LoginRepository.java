package com.example.dataloaderportal.Loginrepository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 

import com.example.dataloaderportal.Logindomain.Login;

 

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

    public Login findByUsername(String username);
}