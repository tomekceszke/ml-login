package com.ceszke.security.mllogin.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LearnedModelDto implements Serializable {

    private double epsilon;

    private int mu;

    private int sigma2;
}
