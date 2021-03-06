package com.fushun.pay.app.validator.extensionpoint;

import com.alibaba.cola.extension.ExtensionPointI;
import com.alibaba.cola.validator.ValidatorI;
import com.fushun.pay.app.dto.RefundCmd;

/**
 * @author wangfushun
 * @version 1.0
 * @description
 * @creation 2019年02月03日22时46分
 */
public interface RefundValidatorExtPt<T extends RefundCmd> extends ValidatorI<T>, ExtensionPointI {
}
