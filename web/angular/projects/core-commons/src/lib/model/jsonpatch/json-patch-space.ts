/**
 * Namespace JsonPatchSpace.
 * It contains the classes and enums required for JSON Patch operations.
 */
export namespace JsonPatchSpace {
  /**
   * Class JsonPatch.
   * It contains an array of JsonPatchOperation objects.
   * @class
   */
  export class JsonPatch {
    private _operations: JsonPatchOperation[] = [];

    /**
     * Getter for 'operations'.
     * @returns {JsonPatchSpace.JsonPatchOperation[]} The 'operations' array.
     */
    get operations(): JsonPatchSpace.JsonPatchOperation[] {
      return this._operations;
    }

    /**
     * Setter for 'operations'.
     * @param {JsonPatchSpace.JsonPatchOperation[]} value - The 'operations' array.
     */
    set operations(value: JsonPatchSpace.JsonPatchOperation[]) {
      this._operations = value;
    }
  }

  /**
   * Class JsonPatchOperation.
   * It contains the details of a single JSON Patch operation.
   * @class
   */
  export class JsonPatchOperation {
    private _op: Operation = Operation.add;

    /**
     * Getter for 'op'.
     * @returns {Operation} The 'op' value.
     */
    get op(): Operation {
      return this._op;
    }

    /**
     * Setter for 'op'.
     * @param {Operation} value - The 'op' value.
     */
    set op(value: Operation) {
      this._op = value;
    }

    private _path: string = "/";

    /**
     * Getter for 'path'.
     * @returns {string} The 'path' value.
     */
    get path(): string {
      return this._path;
    }

    /**
     * Setter for 'path'.
     * @param {string} value - The 'path' value.
     */
    set path(value: string) {
      this._path = value;
    }

    private _value: any = null;

    /**
     * Getter for 'value'.
     * @returns {any} The 'value'.
     */
    get value(): any {
      return this._value;
    }

    /**
     * Setter for 'value'.
     * @param {any} value - The 'value'.
     */
    set value(value: any) {
      this._value = value;
    }
  }

  /**
   * Enum Operation.
   * It contains the possible operations for a JSON Patch.
   * @enum
   */
  export enum Operation {
    add = 'add', copy = 'copy', move = 'move', remove = 'remove', replace = 'replace', test = 'test'
  }
}
