package com.test.tt;

    import com.baomidou.mybatisplus.annotation.TableName;
    import com.baomidou.mybatisplus.annotation.IdType;
    import java.util.Date;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 广告位置表
    * </p>
*
* @author song
* @since 2019-07-11
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ad_position")
    @ApiModel(value="AdPosition对象", description="广告位置表")
    public class AdPosition implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键")
            @TableId(value = "id", type = IdType.AUTO)
    private Long id;

            @ApiModelProperty(value = "编码")
    private String positionNo;

            @ApiModelProperty(value = "名称")
    private String positionName;

            @ApiModelProperty(value = "展现类型(1:图片,2:公司信息)")
    private Integer elementType;

            @ApiModelProperty(value = "数量")
    private Integer elementNum;

            @ApiModelProperty(value = "启用/禁用(0:禁用,1:启用)")
    private Boolean enableStatus;

            @ApiModelProperty(value = "创建时间")
    private Date createTime;

            @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
