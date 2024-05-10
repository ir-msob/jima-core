package ir.msob.jima.core.test;

import com.google.common.collect.Sets;
import ir.msob.jima.core.commons.model.DataType;
import ir.msob.jima.core.commons.model.audit.AuditDomainActionType;
import ir.msob.jima.core.commons.model.characteristic.Characteristic;
import ir.msob.jima.core.commons.model.keyvalue.KeyValue;
import ir.msob.jima.core.commons.model.relateddomain.RelatedDomain;
import ir.msob.jima.core.commons.model.relatedparty.RelatedParty;
import ir.msob.jima.core.commons.model.timeperiod.TimePeriod;

import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;

public class CoreTestData {

    public static final String DEFAULT_STRING = "DEFAULT_STRING";
    public static final String UPDATED_STRING = "UPDATED_STRING";
    public static final Collection<String> DEFAULT_STRINGS = Collections.singleton(DEFAULT_STRING);
    public static final Collection<String> UPDATED_STRINGS = Collections.singleton(UPDATED_STRING);
    public static final DataType DEFAULT_DATA_TYPE = DataType.STRING;
    public static final DataType UPDATED_DATA_TYPE = DataType.INTEGER;
    public static final Collection<DataType> DEFAULT_DATA_TYPES = Collections.singleton(DataType.STRING);
    public static final Collection<DataType> UPDATED_DATA_TYPES = Collections.singleton(DataType.INTEGER);
    public static final String DEFAULT_MIME_TYPE = "text/plain";
    public static final String UPDATED_MIME_TYPE = "text/xml";
    public static final Collection<String> DEFAULT_MIME_TYPES = Collections.singleton(DEFAULT_MIME_TYPE);
    public static final Collection<String> UPDATED_MIME_TYPES = Collections.singleton(UPDATED_MIME_TYPE);
    public static final Boolean DEFAULT_BOOLEAN = Boolean.TRUE;
    public static final Boolean UPDATED_BOOLEAN = Boolean.FALSE;
    public static final Collection<Boolean> DEFAULT_BOOLEANS = Collections.singleton(DEFAULT_BOOLEAN);
    public static final Collection<Boolean> UPDATED_BOOLEANS = Collections.singleton(UPDATED_BOOLEAN);
    public static final Integer DEFAULT_INTEGER = 1;
    public static final Integer UPDATED_INTEGER = 2;
    public static final Collection<Integer> DEFAULT_INTEGERS = Collections.singleton(DEFAULT_INTEGER);
    public static final Collection<Integer> UPDATED_INTEGERS = Collections.singleton(UPDATED_INTEGER);
    public static final Long DEFAULT_LONG = 1L;
    public static final Long UPDATED_LONG = 2L;
    public static final Collection<Long> DEFAULT_LONGS = Collections.singleton(DEFAULT_LONG);
    public static final Collection<Long> UPDATED_LONGS = Collections.singleton(UPDATED_LONG);
    public static final Instant DEFAULT_INSTANT = initDefaultInstant();
    public static final Instant UPDATED_INSTANT = initUpdatedInstant();
    public static final Collection<Instant> DEFAULT_INSTANTS = Collections.singleton(DEFAULT_INSTANT);
    public static final Collection<Instant> UPDATED_INSTANTS = Collections.singleton(UPDATED_INSTANT);
    public static final AuditDomainActionType DEFAULT_AUDIT_DOMAIN_ACTION_TYPE = AuditDomainActionType.CREATE;
    public static final AuditDomainActionType UPDATED_AUDIT_DOMAIN_ACTION_TYPE = AuditDomainActionType.UPDATE;
    public static final SortedSet<AuditDomainActionType> DEFAULT_AUDIT_DOMAIN_ACTION_TYPES = Sets.newTreeSet(Collections.singleton(DEFAULT_AUDIT_DOMAIN_ACTION_TYPE));
    public static final SortedSet<AuditDomainActionType> UPDATED_AUDIT_DOMAIN_ACTION_TYPES = Sets.newTreeSet(Collections.singleton(UPDATED_AUDIT_DOMAIN_ACTION_TYPE));
    public static final Characteristic DEFAULT_REQUIRED_CHARACTERISTIC = initDefaultRequiredCharacteristic();
    public static final SortedSet<Characteristic> DEFAULT_REQUIRED_CHARACTERISTICS = Sets.newTreeSet(Collections.singleton(DEFAULT_REQUIRED_CHARACTERISTIC));
    public static final Characteristic DEFAULT_CHARACTERISTIC = initDefaultCharacteristic();
    public static final SortedSet<Characteristic> DEFAULT_CHARACTERISTICS = Sets.newTreeSet(Collections.singleton(DEFAULT_CHARACTERISTIC));
    public static final KeyValue<String, String> DEFAULT_STRING_KEY_VALUE = new KeyValue<>(DEFAULT_STRING, DEFAULT_STRING);
    public static final Collection<KeyValue<String, String>> DEFAULT_STRING_KEY_VALUES = Collections.singleton(DEFAULT_STRING_KEY_VALUE);
    public static final KeyValue<String, String> UPDATED_STRING_KEY_VALUE = new KeyValue<>(UPDATED_STRING, UPDATED_STRING);
    public static final Collection<KeyValue<String, String>> UPDATED_STRING_KEY_VALUES = Collections.singleton(UPDATED_STRING_KEY_VALUE);
    public static final KeyValue<String, Integer> DEFAULT_INTEGER_KEY_VALUE = new KeyValue<>(DEFAULT_STRING, DEFAULT_INTEGER);
    public static final Collection<KeyValue<String, Integer>> DEFAULT_INTEGER_KEY_VALUES = Collections.singleton(DEFAULT_INTEGER_KEY_VALUE);
    public static final KeyValue<String, Integer> UPDATED_INTEGER_KEY_VALUE = new KeyValue<>(UPDATED_STRING, UPDATED_INTEGER);
    public static final Collection<KeyValue<String, Integer>> UPDATED_INTEGER_KEY_VALUES = Collections.singleton(UPDATED_INTEGER_KEY_VALUE);
    public static Object DEFAULT_ID;
    public static Object UPDATED_ID;
    public static final String DEFAULT_PARTY_ID = DEFAULT_STRING;
    public static final String UPDATED_PARTY_ID = UPDATED_STRING;
    public static RelatedDomain<?> DEFAULT_RELATED_DOMAIN;
    public static RelatedDomain<?> DEFAULT_REQUIRED_RELATED_DOMAIN;
    public static TimePeriod DEFAULT_TIME_PERIOD = initDefaultTimePeriod();
    public static TimePeriod UPDATED_TIME_PERIOD = initUpdatedTimePeriod();
    public static Collection<TimePeriod> DEFAULT_TIME_PERIODS = Collections.singleton(DEFAULT_TIME_PERIOD);
    public static Collection<TimePeriod> UPDATED_TIME_PERIODS = Collections.singleton(UPDATED_TIME_PERIOD);
    public static RelatedParty DEFAULT_RELATED_PARTY;
    public static RelatedParty DEFAULT_REQUIRED_RELATED_PARTY;

