package com.example.demo.newApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ApplicationRepository extends JpaRepository<Application, Long> {}
