package br.com.insurance.product.domain.entity;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Version {

    private int versionNumber;
    private LocalDate changeAt;

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public LocalDate getChangeAt() {
        return changeAt;
    }

    public void setChangeAt(LocalDate changeAt) {
        this.changeAt = changeAt;
    }
}
