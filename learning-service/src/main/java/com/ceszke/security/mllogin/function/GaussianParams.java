package com.ceszke.security.mllogin.function;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GaussianParams {

    private int mu;

    private int sigma2;
}
