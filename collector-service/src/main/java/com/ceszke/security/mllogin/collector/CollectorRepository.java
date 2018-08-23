package com.ceszke.security.mllogin.collector;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectorRepository extends JpaRepository<Sample, Long> {
}
