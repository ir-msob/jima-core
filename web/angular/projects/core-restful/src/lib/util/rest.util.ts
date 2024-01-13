import {HttpParams} from "@angular/common/http";
import {Pagination} from "../model/pagination";
import {isEmpty, isNotEmpty} from "@ir-msob-framework/core-commons";

/**
 * A utility class for generating HTTP parameters based on search criteria and pagination options.
 */
export class RestUtil {
  /**
   * Generates HTTP parameters based on provided criteria and pagination options.
   * @param criteria Optional search criteria for filtering.
   * @param pagination Optional pagination options.
   * @returns An instance of HttpParams or undefined if no criteria or pagination are provided.
   */
  public static generateParams(criteria?: any, pagination?: Pagination): HttpParams {
    let result: HttpParams = new HttpParams();

    // Prepare criteria parameters
    result = RestUtil.prepareCriteria(criteria, result);

    // Prepare pagination parameters
    result = RestUtil.preparePagination(pagination, result);

    return result;
  }

  /**
   * Prepares a field name for inclusion in the HTTP request.
   * Removes an underscore prefix if present.
   * @param field The field name to prepare.
   * @returns The prepared field name.
   */
  public static prepareFieldName(field: string): string {
    if (field.startsWith('_')) {
      return field.substring(1);
    }
    return field;
  }

  /**
   * Prepares criteria parameters to be included in the HTTP request.
   * @param criteria The search criteria.
   * @param result The HttpParams object to add the criteria parameters to.
   * @returns An HttpParams object with the criteria parameters included.
   */
  private static prepareCriteria(criteria: any, result: HttpParams): HttpParams {
    if (isEmpty(criteria)) return result;

    for (const field of Object.keys(criteria)) {
      const preparedField = RestUtil.prepareFieldName(field);

      // Handle "includes" criteria
      if (preparedField === 'includes' && isNotEmpty(criteria.includes)) {
        result = RestUtil.prepareIncludes(criteria, result);
      }
      // Handle query field criteria
      else {
        result = RestUtil.prepareFilters(criteria, preparedField, result);
      }
    }
    return result;
  }

  /**
   * Prepares "includes" criteria to be included in the HTTP request.
   * @param criteria The "includes" criteria.
   * @param result The HttpParams object to add the "includes" parameters to.
   * @returns An HttpParams object with the "includes" parameters included.
   */
  private static prepareIncludes(criteria: any, result: HttpParams): HttpParams {
    for (const include of criteria.includes) {
      result = result.append('includes', include);
    }
    return result;
  }

  /**
   * Prepares field-based criteria to be included in the HTTP request.
   * @param criteria The field-based criteria.
   * @param field The field name.
   * @param result The HttpParams object to add the criteria parameters to.
   * @returns An HttpParams object with the criteria parameters included.
   */
  private static prepareFilters(criteria: any, field: string, result: HttpParams): HttpParams {
    const filter: any = criteria[field];

    // Define a mapping of filter type names to their corresponding parameter names
    const filterTypeMappings: { [key: string]: string } = {
      eq: 'eq',
      ne: 'ne',
      regex: 'regex',
      gte: 'gte',
      gt: 'gt',
      lte: 'lte',
      lt: 'lt',
      exists: 'exists',
    };

    for (const filterType in filterTypeMappings) {
      if (isNotEmpty(filter[filterType])) {
        return result.set(`${field}.${filterTypeMappings[filterType]}`, filter[filterType].toString());
      }
    }

    if (isNotEmpty(filter.in)) {
      for (const element of filter.in) {
        result = result.append(`${field}.in`, element);
      }
      return result;
    }

    if (isNotEmpty(filter.nin)) {
      for (const element of filter.nin) {
        result = result.append(`${field}.nin`, element);
      }
      return result;
    }

    if (isNotEmpty(filter.or)) {
      return RestUtil.prepareOrFilter(filter, result, field);
    }

    return result;
  }

  /**
   * Prepares "or" filter criteria to be included in the HTTP request.
   * @param filter The "or" filter criteria.
   * @param result The HttpParams object to add the "or" filter parameters to.
   * @param field The field name.
   * @returns An HttpParams object with the "or" filter parameters included.
   */
  private static prepareOrFilter(filter: any, result: HttpParams, field: string): HttpParams {
    const orFilter: any = filter['or'];

    const filterTypes: string[] = ['eq', 'ne', 'regex', 'gte', 'gt', 'lte', 'lt', 'exists', 'in', 'nin'];

    for (const filterType of filterTypes) {
      if (isNotEmpty(orFilter[filterType])) {
        return result.set(`${field}.or.${filterType}`, orFilter[filterType].toString());
      }
    }

    return result;
  }

  /**
   * Prepares pagination parameters to be included in the HTTP request.
   * @param pagination The pagination options.
   * @param result The HttpParams object to add the pagination parameters to.
   * @returns An HttpParams object with the pagination parameters included.
   */
  private static preparePagination(pagination: Pagination | undefined, result: HttpParams): HttpParams {
    if (!isNotEmpty(pagination)) return result;

    // Page
    if (isNotEmpty(pagination.page))
      result = result.set('page', pagination.page);

    // Size
    if (isNotEmpty(pagination.size))
      result = result.set('size', pagination.size);

    return result;
  }
}
