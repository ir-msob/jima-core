import {RestUtil} from './rest.util';
import {
  Criteria,
  DEFAULT_BOOLEAN,
  DEFAULT_NUMBER,
  DEFAULT_STRING,
  DEFAULT_STRINGS,
  Filter,
  SampleCriteria
} from "@ir-msob/jima-core-commons";
import {Pagination} from "../model/pagination";
import {HttpParams} from "@angular/common/http";

describe('RestUtil', () => {

  it('should convert Criteria.includes field to a parameter', () => {
    // Prepare a sample criteria with includes field
    let criteria: SampleCriteria<string> = prepareIncludesCriteria(DEFAULT_STRINGS);
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.getAll(Criteria.FN.includes.toString())).not.toBeNull();
    for (const defaultValue of DEFAULT_STRINGS) {
      expect(param?.getAll(Criteria.FN.includes.toString())).toContain(defaultValue);
    }
  });

  it('should convert Criteria.id.eq field to a parameter', () => {
    // Prepare a sample criteria with id field and eq filter
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.eq(DEFAULT_STRING));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.eq')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.ne field to a parameter', () => {
    // Prepare a sample criteria with id field and ne filter
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.ne(DEFAULT_STRING));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.ne')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.exists field to a parameter', () => {
    // Prepare a sample criteria with id field and exists filter
    let criteria = prepareIdCriteria(Filter.exists(DEFAULT_BOOLEAN));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.exists')).toEqual(String(DEFAULT_BOOLEAN));
  });

  it('should convert Criteria.id.gt field to a parameter', () => {
    // Prepare a sample criteria with id field and gt filter
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.gt(DEFAULT_STRING));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.gt')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.gte field to a parameter', () => {
    // Prepare a sample criteria with id field and gte filter
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.gte(DEFAULT_STRING));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.gte')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.lt field to a parameter', () => {
    // Prepare a sample criteria with id field and lt filter
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.lt(DEFAULT_STRING));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.lt')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.lte field to a parameter', () => {
    // Prepare a sample criteria with id field and lte filter
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.lte(DEFAULT_STRING));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.lte')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.regex field to a parameter', () => {
    // Prepare a sample criteria with id field and regex filter
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.regex(DEFAULT_STRING));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.regex')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.in field to a parameter', () => {
    // Prepare a sample criteria with id field and in filter
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.in(DEFAULT_STRINGS));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    for (const defaultValue of DEFAULT_STRINGS) {
      expect(param?.getAll(Criteria.FN.id.toString() + '.in')).toContain(defaultValue);
    }
  });

  it('should convert Criteria.id.nin field to a parameter', () => {
    // Prepare a sample criteria with id field and nin filter
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.nin(DEFAULT_STRINGS));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    for (const defaultValue of DEFAULT_STRINGS) {
      expect(param?.getAll(Criteria.FN.id.toString() + '.nin')).toContain(defaultValue);
    }
  });

  it('should convert Criteria.id.eq field to a parameter', () => {
    // Prepare a sample criteria with id field and an "or" filter with eq condition
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.or(Filter.eq(DEFAULT_STRING)));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.or.eq')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.ne field to a parameter', () => {
    // Prepare a sample criteria with id field and an "or" filter with ne condition
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.or(Filter.ne(DEFAULT_STRING)));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.or.ne')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.exists field to a parameter', () => {
    // Prepare a sample criteria with id field and an "or" filter with exists condition
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.or<string>(Filter.exists<string>(DEFAULT_BOOLEAN)));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.or.exists')).toEqual(String(DEFAULT_BOOLEAN));
  });

  it('should convert Criteria.id.gt field to a parameter', () => {
    // Prepare a sample criteria with id field and an "or" filter with gt condition
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.or(Filter.gt(DEFAULT_STRING)));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.or.gt')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.gte field to a parameter', () => {
    // Prepare a sample criteria with id field and an "or" filter with gte condition
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.or(Filter.gte(DEFAULT_STRING)));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.or.gte')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.lt field to a parameter', () => {
    // Prepare a sample criteria with id field and an "or" filter with lt condition
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.or(Filter.lt(DEFAULT_STRING)));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.or.lt')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.lte field to a parameter', () => {
    // Prepare a sample criteria with id field and an "or" filter with lte condition
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.or(Filter.lte(DEFAULT_STRING)));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.or.lte')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.regex field to a parameter', () => {
    // Prepare a sample criteria with id field and an "or" filter with regex condition
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.or(Filter.regex(DEFAULT_STRING)));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get(Criteria.FN.id.toString() + '.or.regex')).toEqual(DEFAULT_STRING);
  });

  it('should convert Criteria.id.in field to a parameter', () => {
    // Prepare a sample criteria with id field and an "or" filter with in condition
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.or(Filter.in(DEFAULT_STRINGS)));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    for (const defaultValue of DEFAULT_STRINGS) {
      expect(param?.getAll(Criteria.FN.id.toString() + '.or.in')).toContain(defaultValue);
    }
  });

  it('should convert Criteria.id.or.nin field to a parameter', () => {
    // Prepare a sample criteria with id field and an "or" filter with nin condition
    let criteria: SampleCriteria<string> = prepareIdCriteria(Filter.or(Filter.nin(DEFAULT_STRINGS)));
    // Generate HttpParams from the criteria
    let param: HttpParams = RestUtil.generateParams(criteria);

    // Expectations
    expect(param).not.toBeNull();
    for (const defaultValue of DEFAULT_STRINGS) {
      expect(param?.getAll(Criteria.FN.id.toString() + '.or.nin')).toContain(defaultValue);
    }
  });

  it('should convert pagination parameters to parameters', () => {
    // Prepare a sample pagination object
    let pagination: Pagination = preparePagination();
    // Generate HttpParams from the pagination
    let param: HttpParams = RestUtil.generateParams(null, pagination);

    // Expectations
    expect(param).not.toBeNull();
    expect(param?.get('page')).toEqual(DEFAULT_NUMBER.toString());
    expect(param?.get('size')).toEqual(DEFAULT_NUMBER.toString());
  });

  // Helper functions for test cases

  function prepareIdCriteria<TYPE>(idFilter: Filter<TYPE>): SampleCriteria<TYPE> {
    let criteria: SampleCriteria<TYPE> = new SampleCriteria<TYPE>();
    criteria.id = idFilter;
    return criteria;
  }

  function preparePagination(): Pagination {
    let pagination: Pagination = new Pagination();
    pagination.page = DEFAULT_NUMBER;
    pagination.size = DEFAULT_NUMBER;
    return pagination;
  }

  function prepareIncludesCriteria(includes: string[]): SampleCriteria<string> {
    let criteria: SampleCriteria<string> = new SampleCriteria<string>();
    criteria.includes = includes;
    return criteria;
  }
});
