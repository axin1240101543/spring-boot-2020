package com.darren.center.springboot.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JacksonXmlRootElement(localName = "body")
public class Body {

    @JacksonXmlProperty(localName = "PRES_NUM")
    private String presNum;

    @JacksonXmlProperty(localName = "PRES_TIME")
    private String presTime;

    @JacksonXmlProperty(localName = "TREAT_CARD")
    private String treatCard;

    @JacksonXmlProperty(localName = "REG_NUM")
    private String regNum;

    @JacksonXmlProperty(localName = "IS_HOS_ADDR")
    private String isHosAddr;

    @JacksonXmlProperty(localName = "PATIENT_NAME")
    private String patientName;

    @JacksonXmlProperty(localName = "AGE")
    private String age;

    @JacksonXmlProperty(localName = "GENDER")
    private String gender;

    @JacksonXmlProperty(localName = "TEL")
    private String tel;

    @JacksonXmlProperty(localName = "IS_SUFFERING")
    private String isSuffering;

    @JacksonXmlProperty(localName = "AMOUNT")
    private String amount;

    @JacksonXmlProperty(localName = "JI_FRIED")
    private String jiFried;

    @JacksonXmlProperty(localName = "TYPE")
    private String type;

    @JacksonXmlProperty(localName = "WJ_TYPE")
    private String wjType;

    @JacksonXmlProperty(localName = "IS_WITHIN")
    private String isWithin;

    @JacksonXmlProperty(localName = "OTHER_PRES_NUM")
    private String otherPresNum;

    @JacksonXmlProperty(localName = "SPECIAL_INSTRU")
    private String specialInstru;

    @JacksonXmlProperty(localName = "BED_NUM")
    private String bedNum;

    @JacksonXmlProperty(localName = "HOS_DEPART")
    private String hosDepart;

    @JacksonXmlProperty(localName = "HOSPITAL_NUM")
    private String hospitalNum;

    @JacksonXmlProperty(localName = "DISEASE_CODE")
    private String diseaseCode;

    @JacksonXmlProperty(localName = "DOCTOR")
    private String doctor;

    @JacksonXmlProperty(localName = "PASTE_DESC_FILE")
    private String pasteDescFile;

    @JacksonXmlProperty(localName = "PRESCRIPT_REMARK")
    private String prescriptRemark;

    @JacksonXmlProperty(localName = "MEDICATION_METHODS")
    private String medicationMethods;

    @JacksonXmlProperty(localName = "MEDICATION_INSTRUCTION")
    private String medicationInstruction;

    @JacksonXmlProperty(localName = "PER_PACK_NUM")
    private String perPackNum;

    @JacksonXmlProperty(localName = "PER_PACK_DOSE")
    private String perPackDose;

    @JacksonXmlProperty(localName = "IMAGES")
    private String images;

    @JacksonXmlProperty(localName = "MONEY")
    private String money;

    @JacksonXmlProperty(localName = "IS_PREGNANT")
    private String isPregnant;

    @JacksonXmlProperty(localName = "MEDICINES")
    private String medicines;

    @JacksonXmlProperty(localName = "DOSE")
    private String dose;

    @JacksonXmlProperty(localName = "UNIT")
    private String unit;

    @JacksonXmlProperty(localName = "HERB_UNIT_PRICE")
    private String herbUnitPrice;

    @JacksonXmlProperty(localName = "GOODS_NUM")
    private String goodsNum;

    @JacksonXmlProperty(localName = "DOSE_THAT")
    private String doseThat;

    @JacksonXmlProperty(localName = "REMARK")
    private String remark;

    @JacksonXmlProperty(localName = "M_USAGE")
    private String mUsage;

    @JacksonXmlProperty(localName = "GOODS_NORMS")
    private String goodsNorms;

    @JacksonXmlProperty(localName = "GOODS_ORGIN")
    private String goodsOrgin;

    @JacksonXmlProperty(localName = "MEDPERDOS")
    private String medperdos;

    @JacksonXmlProperty(localName = "MEDPERDAY")
    private String medperday;

}
