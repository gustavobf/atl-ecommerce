import { environment } from "./environments/environment.prod";

export const API_ENDPOINT = environment.API_ENDPOINT_AMBIENTE_APP ?? 'http://localhost:8080';
