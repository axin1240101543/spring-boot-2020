package com.darren.center.springboot.action;

import com.darren.center.springboot.command.AbstractAction;
import com.darren.center.springboot.command.ActionMapper;
import com.darren.center.springboot.entity.WebParams;
import com.darren.center.springboot.enums.ActionEnum;
import com.darren.center.springboot.exceptions.UtilsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component("Action1")
public class Action1 extends AbstractAction {

    public Action1(@Value("${service.code.Action1:Action1}")String serviceCode,
                   @Qualifier("Action-BaseActionMapper")ActionMapper actionMapper) {
        super(serviceCode, actionMapper);
    }

    @Override
    public boolean action(WebParams params){
        if (null == params || null == params.getType()){
            throw new UtilsException(UtilsException.VERIFY_PARAM_ERROR, "参数校验失败");
        }
        return params.getType().equals(ActionEnum.SERVICE_1.getService());
    }

    @Override
    public String doAction(WebParams params){
        if (null == params || null == params.getType()){
            throw new UtilsException(UtilsException.VERIFY_PARAM_ERROR, "参数校验失败");
        }
        return "进入Action1的处理逻辑";
    }
}
