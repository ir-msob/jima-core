package ir.msob.jima.core.test;

import com.google.common.collect.Sets;
import ir.msob.jima.core.commons.embeddeddomain.auditdomain.AuditDomainActionType;
import ir.msob.jima.core.commons.embeddeddomain.relatedobject.relateddomain.RelatedDomainAbstract;
import ir.msob.jima.core.commons.embeddeddomain.relatedobject.relatedparty.RelatedPartyAbstract;
import ir.msob.jima.core.commons.shared.DataType;
import ir.msob.jima.core.commons.shared.auditinfo.AuditInfo;
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

    // AuditInfo constants
    public static final AuditInfo DEFAULT_REQUIRED_AUDIT_INFO = AuditInfo.builder().createdAt(DEFAULT_INSTANT).createdBy(DEFAULT_STRING).build();
    public static final AuditInfo UPDATED_REQUIRED_AUDIT_INFO = AuditInfo.builder().createdAt(UPDATED_INSTANT).createdBy(UPDATED_STRING).build();
    public static final AuditInfo DEFAULT_AUDIT_INFO = AuditInfo.builder().createdAt(DEFAULT_INSTANT).createdBy(DEFAULT_STRING).updatedAt(DEFAULT_INSTANT).updatedBy(DEFAULT_STRING).build();
    public static final AuditInfo UPDATED_AUDIT_INFO = AuditInfo.builder().createdAt(UPDATED_INSTANT).createdBy(UPDATED_STRING).updatedAt(UPDATED_INSTANT).updatedBy(UPDATED_STRING).build();

    // AuditDomainActionType constants
    public static final AuditDomainActionType DEFAULT_AUDIT_DOMAIN_ACTION_TYPE = AuditDomainActionType.CREATE;
    public static final AuditDomainActionType UPDATED_AUDIT_DOMAIN_ACTION_TYPE = AuditDomainActionType.UPDATE;
    public static final SortedSet<AuditDomainActionType> DEFAULT_AUDIT_DOMAIN_ACTION_TYPES = Sets.newTreeSet(Collections.singleton(DEFAULT_AUDIT_DOMAIN_ACTION_TYPE));
    public static final SortedSet<AuditDomainActionType> UPDATED_AUDIT_DOMAIN_ACTION_TYPES = Sets.newTreeSet(Collections.singleton(UPDATED_AUDIT_DOMAIN_ACTION_TYPE));

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
     * Initializes the default and updated IDs and embeddeddomain entities.
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
