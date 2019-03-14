package kg.gov.mf.loan.task.model;

import kg.gov.mf.loan.task.listener.MFEntityListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, MFEntityListener.class})
public class Auditable<U> {

    @CreatedBy
    @Column(name = "au_created_by", updatable = false)
    private U auCreatedBy;

    @CreatedDate
    @Column(name = "au_created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date auCreatedDate;

    @LastModifiedBy
    @Column(name = "au_last_modified_by")
    private U auLastModifiedBy;

    @LastModifiedDate
    @Column(name = "au_last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auLastModifiedDate;

}
