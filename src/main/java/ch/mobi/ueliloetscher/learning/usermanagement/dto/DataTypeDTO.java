package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import java.io.Serializable;

public class DataTypeDTO implements Serializable {
    private int codeType;
    private int code;
    private String en;
    private String de;
    private String fr;
    private String it;
    private String validFrom;
    private String validTo;

    public DataTypeDTO() {
    }

    public DataTypeDTO(int codeType, int code, String en, String de, String fr, String it, String validFrom, String validTo) {
        this.codeType = codeType;
        this.code = code;
        this.en = en;
        this.de = de;
        this.fr = fr;
        this.it = it;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }
}
