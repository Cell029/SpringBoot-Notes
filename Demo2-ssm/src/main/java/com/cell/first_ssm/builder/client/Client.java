package com.cell.first_ssm.builder.client;

import com.cell.first_ssm.builder.bean.*;
import com.cell.first_ssm.service.UserService;

public class Client {
    public static void main(String[] args) {
        /*ComputerBuilder builder = new GamingComputerBuilder();
        Director director = new Director(builder);
        Computer computer = director.construct();
        System.out.println(computer);*/

        /*Computer2 computer = Computer2.builder()
                .cpu("AMD Ryzen 9")
                .ram("64GB DDR5")
                .storage("2TB NVMe SSD")
                .build();
        System.out.println(computer);

        Team team = Team.builder()
                .name("开发组")
                .member("Alice") // 添加单个成员
                .member("Bob")
                .build();
        System.out.println(team);*/

        UserService userService = new UserService();
        userService.createUser("张三");

    }
}
