package com.fushun.pay.app.command;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.extension.ExtensionExecutor;
import com.alibaba.cola.logger.Logger;
import com.alibaba.cola.logger.LoggerFactory;
import com.fushun.framework.util.util.JsonUtil;
import com.fushun.pay.app.convertor.extensionpoint.RefundConvertorExtPt;
import com.fushun.pay.app.dto.RefundCmd;
import com.fushun.pay.app.dto.clientobject.RefundCO;
import com.fushun.pay.app.dto.enumeration.ERefundStatus;
import com.fushun.pay.app.thirdparty.extensionpoint.RefundThirdPartyExtPt;
import com.fushun.pay.app.validator.extensionpoint.RefundValidatorExtPt;
import com.fushun.pay.domain.refund.entity.RefundE;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangfushun
 * @version 1.0
 * @description
 * @creation 2019年02月03日22时37分
 */
@Command
public class RefundCmdExe implements CommandExecutorI<Response, RefundCmd> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExtensionExecutor extensionExecutor;


    @Override
    public Response execute(RefundCmd cmd) {

        //1, validation
        extensionExecutor.executeVoid(RefundValidatorExtPt.class, cmd.getContext(), validator -> validator.validate(cmd));

        //2, invoke domain service or directly operate domain to do business logic process
        RefundE refundE = extensionExecutor.execute(RefundConvertorExtPt.class, cmd.getContext(), convertor -> convertor.clientToEntity(cmd.getRefundCO(), cmd.getContext()));
        refundE.refund();

        //获取支付信息
        try {
            RefundCO refundCO = extensionExecutor.execute(RefundThirdPartyExtPt.class, cmd.getContext(), thirdparty -> thirdparty.refund(cmd.getRefundCO()));
        } catch (Exception e) {
            logger.info("refund exception,param:[{}]", JsonUtil.toJson(cmd.getRefundCO()), e);
            refundE.setERefundStatus(ERefundStatus.fail);
            refundE.fail();
            throw e;
        }

        refundE.setThirdRefundNo(cmd.getRefundCO().getThirdRefundNo());
        refundE.setERefundStatus(ERefundStatus.success);
        refundE.success();

        return Response.buildSuccess();
    }
}
