package com.cell.first_ssm.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * t_student
 */
@Data
public class Student implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String email;

    private static final long serialVersionUID = 1L;
}