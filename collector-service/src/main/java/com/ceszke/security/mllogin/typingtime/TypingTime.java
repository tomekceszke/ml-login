package com.ceszke.security.mllogin.typingtime;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypingTime {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private int typingTime;

}