    private CoreTestData() {
    }

    public static SortedSet<RelatedParty> DEFAULT_REQUIRED_RELATED_PARTIES() {
        return Sets.newTreeSet(Collections.singleton(DEFAULT_REQUIRED_RELATED_PARTY));
    }

    public static void UPDATED_REQUIRED_RELATED_PARTIES(SortedSet<RelatedParty> relatedParties) {
        relatedParties.forEach(CoreTestData::UPDATED_REQUIRED_RELATED_PARTY);
    }

    public static SortedSet<RelatedParty> DEFAULT_RELATED_PARTIES() {
        return Sets.newTreeSet(Collections.singleton(DEFAULT_RELATED_PARTY));
    }

    public static void UPDATED_RELATED_PARTIES(SortedSet<RelatedParty> relatedParties) {
        relatedParties.forEach(CoreTestData::UPDATED_RELATED_PARTY);
    }

    public static <ID extends Comparable<ID> & Serializable> SortedSet<RelatedDomain<ID>> DEFAULT_REQUIRED_RELATED_DOMAINS() {
        return Sets.newTreeSet(Collections.singleton((RelatedDomain<ID>) DEFAULT_REQUIRED_RELATED_DOMAIN));
    }

    public static <ID extends Comparable<ID> & Serializable> void UPDATED_REQUIRED_RELATED_DOMAINS(SortedSet<RelatedDomain<ID>> relatedDomains) {
        relatedDomains.forEach(CoreTestData::UPDATED_REQUIRED_RELATED_DOMAIN);
    }

    public static <ID extends Comparable<ID> & Serializable> SortedSet<RelatedDomain<ID>> DEFAULT_RELATED_DOMAINS() {
        return Sets.newTreeSet(Collections.singleton((RelatedDomain<ID>) DEFAULT_RELATED_DOMAIN));
    }

    public static <ID extends Comparable<ID> & Serializable> void UPDATED_RELATED_DOMAINS(SortedSet<RelatedDomain<ID>> relatedDomains) {
        relatedDomains.forEach(CoreTestData::UPDATED_RELATED_DOMAIN);
    }

    public static <ID extends Comparable<ID> & Serializable> void init(ID defaultId, ID updatedId) {
        DEFAULT_ID = defaultId;
        UPDATED_ID = updatedId;

        initDefaultRelatedDomain();

        initDefaultRequiredRelatedDomain();

        initDefaultRelatedParty();

        initDefaultRequiredRelatedParty();
    }

    public static TimePeriod initDefaultTimePeriod() {
        TimePeriod timePeriod = new TimePeriod();

        Calendar startCalendar = getDayCalendar();
        startCalendar.add(Calendar.DATE, 2);
        timePeriod.setStart(startCalendar.toInstant());

        Calendar endCalendar = getDayCalendar();
        endCalendar.add(Calendar.DATE, 3);
        timePeriod.setEnd(endCalendar.toInstant());

        return timePeriod;
    }

