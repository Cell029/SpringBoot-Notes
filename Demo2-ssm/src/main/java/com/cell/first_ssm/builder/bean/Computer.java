package com.cell.first_ssm.builder.bean;

// 产品类
public class Computer {
    private String cpu;
    private String ram;
    private String storage;

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + ", Storage=" + storage + "]";
    }
}
