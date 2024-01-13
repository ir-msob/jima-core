import {HttpHeaders} from "@angular/common/http";

export const TOKEN_PREFIX: string = 'Bearer ';
export const TOKEN_HEADER_NAME: string = 'Authorization';
export const TOKEN_CACHE_KEY: string = 'TOKEN';
export const DEFAULT_HTTP_HEADERS: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

