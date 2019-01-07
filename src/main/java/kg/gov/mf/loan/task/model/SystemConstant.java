package kg.gov.mf.loan.task.model;

import kg.gov.mf.loan.admin.sys.model.User;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="system_constant")
public class SystemConstant extends GenericModel {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "outgoingRegistrator")
    private Set<User> outgoingRegistrator = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "scannerUser")
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
