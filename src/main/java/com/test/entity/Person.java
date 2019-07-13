package com.test.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


@Data
@Accessors(chain = true)
@ApiModel
public class Person {

    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty("姓名")
    private String name;
    //Integer类型的数据 给个默认值 否则会报NumberFormatException: For input string: ""异常
    //但不影响程序使用
    @ApiModelProperty(value = "年龄",example = "0")
    private Integer age;
}
