package ir.msob.jima.core.test;

import ir.msob.jima.core.commons.child.auditdomain.AuditDomainAbstract;
import ir.msob.jima.core.commons.child.characteristic.Characteristic;
import ir.msob.jima.core.commons.child.relatedobject.relateddomain.RelatedDomainAbstract;
import ir.msob.jima.core.commons.child.relatedobject.relatedparty.RelatedPartyAbstract;
import ir.msob.jima.core.commons.shared.keyvalue.KeyValue;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriod;
import org.assertj.core.api.Assertions;

import java.io.Serializable;

/**
 * The `CoreTestAssert` class provides a set of static methods for performing assertions in unit tests.
 * Each method is designed to assert specific properties or attributes of various model objects such as TimePeriod,
 * Characteristic, KeyValue, RelatedDomainAbstract, RelatedPartyAbstract, and AuditDomainAbstract.
 */
public class CoreTestAssertion {
    private CoreTestAssertion() {
    }

    /**
     * Asserts the equality of optional TimePeriod objects.
     *
     * @param before The TimePeriod before an operation.
     * @param after  The TimePeriod after an operation.
     */
    public static void assertOptionalTimePeriod(TimePeriod before, TimePeriod after) {
        Assertions.assertThat(before.getStartDate()).isEqualTo(after.getStartDate());
        Assertions.assertThat(before.getEndDate()).isEqualTo(after.getEndDate());
    }

    /**
     * Asserts the equality of optional Characteristic objects.
     *
     * @param before The Characteristic before an operation.
     * @param after  The Characteristic after an operation.
     */
    public static <ID extends Comparable<ID> & Serializable> void assertOptionalCharacteristic(Characteristic<ID> before, Characteristic<ID> after) {
        Assertions.assertThat(before.getValue()).isEqualTo(after.getValue());
    }

    /**
     * Asserts the equality of mandatory Characteristic objects.
     *
     * @param before The Characteristic before an operation.
     * @param after  The Characteristic after an operation.
     */
    public static <ID extends Comparable<ID> & Serializable> void assertMandatoryCharacteristic(Characteristic<ID> before, Characteristic<ID> after) {
        Assertions.assertThat(before.getKey()).isEqualTo(after.getKey());
        Assertions.assertThat(before.getDataType()).isEqualTo(after.getDataType());
    }

    /**
     * Asserts the equality of optional KeyValue objects.
     *
     * @param before The KeyValue before an operation.
     * @param after  The KeyValue after an operation.
     * @param <K>    The type of the key.
     * @param <V>    The type of the value.
     */
    public static <K extends Comparable<K> & Serializable, V extends Serializable> void assertOptionalKeyValue(KeyValue<K, V> before, KeyValue<K, V> after) {
        Assertions.assertThat(before.getKey()).isEqualTo(after.getKey());
        Assertions.assertThat(before.getValue()).isEqualTo(after.getValue());
    }

    /**
     * Asserts the equality of optional RelatedDomainAbstract objects.
     *
     * @param before The RelatedDomainAbstract before an operation.
     * @param after  The RelatedDomainAbstract after an operation.
     * @param <ID>   The type of the identifier.
     */
    public static <ID extends Comparable<ID> & Serializable> void assertOptionalRelatedDomain(RelatedDomainAbstract<ID> before, RelatedDomainAbstract<ID> after) {
        Assertions.assertThat(before.getRole()).isEqualTo(after.getRole());
        Assertions.assertThat(before.getReferringType()).isEqualTo(after.getReferringType());
    }

    /**
     * Asserts the equality of mandatory RelatedDomainAbstract objects.
     *
     * @param before The RelatedDomainAbstract before an operation.
     * @param after  The RelatedDomainAbstract after an operation.
     * @param <ID>   The type of the identifier.
     */
    public static <ID extends Comparable<ID> & Serializable> void assertMandatoryRelatedDomain(RelatedDomainAbstract<ID> before, RelatedDomainAbstract<ID> after) {
        Assertions.assertThat(before.getName()).isEqualTo(after.getName());
        Assertions.assertThat(before.getRelatedId()).isEqualTo(after.getRelatedId());
    }

    /**
     * Asserts the equality of optional RelatedPartyAbstract objects.
     *
     * @param before The RelatedPartyAbstract before an operation.
     * @param after  The RelatedPartyAbstract after an operation.
     */
    public static <ID extends Comparable<ID> & Serializable> void assertOptionalRelatedParty(RelatedPartyAbstract<ID> before, RelatedPartyAbstract<ID> after) {
        Assertions.assertThat(before.getRole()).isEqualTo(after.getRole());
        Assertions.assertThat(before.getReferringType()).isEqualTo(after.getReferringType());
    }

    /**
     * Asserts the equality of mandatory RelatedPartyAbstract objects.
     *
     * @param before The RelatedPartyAbstract before an operation.
     * @param after  The RelatedPartyAbstract after an operation.
     */
    public static <ID extends Comparable<ID> & Serializable> void assertMandatoryRelatedParty(RelatedPartyAbstract<ID> before, RelatedPartyAbstract<ID> after) {
        Assertions.assertThat(before.getName()).isEqualTo(after.getName());
        Assertions.assertThat(before.getRelatedId()).isEqualTo(after.getRelatedId());
    }

    /**
     * Asserts the equality of mandatory AuditDomainAbstract objects.
     *
     * @param before The AuditDomainAbstract before an operation.
     * @param after  The AuditDomainAbstract after an operation.
     */
    public static <ID extends Comparable<ID> & Serializable, RP extends RelatedPartyAbstract<ID>> void assertMandatoryAuditDomain(AuditDomainAbstract<ID, RP> before, AuditDomainAbstract<ID, RP> after) {
        Assertions.assertThat(before.getRelatedParty()).isEqualTo(after.getRelatedParty());
        Assertions.assertThat(before.getActionDate()).isEqualTo(after.getActionDate());
        Assertions.assertThat(before.getActionType()).isEqualTo(after.getActionType());
    }
}
