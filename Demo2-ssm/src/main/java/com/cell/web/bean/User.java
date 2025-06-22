package com.cell.web.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 添加这个注解可以将 java 对象转换成 xml 格式字符串
@JacksonXmlRootElement
public class User {
    private String username;
    private int age;
}
