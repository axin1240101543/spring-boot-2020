package com.darren.center.springboot.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JacksonXmlRootElement(localName = "ESBEntry")
public class ESBEntry {

    @JacksonXmlProperty(localName = "RetInfo")
    private RetInfo retInfo;

    @JacksonXmlProperty(localName = "MsgInfo")
    private MsgInfo msgInfo;

}
