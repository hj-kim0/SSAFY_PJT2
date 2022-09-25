/* eslint-disable import/prefer-default-export */
import { API } from "./index";

export const getMain = async () => {
  const res = await API.get("/main");
  // console.log(res.data);
  return res.data;
};
export const getDetail = async perfumeId => {
  const res = await API.get(`/detail/${perfumeId}`);
  console.log(res);
  return res.data;
};