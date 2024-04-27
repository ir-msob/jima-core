package ir.msob.jima.core.commons.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class SignatureProperties {
    private SortedSet<Algorithm> algorithms = new TreeSet<>();

    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Algorithm implements Comparable<Algorithm> {
        private String id;
        private String algorithm;
        private PrivateKey privateKey;
        private PublicKey publicKey;
        private boolean enabled = false;

        @Override
        public int compareTo(Algorithm o) {
            if (this == o) {
                return 0;
            }
            return Objects.compare(this.getId(), o.getId(), String::compareTo);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o == null)
                return false;

            if (o instanceof Algorithm that) {
                return Objects.equals(this.getId(), that.getId());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
