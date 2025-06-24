package com.cell.notes.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * t_act
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String actno;
    private Double balance;
}