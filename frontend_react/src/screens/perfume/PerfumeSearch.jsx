import React from "react";
import dummyImg from "@images/icon/perfumeImg.svg";
import "./PerfumeSearch.scss";

function PerfumeSearch() {
  return (
    <div className="container flex">
      <div id="perfumeSearch" className="perfumeSearch flex">
        <div className="perfumeSearch_title flex notoBold fs-36">
          맞춤 향수 검색
        </div>
        <div className="perfumeSearch_gender flex">
          <div className="perfumeSearch_gender_title notoBold fs-32">
            Gender
          </div>
          <div className="perfumeSearch_gender_btns flex">
            <button
              type="button"
              className="perfumeSearch_gender_btns_man notoBold fs-24"
            >
              MAN
            </button>
            <button
              type="button"
              className="perfumeSearch_gender_btns_woman notoBold fs-24"
            >
              WOMAN
            </button>
            <button
              type="button"
              className="perfumeSearch_gender_btns_unisex notoBold fs-24"
            >
              UNISEX
            </button>
          </div>
        </div>
        <div className="divide" />
        <div className="perfumeSearch_duration flex">
          <div className="perfumeSearch_duration_title notoBold fs-32">
            Duration
          </div>
          <div className="perfumeSearch_duration_btns1 flex">
            <button
              type="button"
              className="perfumeSearch_duration_btns1_edp notoBold fs-24"
            >
              EDP(5~6)
            </button>
            <button
              type="button"
              className="perfumeSearch_duration_btns1_edt notoBold fs-24"
            >
              EDT(3~5)
            </button>
            <button
              type="button"
              className="perfumeSearch_duration_btns1_edc notoBold fs-24"
            >
              EDC(1~2)
            </button>
          </div>
          <div className="perfumeSearch_duration_btns2 flex">
            <button
              type="button"
              className="perfumeSearch_duration_btns2_oil notoBold fs-24"
            >
              OIL
            </button>
            <button
              type="button"
              className="perfumeSearch_duration_btns2_pdt notoBold fs-24"
            >
              PDT
            </button>
          </div>
        </div>
        <div className="divide" />
      </div>
      <div id="perfumeResult" className="perfumeResult flex">
        <div className="perfumeResult_img">
          <img src={dummyImg} alt="더미이미지" />
        </div>
        <div className="perfumeResult_img">
          <img src={dummyImg} alt="더미이미지" />
        </div>
        <div className="perfumeResult_img">
          <img src={dummyImg} alt="더미이미지" />
        </div>
        <div className="perfumeResult_img">
          <img src={dummyImg} alt="더미이미지" />
        </div>
        <div className="perfumeResult_img">
          <img src={dummyImg} alt="더미이미지" />
        </div>
        <div className="perfumeResult_img">
          <img src={dummyImg} alt="더미이미지" />
        </div>
      </div>
    </div>
  );
}
export default PerfumeSearch;