    public static TimePeriod initUpdatedTimePeriod() {
        TimePeriod timePeriod = new TimePeriod();
        Calendar startCalendar = getDayCalendar();
        timePeriod.setStart(startCalendar.toInstant());

        Calendar endCalendar = getDayCalendar();
        endCalendar.add(Calendar.DATE, 1);
        timePeriod.setEnd(endCalendar.toInstant());

        return timePeriod;
    }

    public static void initDefaultRelatedParty() {
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setRelatedPartyType(DEFAULT_STRING);
        relatedParty.setRelatedPartyId(DEFAULT_PARTY_ID);
        relatedParty.setRole(DEFAULT_STRING);
        relatedParty.setReferredType(DEFAULT_STRING);
        DEFAULT_RELATED_PARTY = relatedParty;
    }

    public static void UPDATED_RELATED_PARTY(RelatedParty relatedParty) {
        relatedParty.setRelatedPartyType(UPDATED_STRING);
        relatedParty.setRelatedPartyId(UPDATED_PARTY_ID);
        relatedParty.setRole(UPDATED_STRING);
        relatedParty.setReferredType(UPDATED_STRING);
    }

    public static void initDefaultRequiredRelatedParty() {
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setRelatedPartyType(DEFAULT_STRING);
        relatedParty.setRelatedPartyId(DEFAULT_PARTY_ID);
        DEFAULT_REQUIRED_RELATED_PARTY = relatedParty;
    }

    public static void UPDATED_REQUIRED_RELATED_PARTY(RelatedParty relatedParty) {
        relatedParty.setRelatedPartyType(UPDATED_STRING);
        relatedParty.setRelatedPartyId(UPDATED_PARTY_ID);
    }

    public static <ID extends Comparable<ID> & Serializable> void initDefaultRelatedDomain() {
        RelatedDomain<ID> relatedDomain = new RelatedDomain<>();
        relatedDomain.setRelatedDomainType(DEFAULT_STRING);
        relatedDomain.setRelatedDomainId((ID) DEFAULT_ID);
        relatedDomain.setRole(DEFAULT_STRING);
        relatedDomain.setReferredType(DEFAULT_STRING);
        DEFAULT_RELATED_DOMAIN = relatedDomain;
    }

    public static <ID extends Comparable<ID> & Serializable> void UPDATED_RELATED_DOMAIN(RelatedDomain<ID> relatedDomain) {
        relatedDomain.setRelatedDomainType(UPDATED_STRING);
        relatedDomain.setRelatedDomainId((ID) UPDATED_ID);
        relatedDomain.setRole(UPDATED_STRING);
        relatedDomain.setReferredType(UPDATED_STRING);
    }

    public static <ID extends Comparable<ID> & Serializable> void initDefaultRequiredRelatedDomain() {
        RelatedDomain<ID> relatedDomain = new RelatedDomain<>();
        relatedDomain.setRelatedDomainType(DEFAULT_STRING);
        relatedDomain.setRelatedDomainId((ID) DEFAULT_ID);
        DEFAULT_REQUIRED_RELATED_DOMAIN = relatedDomain;
    }

    public static <ID extends Comparable<ID> & Serializable> void UPDATED_REQUIRED_RELATED_DOMAIN(RelatedDomain<ID> relatedDomain) {
        relatedDomain.setRelatedDomainType(UPDATED_STRING);
        relatedDomain.setRelatedDomainId((ID) UPDATED_ID);
    }

    public static Characteristic initDefaultRequiredCharacteristic() {
        Characteristic characteristic = new Characteristic();
        characteristic.setKey(DEFAULT_STRING);
        characteristic.setValue(DEFAULT_STRING);
        characteristic.setDataType(DEFAULT_DATA_TYPE);
        return characteristic;
    }

    public static void UPDATED_MANDATORY_CHARACTERISTIC(Characteristic characteristic) {
        characteristic.setKey(UPDATED_STRING);
        characteristic.setDataType(UPDATED_DATA_TYPE);
    }

    public static void UPDATED_MANDATORY_CHARACTERISTICS(SortedSet<Characteristic> characteristics) {
        characteristics.forEach(CoreTestData::UPDATED_MANDATORY_CHARACTERISTIC);
    }

    public static Characteristic initDefaultCharacteristic() {
        Characteristic characteristic = initDefaultRequiredCharacteristic();
        characteristic.setValue(DEFAULT_STRING);
        return characteristic;
    }

    public static void UPDATED_CHARACTERISTIC(Characteristic characteristic) {
        UPDATED_MANDATORY_CHARACTERISTIC(characteristic);
        characteristic.setValue(UPDATED_INTEGER);
    }

    public static void UPDATED_CHARACTERISTICS(SortedSet<Characteristic> characteristics) {
        characteristics.forEach(CoreTestData::UPDATED_CHARACTERISTIC);
    }

    public static Instant initDefaultInstant() {
        Calendar calendar = getDayCalendar();
        return calendar.toInstant();
    }

    public static Instant initUpdatedInstant() {
        Calendar calendar = getDayCalendar();
        calendar.add(Calendar.DATE, 1);
        return calendar.toInstant();
    }

    public static Calendar getDayCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar;
    }

}
