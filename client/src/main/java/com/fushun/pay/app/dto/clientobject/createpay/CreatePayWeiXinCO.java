package com.fushun.pay.app.dto.clientobject.createpay;

import com.fushun.pay.app.dto.clientobject.PayCO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author wangfushun
 * @version 1.0
 * @description 微信支付 参数 公共对象
 * @creation 2019年02月03日04时08分
 */
@Data
public class CreatePayWeiXinCO extends PayCO {
    /**
     * 微信openId
     */
    private String openId;

    /**
     * 支付信息描述
     */
    @NotEmpty//(groups={CreateWeiXinJZHPayValidatorGroup.class,CreateWeiXinAppPayValidatorGroup.class})
    private String body;

    /**
     * 用户终端ip
     */
    @NotEmpty//(groups={CreateWeiXinJZHPayValidatorGroup.class,CreateWeiXinAppPayValidatorGroup.class})
    private String spbillCreateIp;
}
