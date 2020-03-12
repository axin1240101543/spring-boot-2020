package com.darren.center.springboot.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "RetInfo")
public class RetInfo {

    @JacksonXmlProperty(localName = "RetCode")
    private String retCode;

    @JacksonXmlProperty(localName = "RetCon")
    private String retCon;

}
