export interface Sort {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

export interface PageableMetaData {
  sort: Sort;
  offset: number;
  pageNumber: number;
  pageSize: number;
  paged: boolean;
  unpaged: boolean;
}

export interface Page<T> {
  content: T[];
  pageable: PageableMetaData;
  last: boolean;
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
  sort: Sort;
  first: boolean;
  numberOfElements: number;
  empty: boolean;
}

export interface Pageable {
  sortBy: string,
  descending: boolean,
  page: number,
  rowsPerPage: number,
  filter: string,
}
