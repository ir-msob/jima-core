export class Pagination {
  private _page: number = 0;

  get page(): number {
    return this._page;
  }

  set page(value: number) {
    this._page = value;
  }

  private _size: number = 10;

  get size(): number {
    return this._size;
  }

  set size(value: number) {
    this._size = value;
  }
}

export enum FN {
  page = 'page',
  size = 'size'
}
