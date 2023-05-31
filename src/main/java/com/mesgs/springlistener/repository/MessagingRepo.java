package com.mesgs.springlistener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mesgs.springlistener.entity.Messages;

@Repository
public interface MessagingRepo extends JpaRepository<Messages, Integer> {

}
