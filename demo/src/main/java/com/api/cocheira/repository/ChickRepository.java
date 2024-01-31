package com.api.cocheira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cocheira.Entity.Chick;

public interface ChickRepository extends JpaRepository<Chick, Long> {


}
