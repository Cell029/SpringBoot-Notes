package com.cell.first_ssm.builder.bean;

public class Director {
    private ComputerBuilder builder;

    public Director(ComputerBuilder builder) {
        this.builder = builder;
    }

    public Computer construct() {
        builder.buildCpu();
        builder.buildRam();
        builder.buildStorage();
        return builder.getResult();
    }
}
