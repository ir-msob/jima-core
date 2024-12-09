package ir.msob.jima.core.test;

import com.google.common.collect.Sets;
import ir.msob.jima.core.commons.child.auditdomain.AuditDomainActionType;
import ir.msob.jima.core.commons.child.characteristic.Characteristic;
import ir.msob.jima.core.commons.child.relatedobject.relateddomain.RelatedDomainAbstract;
import ir.msob.jima.core.commons.child.relatedobject.relatedparty.RelatedPartyAbstract;
import ir.msob.jima.core.commons.shared.DataType;
import ir.msob.jima.core.commons.shared.keyvalue.KeyValue;
import ir.msob.jima.core.commons.shared.timeperiod.TimePeriod;

import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;

/**
 * Utility class for providing default and updated test data.
 * This class contains constants and methods for initializing test data.
 */
public class CoreTestData {

    // String constants
    public static final String DEFAULT_STRING = "DEFAULT_STRING";
    public static final String UPDATED_STRING = "UPDATED_STRING";
    public static final Collection<String> DEFAULT_STRINGS = Collections.singleton(DEFAULT_STRING);
    public static final Collection<String> UPDATED_STRINGS = Collections.singleton(UPDATED_STRING);

    // DataType constants
    public static final DataType DEFAULT_DATA_TYPE = DataType.STRING;
    public static final DataType UPDATED_DATA_TYPE = DataType.INTEGER;
    public static final Collection<DataType> DEFAULT_DATA_TYPES = Collections.singleton(DataType.STRING);
    public static final Collection<DataType> UPDATED_DATA_TYPES = Collections.singleton(DataType.INTEGER);

    // Mime Type constants
    public static final String DEFAULT_MIME_TYPE = "text/plain";
    public static final String UPDATED_MIME_TYPE = "text/xml";
    public static final Collection<String> DEFAULT_MIME_TYPES = Collections.singleton(DEFAULT_MIME_TYPE);
    public static final Collection<String> UPDATED_MIME_TYPES = Collections.singleton(UPDATED_MIME_TYPE);

    // Boolean constants
    public static final Boolean DEFAULT_BOOLEAN = Boolean.TRUE;
    public static final Boolean UPDATED_BOOLEAN = Boolean.FALSE;
    public static final Collection<Boolean> DEFAULT_BOOLEANS = Collections.singleton(DEFAULT_BOOLEAN);
    public static final Collection<Boolean> UPDATED_BOOLEANS = Collections.singleton(UPDATED_BOOLEAN);

    // Integer constants
    public static final Integer DEFAULT_INTEGER = 1;
    public static final Integer UPDATED_INTEGER = 2;
    public static final Collection<Integer> DEFAULT_INTEGERS = Collections.singleton(DEFAULT_INTEGER);
    public static final Collection<Integer> UPDATED_INTEGERS = Collections.singleton(UPDATED_INTEGER);

    // Long constants
    public static final Long DEFAULT_LONG = 1L;
    public static final Long UPDATED_LONG = 2L;
    public static final Collection<Long> DEFAULT_LONGS = Collections.singleton(DEFAULT_LONG);
    public static final Collection<Long> UPDATED_LONGS = Collections.singleton(UPDATED_LONG);

    // Instant constants
    public static final Instant DEFAULT_INSTANT = initDefaultInstant();
    public static final Instant UPDATED_INSTANT = initUpdatedInstant();
    public static final Collection<Instant> DEFAULT_INSTANTS = Collections.singleton(DEFAULT_INSTANT);
    public static final Collection<Instant> UPDATED_INSTANTS = Collections.singleton(UPDATED_INSTANT);

    // AuditDomainActionType constants
    public static final AuditDomainActionType DEFAULT_AUDIT_DOMAIN_ACTION_TYPE = AuditDomainActionType.CREATE;
    public static final AuditDomainActionType UPDATED_AUDIT_DOMAIN_ACTION_TYPE = AuditDomainActionType.UPDATE;
    public static final SortedSet<AuditDomainActionType> DEFAULT_AUDIT_DOMAIN_ACTION_TYPES = Sets.newTreeSet(Collections.singleton(DEFAULT_AUDIT_DOMAIN_ACTION_TYPE));
    public static final SortedSet<AuditDomainActionType> UPDATED_AUDIT_DOMAIN_ACTION_TYPES = Sets.newTreeSet(Collections.singleton(UPDATED_AUDIT_DOMAIN_ACTION_TYPE));

