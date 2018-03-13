package kg.gov.mf.loan.task.model;

import kg.gov.mf.loan.admin.sys.model.User;
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
    private long identifiedByUserId;

    //The date the issue occurred.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date identifiedDate;

    //The person assigned to resolve this issue.
    private long assignedToUserId;

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
    @Column(nullable=true)
    private Date actualResolutionDate;

    private String resolutionSummary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date createdOn;

    @ManyToOne(targetEntity=User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="createdByUserId")
    private User createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date modifiedOn;

    private long modifiedByUserId;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIdentifiedByUserId() {
        return identifiedByUserId;
    }

    public void setIdentifiedByUserId(long identifiedByUserId) {
        this.identifiedByUserId = identifiedByUserId;
    }

    public Date getIdentifiedDate() {
        return identifiedDate;
    }

    public void setIdentifiedDate(Date identifiedDate) {
        this.identifiedDate = identifiedDate;
    }

    public long getAssignedToUserId() {
        return assignedToUserId;
    }

    public void setAssignedToUserId(long assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public Date getTargetResolutionDate() {
        return targetResolutionDate;
    }

    public void setTargetResolutionDate(Date targetResolutionDate) {
        this.targetResolutionDate = targetResolutionDate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Date getActualResolutionDate() {
        return actualResolutionDate;
    }

    public void setActualResolutionDate(Date actualResolutionDate) {
        this.actualResolutionDate = actualResolutionDate;
    }

    public String getResolutionSummary() {
        return resolutionSummary;
    }

    public void setResolutionSummary(String resolutionSummary) {
        this.resolutionSummary = resolutionSummary;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public long getModifiedByUserId() {
        return modifiedByUserId;
    }

    public void setModifiedByUserId(long modifiedByUserId) {
        this.modifiedByUserId = modifiedByUserId;
    }
}
