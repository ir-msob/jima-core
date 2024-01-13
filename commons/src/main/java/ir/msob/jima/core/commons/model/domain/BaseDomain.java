package ir.msob.jima.core.commons.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.msob.jima.core.commons.model.BaseModel;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @param <ID>
 * @author Yaqub Abdi
 */
public interface BaseDomain<ID extends Comparable<ID> & Serializable> extends BaseModel, Comparable<BaseDomain<ID>> {

    /**
     * Get domain id
     *
     * @return
     */
    @Transient
    @JsonIgnore
    ID getDomainId();

    /**
     * Set domain id
     */
    void setDomainId(ID id);

    /**
     * Get domain id field name
     *
     * @return
     */
    @Transient
    @JsonIgnore
    String getDomainIdName();

    @Override
    default int compareTo(BaseDomain<ID> o) {
        if (this == o) {
            return 0;
        }

        if (o != null && (this.getDomainId() != null && o.getDomainId() != null)) {
            return this.getDomainId().compareTo(o.getDomainId());

        }

        return Comparator
                .comparing(System::identityHashCode)
                .compare(this, o);
    }

}
