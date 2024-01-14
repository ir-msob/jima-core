import {Criteria} from "./criteria";
import BaseCriteriaAbstract = Criteria.BaseCriteriaAbstract;

/**
 * This class extends the BaseCriteriaAbstract class with a generic ID.
 * It can be used as a base class for other criteria classes that require a generic ID.
 * It inherits all the properties and methods from the BaseCriteriaAbstract class.
 */
export class SampleCriteria<ID> extends BaseCriteriaAbstract<ID> {
}
