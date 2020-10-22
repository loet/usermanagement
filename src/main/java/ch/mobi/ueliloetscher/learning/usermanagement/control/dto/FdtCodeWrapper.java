package ch.mobi.ueliloetscher.learning.usermanagement.control.dto;

public class FdtCodeWrapper {

    private FdtCode fdtCode;
    private FdtBezeichnung bezeichnung;

    public FdtCodeWrapper() {
    }

    public FdtCodeWrapper(FdtCode fdtCode) {
        this.fdtCode = fdtCode;
    }

    public FdtCode getFdtCode() {
        return fdtCode;
    }

    public void setFdtCode(FdtCode fdtCode) {
        this.fdtCode = fdtCode;
    }

    public FdtBezeichnung getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(FdtBezeichnung bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
