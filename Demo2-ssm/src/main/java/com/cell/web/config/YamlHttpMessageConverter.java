package com.cell.web.config;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.cell.web.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;

// 这是一个消息转换器，专门处理 yaml 格式的数据
// 所有的消息转换器都必须实现 HttpMessageConvert 接口，或者继承 AbstractHttpMessageConverter 抽象类，并重写方法
public class YamlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    // 创建 YAMLFactory 对象
    YAMLFactory yamlFactory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);

    // 创建对象映射器
    private ObjectMapper objectMapper = new ObjectMapper(yamlFactory);

    // 让 消息转换器 和 媒体类型 application/yaml 绑定在一起
    public YamlHttpMessageConverter() {
        super(new MediaType("text", "yaml", Charset.forName("UTF-8")));
    }

    // 指定此消息转换器只适合于哪些类型的对象
    @Override
    protected boolean supports(Class<?> clazz) {
        // 表示只有User类型的数据才能使用消息转换器，其他类型不支持
        return User.class.isAssignableFrom(clazz);
    }

    // 处理 @RequestBody（将提交的yaml格式数据转换为java对象）
    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    // 处理 @ResponseBody（将java对象转换为yaml格式的数据）
    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        this.objectMapper.writeValue(outputMessage.getBody(), o);
        // 注意：spring框架会自动关闭输出流，无需程序员手动释放。
    }
}