    // Characteristic constants
    public static final Characteristic<?> DEFAULT_REQUIRED_CHARACTERISTIC = initDefaultRequiredCharacteristic();
    public static final SortedSet<Characteristic<?>> DEFAULT_REQUIRED_CHARACTERISTICS = Sets.newTreeSet(Collections.singleton(DEFAULT_REQUIRED_CHARACTERISTIC));
    public static final Characteristic<?> DEFAULT_CHARACTERISTIC = initDefaultCharacteristic();
    public static final SortedSet<Characteristic<?>> DEFAULT_CHARACTERISTICS = Sets.newTreeSet(Collections.singleton(DEFAULT_CHARACTERISTIC));

    // KeyValue constants
    public static final KeyValue<String, String> DEFAULT_STRING_KEY_VALUE = new KeyValue<>(DEFAULT_STRING, DEFAULT_STRING);
    public static final Collection<KeyValue<String, String>> DEFAULT_STRING_KEY_VALUES = Collections.singleton(DEFAULT_STRING_KEY_VALUE);
    public static final KeyValue<String, String> UPDATED_STRING_KEY_VALUE = new KeyValue<>(UPDATED_STRING, UPDATED_STRING);
    public static final Collection<KeyValue<String, String>> UPDATED_STRING_KEY_VALUES = Collections.singleton(UPDATED_STRING_KEY_VALUE);
    public static final KeyValue<String, Integer> DEFAULT_INTEGER_KEY_VALUE = new KeyValue<>(DEFAULT_STRING, DEFAULT_INTEGER);
    public static final Collection<KeyValue<String, Integer>> DEFAULT_INTEGER_KEY_VALUES = Collections.singleton(DEFAULT_INTEGER_KEY_VALUE);
    public static final KeyValue<String, Integer> UPDATED_INTEGER_KEY_VALUE = new KeyValue<>(UPDATED_STRING, UPDATED_INTEGER);
    public static final Collection<KeyValue<String, Integer>> UPDATED_INTEGER_KEY_VALUES = Collections.singleton(UPDATED_INTEGER_KEY_VALUE);
    public static final String DEFAULT_PARTY_ID = DEFAULT_STRING;
    public static final String UPDATED_PARTY_ID = UPDATED_STRING;
    // TimePeriod constants
    public static final TimePeriod DEFAULT_TIME_PERIOD = initDefaultTimePeriod();
    public static final TimePeriod UPDATED_TIME_PERIOD = initUpdatedTimePeriod();
    public static final Collection<TimePeriod> DEFAULT_TIME_PERIODS = Collections.singleton(DEFAULT_TIME_PERIOD);
    public static final Collection<TimePeriod> UPDATED_TIME_PERIODS = Collections.singleton(UPDATED_TIME_PERIOD);
    // ID and Party ID constants
    public static Object DEFAULT_ID;
    public static Object UPDATED_ID;
    // RelatedDomainAbstract and RelatedPartyAbstract constants
    public static RelatedDomainAbstract<?> DEFAULT_RELATED_DOMAIN;
    public static RelatedDomainAbstract<?> DEFAULT_REQUIRED_RELATED_DOMAIN;
    public static RelatedPartyAbstract<?> DEFAULT_RELATED_PARTY;
    public static RelatedPartyAbstract<?> DEFAULT_REQUIRED_RELATED_PARTY;

    private CoreTestData() {
        // Private constructor to prevent instantiation
    }

    /**
     * Returns a sorted set of default required child parties.
     *
     * @return a sorted set of default required child parties
     */
    public static <ID extends Comparable<ID> & Serializable> SortedSet<RelatedPartyAbstract<ID>> DEFAULT_REQUIRED_RELATED_PARTIES() {
        return Sets.newTreeSet(Collections.singleton((RelatedPartyAbstract<ID>) DEFAULT_REQUIRED_RELATED_PARTY));
    }

    /**
     * Updates the provided sorted set of child parties with updated values.
     *
     * @param relatedParties the sorted set of child parties to update
     */
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_REQUIRED_RELATED_PARTIES(SortedSet<RelatedPartyAbstract<ID>> relatedParties) {
        relatedParties.forEach(CoreTestData::UPDATED_REQUIRED_RELATED_PARTY);
    }

