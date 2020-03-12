package com.darren.center.springboot.id;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@ToString
@AllArgsConstructor
public class ID implements Externalizable{

    /**
     * 生成的ID值
     */
    public long id;

    /**
     * 生成该ID的时间（秒数）
     */
    public long timeSeconds;

    public long getTimeMillis(){
        return this.timeSeconds * 1000;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeLong(id);
        out.writeLong(timeSeconds);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        in.readByte();
        this.id = in.readLong();
        this.timeSeconds = in.readLong();
    }
}
