import {DEFAULT_STRING, DEFAULT_STRINGS} from "./test/core-test-data";
import {isEmpty, isNotEmpty} from "./constants";

describe('Constants', () => {

  /**
   * Test suite for the 'isEmpty' function.
   */
  describe('isEmpty Function', () => {

    it('should return true for null', () => {
      // Verifies that the 'isEmpty' function returns true for null values.
      expect(isEmpty(null)).toEqual(true);
    });

    it('should return true for undefined', () => {
      // Verifies that the 'isEmpty' function returns true for undefined values.
      expect(isEmpty(undefined)).toEqual(true);
    });

    it('should return false for a non-empty string', () => {
      // Verifies that the 'isEmpty' function returns false for a non-empty string.
      expect(isEmpty(DEFAULT_STRING)).toEqual(false);
    });

    it('should return true for an empty array', () => {
      // Verifies that the 'isEmpty' function returns true for an empty array.
      expect(isEmpty([])).toEqual(true);
    });

    it('should return false for a non-empty array', () => {
      // Verifies that the 'isEmpty' function returns false for a non-empty array.
      expect(isEmpty(DEFAULT_STRINGS)).toEqual(false);
    });
  });

  /**
   * Test suite for the 'isNotEmpty' function.
   */
  describe('isNotEmpty Function', () => {

    it('should return false for null', () => {
      // Verifies that the 'isNotEmpty' function returns false for null values.
      expect(isNotEmpty(null)).toEqual(false);
    });

    it('should return false for undefined', () => {
      // Verifies that the 'isNotEmpty' function returns false for undefined values.
      expect(isNotEmpty(undefined)).toEqual(false);
    });

    it('should return true for a non-empty string', () => {
      // Verifies that the 'isNotEmpty' function returns true for a non-empty string.
      expect(isNotEmpty(DEFAULT_STRING)).toEqual(true);
    });

    it('should return false for an empty array', () => {
      // Verifies that the 'isNotEmpty' function returns false for an empty array.
      expect(isNotEmpty([])).toEqual(false);
    });

    it('should return true for a non-empty array', () => {
      // Verifies that the 'isNotEmpty' function returns true for a non-empty array.
      expect(isNotEmpty(DEFAULT_STRINGS)).toEqual(true);
    });
  });
});
