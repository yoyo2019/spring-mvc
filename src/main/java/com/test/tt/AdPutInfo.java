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
    * 广告投放表
    * </p>
*
* @author song
* @since 2019-07-11
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ad_put_info")
    @ApiModel(value="AdPutInfo对象", description="广告投放表")
    public class AdPutInfo implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键")
            @TableId(value = "id", type = IdType.AUTO)
    private Long id;

            @ApiModelProperty(value = "位置编码")
    private String positionNo;

            @ApiModelProperty(value = "展示内容(图片路径/公司ID)")
    private String elementValue;

            @ApiModelProperty(value = "跳转方式(1:H5,2:原生)")
    private Integer goType;

            @ApiModelProperty(value = "广告内容")
    private String adValue;

            @ApiModelProperty(value = "广告参数")
    private String adParam;

            @ApiModelProperty(value = "投放状态(0:待上架,1:已上架,2:已下架)")
    private Integer status;

            @ApiModelProperty(value = "备注(广告主/系统)")
    private String remark;

            @ApiModelProperty(value = "创建时间")
    private Date createTime;

            @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
