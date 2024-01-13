package ir.msob.jima.core.commons.security;

import ir.msob.jima.core.commons.model.dto.ModelType;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Yaqub Abdi
 */
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseUser<ID extends Comparable<ID> & Serializable> extends ModelType {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 4945340068393543863L;

    private ID id;
    private ID sessionId;
    private String username;
    private SortedSet<String> roles = new TreeSet<>();
    private String audience;
}
