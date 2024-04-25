import {ModelType} from "../dto/model-type";

export class BaseUser extends ModelType {
  private _id: string | undefined;

  get id(): string | undefined {
    return this._id;
  }

  set id(id: string | undefined) {
    this._id = id;
  }

  private _sessionId: string | undefined;

  get sessionId(): string | undefined {
    return this._sessionId;
  }

  set sessionId(sessionId: string | undefined) {
    this._sessionId = sessionId;
  }
  private _name: string | undefined;

  get name(): string | undefined {
    return this._name;
  }

  set name(value: string | undefined) {
    this._name = value;
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
