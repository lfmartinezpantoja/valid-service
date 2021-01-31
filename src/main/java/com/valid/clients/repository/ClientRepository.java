package com.valid.clients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valid.clients.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
