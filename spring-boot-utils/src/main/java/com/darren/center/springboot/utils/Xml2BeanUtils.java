package com.darren.center.springboot.utils;

import com.alibaba.fastjson.JSON;
import com.darren.center.springboot.entity.ESBEntry;
import com.darren.center.springboot.entity.Msgs;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Xml2BeanUtils {

    public static void main(String[] args) throws Exception{
        String pres = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><NS1:HIPMessageServerResponse xmlns:NS1=\"http://esb.ewell.cc\"><NS1:output><![CDATA[<ESBEntry><MessageHeader><Fid>BS10163</Fid><SourceSysCode>S02</SourceSysCode><TargetSysCode>S01</TargetSysCode><MsgDate>2019-06-12T17:24:40.942820</MsgDate></MessageHeader><MsgInfo><Msg><msg><body><PRES_NUM>56275481</PRES_NUM><PRES_TIME>2019-06-12 10:21:54</PRES_TIME><TREAT_CARD>14091315025</TREAT_CARD><REG_NUM>JA12345703</REG_NUM><IS_HOS_ADDR>2</IS_HOS_ADDR><PATIENT_NAME>杜伊伊</PATIENT_NAME><AGE>65</AGE><GENDER>0</GENDER><TEL>15989447255</TEL><IS_SUFFERING>0</IS_SUFFERING><AMOUNT>3</AMOUNT><JI_FRIED>1</JI_FRIED><TYPE>0</TYPE><WJ_TYPE> </WJ_TYPE><IS_WITHIN>0</IS_WITHIN><OTHER_PRES_NUM>56275481</OTHER_PRES_NUM><SPECIAL_INSTRU>鼻塞,感冒</SPECIAL_INSTRU><BED_NUM> </BED_NUM><HOS_DEPART>中医科</HOS_DEPART><HOSPITAL_NUM> </HOSPITAL_NUM><DISEASE_CODE> </DISEASE_CODE><DOCTOR>管理员</DOCTOR><PASTE_DESC_FILE> </PASTE_DESC_FILE><PRESCRIPT_REMARK> </PRESCRIPT_REMARK><MEDICATION_METHODS> </MEDICATION_METHODS><MEDICATION_INSTRUCTION>每日一剂,分2-3次煎服</MEDICATION_INSTRUCTION><PER_PACK_NUM>2</PER_PACK_NUM><PER_PACK_DOSE>50ml</PER_PACK_DOSE><IMAGES> </IMAGES><MONEY>.53</MONEY><IS_PREGNANT>0</IS_PREGNANT></body></msg></Msg><MsgCount>1</MsgCount><CurrentNum>1</CurrentNum></MsgInfo><RetInfo><RetCode>1</RetCode><RetCon>查询成功</RetCon></RetInfo></ESBEntry>]]></NS1:output></NS1:HIPMessageServerResponse></soapenv:Body></soapenv:Envelope>";
        ESBEntry esbEntry = xml2bean(pres, "<ESBEntry>", "</ESBEntry>", ESBEntry.class);
        log.info("xml转bean:{}", JSON.toJSONString(esbEntry));
        //bean转xml
        String xml = bean2xml(esbEntry);
        log.info("bean转xml:{}", JSON.toJSONString(xml));
        //使用XStream将bean转xml
        XStream xstream = new XStream();
        xstream.alias("ESBEntry", ESBEntry.class);
        xstream.alias("Msg", Msgs.class);
        String xXml = xstream.toXML(esbEntry);
        log.info("使用XStream将bean转xml:{}", xXml);

        String details = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><NS1:HIPMessageServerResponse xmlns:NS1=\"http://esb.ewell.cc\"><NS1:output><![CDATA[<ESBEntry><MessageHeader><Fid>BS10164</Fid><SourceSysCode>S02</SourceSysCode><TargetSysCode>S01</TargetSysCode><MsgDate>2019-06-12T17:26:50.775557</MsgDate></MessageHeader><MsgInfo><Msg><msg><body><PRES_NUM>56275481</PRES_NUM><MEDICINES>板蓝根</MEDICINES><DOSE>3</DOSE><UNIT>克</UNIT><HERB_UNIT_PRICE>.059</HERB_UNIT_PRICE><GOODS_NUM>YP668*557</GOODS_NUM><DOSE_THAT> </DOSE_THAT><REMARK> </REMARK><M_USAGE>先煎</M_USAGE><GOODS_NORMS>1克/克*1000克/公斤</GOODS_NORMS><GOODS_ORGIN> </GOODS_ORGIN><MEDPERDOS> </MEDPERDOS><MEDPERDAY> </MEDPERDAY></body></msg></Msg><Msg><msg><body><PRES_NUM>56275481</PRES_NUM><MEDICINES>麸炒苍术</MEDICINES><DOSE>3</DOSE><UNIT>克</UNIT><HERB_UNIT_PRICE>.117</HERB_UNIT_PRICE><GOODS_NUM>YP667*557</GOODS_NUM><DOSE_THAT> </DOSE_THAT><REMARK> </REMARK><M_USAGE>后下</M_USAGE><GOODS_NORMS>1克/克*1000克/公斤</GOODS_NORMS><GOODS_ORGIN> </GOODS_ORGIN><MEDPERDOS> </MEDPERDOS><MEDPERDAY> </MEDPERDAY></body></msg></Msg><MsgCount>1</MsgCount><CurrentNum>1</CurrentNum></MsgInfo><RetInfo><RetCode>1</RetCode><RetCon>查询成功</RetCon></RetInfo></ESBEntry>]]></NS1:output></NS1:HIPMessageServerResponse></soapenv:Body></soapenv:Envelope>";
        ESBEntry esbEntrys = xml2bean(details, "<ESBEntry>", "</ESBEntry>", ESBEntry.class);
        log.info("xml转bean:{}", JSON.toJSONString(esbEntrys));
    }

    /**
     * xml转bean
     * @param xml 原始xml
     * @param headNode 目标头标签
     * @param tailNode 目标尾标签
     * @param c  转换类型
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T>T xml2bean(String xml, String headNode, String tailNode, Class<T> c) throws Exception{
        //截取目标xml
        int indexBegin = xml.indexOf(headNode);
        int indexEnd = xml.indexOf(tailNode) + tailNode.length();
        String newXml = xml.substring(indexBegin, indexEnd);
        //使用jackson
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return xmlMapper.readValue(newXml,c);
    }

    /**
     * bean转xml
     * @param obj 原始bean
     * @return
     * @throws Exception
     */
    public static String bean2xml(Object obj) throws Exception{
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(obj).replaceAll(" xmlns=\"\"", "");
    }

}
