package kg.gov.mf.loan.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class GenericModel implements Serializable {

	private static final long serialVersionUID = -3307436748176180347L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonIgnore
    private Long version = 1L;

    @JsonIgnore
    @NaturalId
    private String uuid = UUID.randomUUID().toString();

    //region HIDDEN
    public GenericModel() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersion() {
        return version;
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
        return Objects.hash(id, version);
    }
    //endregion
}
