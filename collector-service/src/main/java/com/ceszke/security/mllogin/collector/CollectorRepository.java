package com.ceszke.security.mllogin.collector;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectorRepository extends JpaRepository<Sample, Long> {

    List<Sample> findAllBySessionId(String sessionId);

    long countBySessionId(String sessionId);
}
