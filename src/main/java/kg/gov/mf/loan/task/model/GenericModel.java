package kg.gov.mf.loan.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class GenericModel extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = -3307436748176180347L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@JsonIgnore
    private Long version = 1L;

    @JsonIgnore
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "record_status", columnDefinition = "int default 1")
    private int record_status = 1;

    //region GET-SET
    public GenericModel() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersion() {
        return version;
    }

    public int getRecord_status() {
        return record_status;
    }

    public void setRecord_status(int record_status) {
        this.record_status = record_status;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) return true;
        if (!(other instanceof GenericModel)) {
            return false;
        }
        GenericModel genericModel = (GenericModel) other;
        return uuid.equals(genericModel.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, uuid);
    }
    //endregion
}
