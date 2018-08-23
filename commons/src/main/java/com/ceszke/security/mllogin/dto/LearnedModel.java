package com.ceszke.security.mllogin.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LearnedModel implements Serializable {

    private int epsilon;

    private GaussianDistribution gaussianDistribution;
}
