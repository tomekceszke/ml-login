package com.ceszke.security.mllogin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LearnedModelDto implements Serializable {

    private double epsilon;

    private int mu;

    private int sigma2;
}