    /**
     * Returns a sorted set of default child parties.
     *
     * @return a sorted set of default child parties
     */
    public static <ID extends Comparable<ID> & Serializable> SortedSet<RelatedPartyAbstract<ID>> DEFAULT_RELATED_PARTIES() {
        return Sets.newTreeSet(Collections.singleton((RelatedPartyAbstract<ID>) DEFAULT_RELATED_PARTY));
    }

    /**
     * Updates the provided sorted set of child parties with updated values.
     *
     * @param relatedParties the sorted set of child parties to update
     */
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_RELATED_PARTIES(SortedSet<RelatedPartyAbstract<ID>> relatedParties) {
        relatedParties.forEach(CoreTestData::UPDATED_RELATED_PARTY);
    }

    /**
     * Returns a sorted set of default required child domains.
     *
     * @param <ID> the type of the domain identifier
     * @return a sorted set of default required child domains
     */
    @SuppressWarnings("unchecked")
    public static <ID extends Comparable<ID> & Serializable> SortedSet<RelatedDomainAbstract<ID>> DEFAULT_REQUIRED_RELATED_DOMAINS() {
        return Sets.newTreeSet(Collections.singleton((RelatedDomainAbstract<ID>) DEFAULT_REQUIRED_RELATED_DOMAIN));
    }

    /**
     * Updates the provided sorted set of child domains with updated values.
     *
     * @param relatedDomains the sorted set of child domains to update
     * @param <ID>           the type of the domain identifier
     */
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_REQUIRED_RELATED_DOMAINS(SortedSet<RelatedDomainAbstract<ID>> relatedDomains) {
        relatedDomains.forEach(CoreTestData::UPDATED_REQUIRED_RELATED_DOMAIN);
    }

    /**
     * Returns a sorted set of default child domains.
     *
     * @param <ID> the type of the domain identifier
     * @return a sorted set of default child domains
     */
    @SuppressWarnings("unchecked")
    public static <ID extends Comparable<ID> & Serializable> SortedSet<RelatedDomainAbstract<ID>> DEFAULT_RELATED_DOMAINS() {
        return Sets.newTreeSet(Collections.singleton((RelatedDomainAbstract<ID>) DEFAULT_RELATED_DOMAIN));
    }

    /**
     * Updates the provided sorted set of child domains with updated values.
     *
     * @param relatedDomains the sorted set of child domains to update
     * @param <ID>           the type of the domain identifier
     */
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_RELATED_DOMAINS(SortedSet<RelatedDomainAbstract<ID>> relatedDomains) {
        relatedDomains.forEach(CoreTestData::UPDATED_RELATED_DOMAIN);
    }

    /**
     * Initializes the default and updated IDs and child entities.
     *
     * @param defaultId the default ID
     * @param updatedId the updated ID
     * @param <ID>      the type of the domain identifier
     */
    public static <ID extends Comparable<ID> & Serializable> void init(ID defaultId, ID updatedId) {
        DEFAULT_ID = defaultId;
        UPDATED_ID = updatedId;
    }

    /**
     * Initializes the default time period.
     *
     * @return the initialized default time period
     */
    public static TimePeriod initDefaultTimePeriod() {
        TimePeriod timePeriod = new TimePeriod();

        Calendar startCalendar = getDayCalendar();
        startCalendar.add(Calendar.DATE, 2);
        timePeriod.setStartDate(startCalendar.toInstant());

        Calendar endCalendar = getDayCalendar();
        endCalendar.add(Calendar.DATE, 3);
        timePeriod.setEndDate(endCalendar.toInstant());

        return timePeriod;
    }

    /**
     * Initializes the updated time period.
     *
     * @return the initialized updated time period
     */
    public static TimePeriod initUpdatedTimePeriod() {
        TimePeriod timePeriod = new TimePeriod();
        Calendar startCalendar = getDayCalendar();
        timePeriod.setStartDate(startCalendar.toInstant());

        Calendar endCalendar = getDayCalendar();
        endCalendar.add(Calendar.DATE, 1);
        timePeriod.setEndDate(endCalendar.toInstant());

        return timePeriod;
    }


    /**
     * Updates the provided child party with updated values.
     *
     * @param relatedParty the child party to update
     */
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_RELATED_PARTY(RelatedPartyAbstract<ID> relatedParty) {
        relatedParty.setName(UPDATED_STRING);
        relatedParty.setRelatedId(UPDATED_PARTY_ID);
        relatedParty.setRole(UPDATED_STRING);
        relatedParty.setReferringType(UPDATED_STRING);
    }

