package kg.gov.mf.loan.task.model;

import kg.gov.mf.loan.admin.org.model.Person;
import kg.gov.mf.loan.manage.model.GenericModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="task")
public class Task extends GenericModel {

    //Short description of the issue.
    @NotNull
    private String summary;

    private String description;

    //The person who encountered the issue.
    @ManyToOne(targetEntity=Person.class, fetch = FetchType.EAGER)
    @JoinColumn(name="identifiedByPersonId")
    private Person identifiedBy;

    //The date the issue occurred.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date identifiedDate;

    //The person assigned to resolve this issue.
    @ManyToOne(targetEntity=Person.class, fetch = FetchType.EAGER)
    @JoinColumn(name="assignedToPersonId")
    private Person assignedTo;

    //The issue status which can be open, closed or on-hold.
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    //The issue priority which can be high, medium or low.
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    //The date this issue should be closed.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date targetResolutionDate;

    //A brief description of progress made for this issue.
    private String progress;

    //The date the issue was closed.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date actualResolutionDate;

    private String resolutionSummary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date createdOn;

    @ManyToOne(targetEntity=Person.class, fetch = FetchType.EAGER)
    @JoinColumn(name="createdByPersonId")
    private Person createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date modifiedOn;

    @ManyToOne(targetEntity=Person.class, fetch = FetchType.EAGER)
    @JoinColumn(name="modifiedByPersonId")
    private Person modifiedBy;


}
