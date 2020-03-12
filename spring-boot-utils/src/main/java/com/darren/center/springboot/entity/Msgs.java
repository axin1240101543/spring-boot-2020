package com.darren.center.springboot.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "Msg")
public class Msgs {

    @JacksonXmlProperty(localName = "msg")
    private Msg msg;

}
