import {Sort} from "./model/page/sort";
import {Pageable} from "./model/page/pageable";

// Types
export type NullableBoolean = boolean | null | undefined;
export type NullableNumber = number | null | undefined;
export type NullableString = string | null | undefined;
export type NullableSort = Sort | null | undefined;
export type NullablePageable = Pageable | null | undefined;
export type NullableT<T> = T | null | undefined;
export type NullableTs<T> = T[] | null | undefined;

export type NotNull<T> = T extends null | undefined | '' | [] ? never : T;
export type Nullable<T> = null | undefined | '' | [] extends T ? never : T;

export const isEmpty = function <T>(value: NullableT<T>): value is Nullable<T> {
  return value === null || value === undefined || (Array.isArray(value) && value.length === 0) || (typeof value === "string" && value.length === 0);
};

export const isNotEmpty = function <T>(value: NullableT<T>): value is NotNull<T> {
  return !isEmpty(value);
};
