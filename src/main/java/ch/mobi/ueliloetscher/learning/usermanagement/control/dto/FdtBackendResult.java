package ch.mobi.ueliloetscher.learning.usermanagement.control.dto;

import java.util.Collection;

public class FdtBackendResult {

    private Collection<FdtCodeWrapper> results;

    public FdtBackendResult() {
    }

    public FdtBackendResult(Collection<FdtCodeWrapper> results) {
        this.results = results;
    }

    public Collection<FdtCodeWrapper> getResults() {
        return results;
    }

    public void setResults(Collection<FdtCodeWrapper> results) {
        this.results = results;
    }
}

