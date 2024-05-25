package com.example.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.Listener;

public interface ListenerRepository extends JpaRepository<Listener, Long> {}

