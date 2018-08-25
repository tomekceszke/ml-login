package com.ceszke.security.mllogin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class GaussianDistribution implements Serializable {

    private int mu;

    private int sigma2;
}
