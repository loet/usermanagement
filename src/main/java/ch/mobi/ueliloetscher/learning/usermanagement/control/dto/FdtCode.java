package ch.mobi.ueliloetscher.learning.usermanagement.control.dto;

public class FdtCode {
    private int code;
    private String gueltigAb;
    private String gueltigBis;
    private FdtType art;

    public FdtCode() {
    }

    public FdtCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getGueltigAb() {
        return gueltigAb;
    }

    public void setGueltigAb(String gueltigAb) {
        this.gueltigAb = gueltigAb;
    }

    public String getGueltigBis() {
        return gueltigBis;
    }

    public void setGueltigBis(String gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    public FdtType getArt() {
        return art;
    }

    public void setArt(FdtType art) {
        this.art = art;
    }
}
