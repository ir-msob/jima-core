import {Filter} from "./filter";
import {NullableBoolean, NullableT} from "../../constants";

export class FilterQuery<TYPE> {
  private _eq: NullableT<TYPE>;

  get eq(): NullableT<TYPE> {
    return this._eq;
  }

  set eq(value: NullableT<TYPE>) {
    this._eq = value;
  }

  private _ne: NullableT<TYPE>;

  get ne(): NullableT<TYPE> {
    return this._ne;
  }

  set ne(value: NullableT<TYPE>) {
    this._ne = value;
  }

  private _regex: NullableT<TYPE>;

  get regex(): NullableT<TYPE> {
    return this._regex;
  }

  set regex(value: NullableT<TYPE>) {
    this._regex = value;
  }

  private _gte: NullableT<TYPE>;

  get gte(): NullableT<TYPE> {
    return this._gte;
  }

  set gte(value: NullableT<TYPE>) {
    this._gte = value;
  }

  private _gt: NullableT<TYPE>;

  get gt(): NullableT<TYPE> {
    return this._gt;
  }

  set gt(value: NullableT<TYPE>) {
    this._gt = value;
  }

  private _lte: NullableT<TYPE>;

  get lte(): NullableT<TYPE> {
    return this._lte;
  }

  set lte(value: NullableT<TYPE>) {
    this._lte = value;
  }

  private _lt: NullableT<TYPE>;

  get lt(): NullableT<TYPE> {
    return this._lt;
  }

  set lt(value: NullableT<TYPE>) {
    this._lt = value;
  }

  private _exists: NullableBoolean;

  get exists(): NullableBoolean {
    return this._exists;
  }

  set exists(value: NullableBoolean) {
    this._exists = value;
  }

  private _in: TYPE[] = []

  get in(): TYPE[] {
    return this._in;
  }

  set in(value: TYPE[]) {
    this._in = value;
  }

  private _nin: TYPE[] = [];

  get nin(): TYPE[] {
    return this._nin;
  }

  set nin(value: TYPE[]) {
    this._nin = value;
  }

  public static eq<TYPE>(value: TYPE): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.eq = value;
    return f;
  }

  public static ne<TYPE>(value: TYPE): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.ne = value;
    return f;
  }

  public static regex<TYPE>(value: TYPE): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.regex = value;
    return f;
  }

  public static gte<TYPE>(value: TYPE): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.gte = value;
    return f;
  }

  public static gt<TYPE>(value: TYPE): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.gt = value;
    return f;
  }

  public static lte<TYPE>(value: TYPE): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.lte = value;
    return f;
  }

  public static lt<TYPE>(value: TYPE): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.lt = value;
    return f;
  }

  public static exists<TYPE>(value: NullableBoolean): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.exists = value;
    return f;
  }

  public static in<TYPE>(value: TYPE[]): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.in = value;
    return f;
  }

  public static nin<TYPE>(value: TYPE[]): Filter<TYPE> {
    let f: Filter<TYPE> = new Filter<TYPE>();
    f.nin = value;
    return f;
  }
}
