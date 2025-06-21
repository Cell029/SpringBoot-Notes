package com.cell.first_ssm.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * t_user
 */
@Data
public class User implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String email;

    private static final long serialVersionUID = 1L;
}