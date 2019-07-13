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
    * 广告投放记录表
    * </p>
*
* @author song
* @since 2019-07-11
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @TableName("ad_delivery_record")
    @ApiModel(value="AdDeliveryRecord对象", description="广告投放记录表")
    public class AdDeliveryRecord implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "主键")
            @TableId(value = "id", type = IdType.AUTO)
    private Long id;

            @ApiModelProperty(value = "广告ID")
    private Long adId;

            @ApiModelProperty(value = "投放城市code(-1:全国,其他code:指定城市)")
    private Integer cityCode;

            @ApiModelProperty(value = "投放城市")
    private String cityName;

            @ApiModelProperty(value = "开始时间")
    private Date startTime;

            @ApiModelProperty(value = "结束时间")
    private Date endTime;

            @ApiModelProperty(value = "广告位置")
    private Integer adIndex;

            @ApiModelProperty(value = "投放平台(安卓,IOS,全部)")
    private String adPlatform;

            @ApiModelProperty(value = "状态(0:未上架,1:已上架,2:已下架)")
    private Integer status;

            @ApiModelProperty(value = "创建时间")
    private Date createTime;

            @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
