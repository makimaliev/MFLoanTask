package kg.gov.mf.loan.task.model;

import kg.gov.mf.loan.admin.sys.model.User;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="system_constant")
public class SystemConstant extends GenericModel {

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sc_outgoingRegistrator",
            joinColumns = { @JoinColumn(name = "outgoingRegistrator") },
            inverseJoinColumns = { @JoinColumn(name = "id") }
    )
    private Set<User> outgoingRegistrator = new HashSet<>(0);

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sc_scannerUser",
            joinColumns = { @JoinColumn(name = "scannerUser") },
            inverseJoinColumns = { @JoinColumn(name = "id") }
    )
    private Set<User> scannerUser = new HashSet<>(0);

    //region GET-SET
    public Set<User> getOutgoingRegistrator() {
        return outgoingRegistrator;
    }

    public void setOutgoingRegistrator(Set<User> outgoingRegistrator) {
        this.outgoingRegistrator = outgoingRegistrator;
    }

    public Set<User> getScannerUser() {
        return scannerUser;
    }

    public void setScannerUser(Set<User> scannerUser) {
        this.scannerUser = scannerUser;
    }
    //endregion
}
