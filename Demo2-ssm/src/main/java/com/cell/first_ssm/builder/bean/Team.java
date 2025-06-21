package com.cell.first_ssm.builder.bean;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
public class Team {
    private String name;
    @Singular
    private List<String> members;
}
