import {Domain} from "./domain";
import BaseDomainAbstract = Domain.BaseDomainAbstract;

/**
 * This class extends the BaseDomainAbstract class with a generic ID.
 * It can be used as a base class for other domain classes that require a generic ID.
 * It inherits all the properties and methods from the BaseDomainAbstract class.
 */
export class SampleDomain<ID> extends BaseDomainAbstract<ID> {
}
