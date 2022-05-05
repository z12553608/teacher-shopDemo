package com.meituan.catering.management.shop.api.http.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 联系方式DTO
 *
 * @author dulinfeng
 */
@Data
@ApiModel("联系人信息")
public class ContactDTO {

    @ApiModelProperty("座机号码")
    private String telephone;

    @ApiModelProperty("手机号码")
    private String cellphone;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("地址")
    private String address;
}
