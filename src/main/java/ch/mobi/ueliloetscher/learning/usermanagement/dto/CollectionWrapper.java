package ch.mobi.ueliloetscher.learning.usermanagement.dto;

import java.io.Serializable;
import java.util.Collection;

public class CollectionWrapper implements Serializable {

    private Collection data;

    public CollectionWrapper() {
    }

    public CollectionWrapper(Collection data) {
        this.data = data;
    }

    public Collection getData() {
        return data;
    }

    public void setData(Collection data) {
        this.data = data;
    }
}
