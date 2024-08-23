package ir.msob.jima.core.test;

import ir.msob.jima.core.commons.model.audit.AuditDomain;
import ir.msob.jima.core.commons.model.characteristic.Characteristic;
import ir.msob.jima.core.commons.model.keyvalue.KeyValue;
import ir.msob.jima.core.commons.model.relateddomain.RelatedDomain;
import ir.msob.jima.core.commons.model.relatedparty.RelatedParty;
import ir.msob.jima.core.commons.model.timeperiod.TimePeriod;
import org.assertj.core.api.Assertions;

import java.io.Serializable;

/**
 * The `CoreTestAssert` class provides a set of static methods for performing assertions in unit tests.
 * Each method is designed to assert specific properties or attributes of various model objects such as TimePeriod,
 * Characteristic, KeyValue, RelatedDomain, RelatedParty, and AuditDomain.
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
        Assertions.assertThat(before.getStart()).isEqualTo(after.getStart());
        Assertions.assertThat(before.getEnd()).isEqualTo(after.getEnd());
    }

    /**
     * Asserts the equality of optional Characteristic objects.
     *
     * @param before The Characteristic before an operation.
     * @param after  The Characteristic after an operation.
     */
    public static void assertOptionalCharacteristic(Characteristic before, Characteristic after) {
        Assertions.assertThat(before.getValue()).isEqualTo(after.getValue());
    }

    /**
     * Asserts the equality of mandatory Characteristic objects.
     *
     * @param before The Characteristic before an operation.
     * @param after  The Characteristic after an operation.
     */
    public static void assertMandatoryCharacteristic(Characteristic before, Characteristic after) {
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
     * Asserts the equality of optional RelatedDomain objects.
     *
     * @param before The RelatedDomain before an operation.
     * @param after  The RelatedDomain after an operation.
     * @param <ID>   The type of the identifier.
     */
    public static <ID extends Comparable<ID> & Serializable> void assertOptionalRelatedDomain(RelatedDomain<ID> before, RelatedDomain<ID> after) {
        Assertions.assertThat(before.getRole()).isEqualTo(after.getRole());
        Assertions.assertThat(before.getReferringType()).isEqualTo(after.getReferringType());
    }

    /**
     * Asserts the equality of mandatory RelatedDomain objects.
     *
     * @param before The RelatedDomain before an operation.
     * @param after  The RelatedDomain after an operation.
     * @param <ID>   The type of the identifier.
     */
    public static <ID extends Comparable<ID> & Serializable> void assertMandatoryRelatedDomain(RelatedDomain<ID> before, RelatedDomain<ID> after) {
        Assertions.assertThat(before.getObjectType()).isEqualTo(after.getObjectType());
        Assertions.assertThat(before.getObjectId()).isEqualTo(after.getObjectId());
    }

    /**
     * Asserts the equality of optional RelatedParty objects.
     *
     * @param before The RelatedParty before an operation.
     * @param after  The RelatedParty after an operation.
     */
    public static void assertOptionalRelatedParty(RelatedParty before, RelatedParty after) {
        Assertions.assertThat(before.getRole()).isEqualTo(after.getRole());
        Assertions.assertThat(before.getReferringType()).isEqualTo(after.getReferringType());
    }

    /**
     * Asserts the equality of mandatory RelatedParty objects.
     *
     * @param before The RelatedParty before an operation.
     * @param after  The RelatedParty after an operation.
     */
    public static void assertMandatoryRelatedParty(RelatedParty before, RelatedParty after) {
        Assertions.assertThat(before.getObjectType()).isEqualTo(after.getObjectType());
        Assertions.assertThat(before.getObjectId()).isEqualTo(after.getObjectId());
    }

    /**
     * Asserts the equality of mandatory AuditDomain objects.
     *
     * @param before The AuditDomain before an operation.
     * @param after  The AuditDomain after an operation.
     */
    public static void assertMandatoryAuditDomain(AuditDomain before, AuditDomain after) {
        Assertions.assertThat(before.getRelatedPartyId()).isEqualTo(after.getRelatedPartyId());
        Assertions.assertThat(before.getActionDate()).isEqualTo(after.getActionDate());
        Assertions.assertThat(before.getActionType()).isEqualTo(after.getActionType());
    }
}
