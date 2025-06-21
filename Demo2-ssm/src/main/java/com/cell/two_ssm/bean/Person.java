package com.cell.two_ssm.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * t_person
 */
@Data
public class Person implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String email;

    private static final long serialVersionUID = 1L;
}