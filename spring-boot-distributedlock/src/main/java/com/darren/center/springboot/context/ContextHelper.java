package com.darren.center.springboot.context;

import com.darren.center.springboot.common.Constants;
import com.darren.center.springboot.id.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ContextHelper {

    @Autowired
    private IDGenerator idGenerator;

    public Context generatorContext(){
        long seq = idGenerator.next(Constants.PLATFORM_KEY).id;
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        String msgSeq = Constants.PLATFORM_KEY.concat(sf.format(new Date())) + seq;
        String msgSeq = Constants.PLATFORM_KEY.concat(LocalDateTime.now().format(Constants.DATE_TIEM_FORMATTER)) + seq;
        Context context = new Context();
        context.setMsgSeq(msgSeq);
        return context;
    }

}
