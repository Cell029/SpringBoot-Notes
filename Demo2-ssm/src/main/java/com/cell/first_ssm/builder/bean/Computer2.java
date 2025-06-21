package com.cell.first_ssm.builder.bean;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Computer2 {
    private String cpu;
    private String ram;
    private String storage;
}
