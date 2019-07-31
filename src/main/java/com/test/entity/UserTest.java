package com.test.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 用户
    * </p>
*
* @author song
* @since 2019-07-26
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("user_test")
    @ApiModel(value="UserTest对象", description="用户")
    public class UserTest implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键id")
            @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

            @ApiModelProperty(value = "密码")
    private String passWord;


}
