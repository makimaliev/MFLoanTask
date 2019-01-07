package kg.gov.mf.loan.task.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Catalog extends GenericModel implements Comparable<Catalog>
{
	private String name;
    private String internalName;

    //region GET-SET
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInternalName() {
        return internalName;
    }
    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    @Override
    public int compareTo(Catalog catalog)
    {
        if (getName() == null || catalog.getName() == null) {
            return 0;
        }
        return getName().compareTo(catalog.getName());
    }
    //endregion
}
