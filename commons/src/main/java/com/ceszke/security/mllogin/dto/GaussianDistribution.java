package com.ceszke.security.mllogin.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GaussianDistribution {

    private int mu;

    private int sigma2;
}
