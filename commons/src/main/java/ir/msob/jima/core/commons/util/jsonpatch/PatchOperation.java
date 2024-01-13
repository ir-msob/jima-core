package ir.msob.jima.core.commons.util.jsonpatch;

import lombok.*;

@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PatchOperation {
    private String operation;
    private String path;
    private Object value;
}
