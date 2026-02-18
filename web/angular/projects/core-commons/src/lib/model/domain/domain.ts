import {NullableT} from "../../constants";

/**
 * Namespace for Domain embeddeddomain interfaces and classes.
 */
export namespace Domain {
  /**
   * Interface for base domain.
   * It provides getter and setter methods for domainId.
   */
  export interface BaseDomain<ID> {
    /**
     * Getter for domainId.
     * @returns {NullableT<ID>} The domainId.
     */
    get domainId(): NullableT<ID>;

    /**
     * Setter for domainId.
     * @param {NullableT<ID>} id - The domainId to set.
     */
    set domainId(id: NullableT<ID>);
  }

  /**
   * Abstract class for base domain.
   * It implements BaseDomain interface.
   * It provides implementation for getter and setter methods of id and domainId.
   */
  export abstract class BaseDomainAbstract<ID> implements BaseDomain<ID> {
    private _id: NullableT<ID>;

    /**
     * Getter for id.
     * @returns {NullableT<ID>} The id.
     */
    get id(): NullableT<ID> {
      return this._id;
    }

    /**
     * Setter for id.
     * @param {NullableT<ID>} value - The id to set.
     */
    set id(value: NullableT<ID>) {
      this._id = value;
    }

    /**
     * Getter for domainId.
     * @returns {NullableT<ID>} The domainId.
     */
    get domainId(): NullableT<ID> {
      return this._id;
    }

    /**
     * Setter for domainId.
     * @param {NullableT<ID>} id - The domainId to set.
     */
    set domainId(id: NullableT<ID>) {
      this._id = id;
    }
  }

  /**
   * Enum for field names.
   */
  export enum FN {
    includes = 'includes',
    includesLimitation = 'includesLimitation',
    id = 'id'
  }
}
