package ir.msob.jima.core.commons.childdomain.auditdomain;

/**
 * The 'AuditDomainActionType' enum represents the type of action performed on an audit domain.
 * It includes two constants: 'CREATE' and 'UPDATE'.
 * 'CREATE' is used when a new audit domain is created.
 * 'UPDATE' is used when an existing audit domain is updated.
 */
public enum AuditDomainActionType {
    /**
     * Represents the creation of a new audit domain.
     */
    CREATE,

    /**
     * Represents the update of an existing audit domain.
     */
    UPDATE
}