package kg.gov.mf.loan.task.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="mfnotifcation")
public class MFNotification extends GenericModel
{
    private long toUser;
    //private
}
