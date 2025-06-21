package com.cell.first_ssm.builder.bean;

// 抽象建造者（Builder）
public abstract class ComputerBuilder {
    protected Computer computer = new Computer();

    public abstract void buildCpu();
    public abstract void buildRam();
    public abstract void buildStorage();

    public Computer getResult() {
        return computer;
    }
}
