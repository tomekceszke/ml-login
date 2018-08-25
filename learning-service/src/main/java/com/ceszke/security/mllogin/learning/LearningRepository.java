package com.ceszke.security.mllogin.learning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningRepository  extends JpaRepository<LearnedModel, Long> {
}
