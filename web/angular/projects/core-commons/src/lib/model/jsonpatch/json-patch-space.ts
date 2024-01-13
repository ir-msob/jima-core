export namespace JsonPatchSpace {
  export class JsonPatch {
    private _operations: JsonPatchOperation[] = [];

    get operations(): JsonPatchSpace.JsonPatchOperation[] {
      return this._operations;
    }

    set operations(value: JsonPatchSpace.JsonPatchOperation[]) {
      this._operations = value;
    }
  }

  export class JsonPatchOperation {
    private _op: Operation = Operation.add;

    get op(): Operation {
      return this._op;
    }

    set op(value: Operation) {
      this._op = value;
    }

    private _path: string = "/";

    get path(): string {
      return this._path;
    }

    set path(value: string) {
      this._path = value;
    }

    private _value: any = null;

    get value(): any {
      return this._value;
    }

    set value(value: any) {
      this._value = value;
    }
  }

  export enum Operation {
    add = 'add', copy = 'copy', move = 'move', remove = 'remove', replace = 'replace', test = 'test'
  }
}
