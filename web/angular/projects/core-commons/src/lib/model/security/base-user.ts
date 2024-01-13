import {ModelType} from "../dto/model-type";

export class BaseUser<ID> extends ModelType {
  private _id: ID | undefined;

  get id(): ID | undefined {
    return this._id;
  }

  set id(id: ID | undefined) {
    this._id = id;
  }

  private _sessionId: ID | undefined;

  get sessionId(): ID | undefined {
    return this._sessionId;
  }

  set sessionId(sessionId: ID | undefined) {
    this._sessionId = sessionId;
  }

  private _username: string | undefined;

  get username(): string | undefined {
    return this._username;
  }

  set username(username: string | undefined) {
    this._username = username;
  }

  private _roles: string[] = [];

  get roles(): string[] {
    return this._roles;
  }

  set roles(roles: string[]) {
    this._roles = roles;
  }

  private _audience: string | undefined;

  get audience(): string | undefined {
    return this._audience;
  }

  set audience(audience: string | undefined) {
    this._audience = audience;
  }
}
