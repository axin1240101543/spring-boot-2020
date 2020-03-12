package com.darren.center.springboot.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "msg")
public class Msg {

    @JacksonXmlProperty(localName = "body")
    private Body body;

}
