import {Filter} from "../filter/filter";
import {BaseModel} from "../base-model";
import {BaseType} from "../dto/base-type";

/**
 * Namespace for Criteria related interfaces and classes.
 */
export namespace Criteria {
  /**
   * Interface for base criteria.
   * It extends BaseModel and BaseType interfaces.
   * It provides getter and setter methods for includes, includesLimitation and id.
   */
  export interface BaseCriteria<ID> extends BaseModel, BaseType {

    /**
     * Getter for includes.
     * @returns {string[]} The includes.
     */
    get includes(): string[];

    /**
     * Setter for includes.
     * @param {string[]} includes - The includes to set.
     */
    set includes(includes: string[]);

    /**
     * Getter for includesLimitation.
     * @returns {string[]} The includesLimitation.
     */
    get includesLimitation(): string[];

    /**
     * Setter for includesLimitation.
     * @param {string[]} includes - The includesLimitation to set.
     */
    set includesLimitation(includes: string[]);

    /**
     * Getter for id.
     * @returns {Filter<ID> | undefined} The id.
     */
    get id(): Filter<ID> | undefined;

    /**
     * Setter for id.
     * @param {Filter<ID> | undefined} id - The id to set.
     */
    set id(id: Filter<ID> | undefined);

  }

  /**
   * Abstract class for base criteria.
   * It implements BaseCriteria interface.
   * It provides implementation for getter and setter methods of includes, includesLimitation and id.
   */
  export abstract class BaseCriteriaAbstract<ID> implements BaseCriteria<ID> {

    private _includes: string[] = [];

    /**
     * Getter for includes.
     * @returns {string[]} The includes.
     */
    get includes(): string[] {
      return this._includes;
    }

    /**
     * Setter for includes.
     * @param {string[]} includes - The includes to set.
     */
    set includes(includes: string[]) {
      this._includes = includes;
    }

    private _includesLimitation: string[] = [];

    /**
     * Getter for includesLimitation.
     * @returns {string[]} The includesLimitation.
     */
    get includesLimitation(): string[] {
      return this._includesLimitation;
    }

    /**
     * Setter for includesLimitation.
     * @param {string[]} includesLimitation - The includesLimitation to set.
     */
    set includesLimitation(includesLimitation: string[]) {
      this._includesLimitation = includesLimitation;
    }

    private _id: Filter<ID> | undefined;

    /**
     * Getter for id.
     * @returns {Filter<ID> | undefined} The id.
     */
    get id(): Filter<ID> | undefined {
      return this._id;
    }

    /**
     * Setter for id.
     * @param {Filter<ID> | undefined} id - The id to set.
     */
    set id(id: Filter<ID> | undefined) {
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
