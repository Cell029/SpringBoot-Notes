package com.cell.first_ssm.builder.bean;

// 具体建造者（ConcreteBuilder）
public class GamingComputerBuilder extends ComputerBuilder {
    @Override
    public void buildCpu() {
        computer.setCpu("Intel i9");
    }

    @Override
    public void buildRam() {
        computer.setRam("32GB DDR5");
    }

    @Override
    public void buildStorage() {
        computer.setStorage("2TB SSD");
    }
}
