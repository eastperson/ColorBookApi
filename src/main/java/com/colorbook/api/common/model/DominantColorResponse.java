package com.colorbook.api.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DominantColorResponse {

    private Double ratio;

    private int[] RGB;
}