    /**
     * Updates the provided required child party with updated values.
     *
     * @param relatedParty the required child party to update
     */
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_REQUIRED_RELATED_PARTY(RelatedPartyAbstract<ID> relatedParty) {
        relatedParty.setName(UPDATED_STRING);
        relatedParty.setRelatedId(UPDATED_PARTY_ID);
    }

    /**
     * Updates the provided child domain with updated values.
     *
     * @param relatedDomain the child domain to update
     * @param <ID>          the type of the domain identifier
     */
    @SuppressWarnings("unchecked")
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_RELATED_DOMAIN(RelatedDomainAbstract<ID> relatedDomain) {
        relatedDomain.setName(UPDATED_STRING);
        relatedDomain.setRelatedId((ID) UPDATED_ID);
        relatedDomain.setRole(UPDATED_STRING);
        relatedDomain.setReferringType(UPDATED_STRING);
    }

    /**
     * Updates the provided required child domain with updated values.
     *
     * @param relatedDomain the required child domain to update
     * @param <ID>          the type of the domain identifier
     */
    @SuppressWarnings("unchecked")
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_REQUIRED_RELATED_DOMAIN(RelatedDomainAbstract<ID> relatedDomain) {
        relatedDomain.setName(UPDATED_STRING);
        relatedDomain.setRelatedId((ID) UPDATED_ID);
    }

    /**
     * Initializes the default required characteristic.
     *
     * @return the initialized default required characteristic
     */
    public static <ID extends Comparable<ID> & Serializable> Characteristic<ID> initDefaultRequiredCharacteristic() {
        Characteristic<ID> characteristic = new Characteristic<>();
        characteristic.setKey(DEFAULT_STRING);
        characteristic.setValue(DEFAULT_STRING);
        characteristic.setDataType(DEFAULT_DATA_TYPE.name());
        return characteristic;
    }

    /**
     * Updates the provided mandatory characteristic with updated values.
     *
     * @param characteristic the mandatory characteristic to update
     */
    public static void UPDATED_MANDATORY_CHARACTERISTIC(Characteristic<?> characteristic) {
        characteristic.setKey(UPDATED_STRING);
        characteristic.setDataType(UPDATED_DATA_TYPE.name());
    }

    /**
     * Updates the provided sorted set of mandatory characteristics with updated values.
     *
     * @param characteristics the sorted set of mandatory characteristics to update
     */
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_MANDATORY_CHARACTERISTICS(SortedSet<Characteristic<ID>> characteristics) {
        characteristics.forEach(CoreTestData::UPDATED_MANDATORY_CHARACTERISTIC);
    }

    /**
     * Initializes the default characteristic.
     *
     * @return the initialized default characteristic
     */
    public static <ID extends Comparable<ID> & Serializable> Characteristic<ID> initDefaultCharacteristic() {
        Characteristic<ID> characteristic = initDefaultRequiredCharacteristic();
        characteristic.setValue(DEFAULT_STRING);
        return characteristic;
    }

    /**
     * Updates the provided characteristic with updated values.
     *
     * @param characteristic the characteristic to update
     */
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_CHARACTERISTIC(Characteristic<ID> characteristic) {
        UPDATED_MANDATORY_CHARACTERISTIC(characteristic);
        characteristic.setValue(UPDATED_INTEGER);
    }

    /**
     * Updates the provided sorted set of characteristics with updated values.
     *
     * @param characteristics the sorted set of characteristics to update
     */
    public static <ID extends Comparable<ID> & Serializable> void UPDATED_CHARACTERISTICS(SortedSet<Characteristic<ID>> characteristics) {
        characteristics.forEach(CoreTestData::UPDATED_CHARACTERISTIC);
    }

    /**
     * Initializes the default instant.
     *
     * @return the initialized default instant
     */
    public static Instant initDefaultInstant() {
        Calendar calendar = getDayCalendar();
        return calendar.toInstant();
    }

    /**
     * Initializes the updated instant.
     *
     * @return the initialized updated instant
     */
    public static Instant initUpdatedInstant() {
        Calendar calendar = getDayCalendar();
        calendar.add(Calendar.DATE, 1);
        return calendar.toInstant();
    }

    /**
     * Returns a calendar instance set to the start of the day.
     *
     * @return a calendar instance set to the start of the day
     */
    public static Calendar getDayCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar;
    }
}
