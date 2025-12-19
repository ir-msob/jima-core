package ir.msob.jima.core.ral.hr.it.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.domain.DomainInfo;
import ir.msob.jima.core.it.domain.ProjectDomain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "test_domain")
@DomainInfo(domainName = TestDomain.DOMAIN_URI)
public class TestDomain implements ProjectDomain {

    @Transient
    public static final String DOMAIN_NAME = "TestDomain";

    @Transient
    public static final String DOMAIN_URI = "test-domain";

    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Column(name = "domain_field", nullable = false)
    private String domainField;

    // --- Embeddable example ---
    @Embedded
    private AddressInfo address;

    // --- ManyToOne (owner) ---
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    // --- OneToMany children (bidirectional) ---
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Child> children = new ArrayList<>();

    // --- ManyToMany tags via join table ---
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "test_domain_tag",
            joinColumns = @JoinColumn(name = "domain_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public TestDomain(String id) {
        this.id = id;
    }

    public enum FN {
        domainField
    }

    @Setter
    @Getter
    @ToString(callSuper = true)
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Embeddable
    public static class AddressInfo {
        @Column(name = "addr_street")
        private String street;

        @Column(name = "addr_city")
        private String city;

        @Column(name = "addr_zip")
        private String zip;
    }

    @Setter
    @Getter
    @ToString(callSuper = true)
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Entity
    @Table(name = "owner")
    public static class Owner {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;
        private String name;
    }

    @Setter
    @Getter
    @ToString(callSuper = true)
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Entity
    @Table(name = "child")
    public static class Child {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        private String name;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parent_id")
        private TestDomain parent;
    }

    @Setter
    @Getter
    @ToString(callSuper = true)
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Entity
    @Table(name = "tag")
    public static class Tag {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        @Column(unique = true)
        private String name;

        // equals/hashCode based on name if used in Set
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tag tag)) return false;
            return Objects.equals(name, tag.name);
        }
        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

}

