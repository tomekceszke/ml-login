package com.ceszke.security.mllogin.typingtime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TypingTimeRepository extends JpaRepository<TypingTime, Long> {
}
