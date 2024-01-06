import { HttpStatusCode } from "axios";
type ResponseAPI<T> = {
  statusCode: HttpStatusCode;
  message?: string;
  error?: string;
  data: T;
};
