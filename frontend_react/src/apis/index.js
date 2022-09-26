import axios from "axios";
import cookies from "react-cookies";

export const BASE_URL = "http://j7c105.p.ssafy.io:8083";
export const coo = cookies.load("Spring");

export const API = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Access-Control-Allow-Origin": "http://j7c105.p.ssafy.io:8083",
    Authorization: coo
  }
});

export const ex = () => {};
