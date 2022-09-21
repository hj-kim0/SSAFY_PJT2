import React from "react";
import dummyImg from "@images/icon/perfumeImg.svg";
import brandChart from "@images/icon/brand.png";
import scentChart from "@images/icon/scent.png";
import "./TasteAnalysis.scss";

function TasteAnalysis() {
  return (
    <div className="container flex">
      <div id="tasteAnalysis" className="tasteAnalysis flex">
        <div className="tasteAnalysis_wishList flex">
          <div className="tasteAnalysis_wishList_title notoBold fs-36">
            위시리스트
          </div>
          <div className="tasteAnalysis_wishList_cart flex">
            <img src={dummyImg} alt="향수이미지1" />
            <img src={dummyImg} alt="향수이미지2" />
            <img src={dummyImg} alt="향수이미지3" />
          </div>
        </div>
        <div className="tasteAnalysis_emptyList flex">
          <div className="tasteAnalysis_emptyList_title notoBold fs-36">
            위시리스트가 비었어요!
          </div>
          <div className="tasteAnalysis_emptyList_btns flex">
            <button
              type="button"
              className="tasteAnalysis_emptyList_btns_best notoBold fs-20"
            >
              랭킹 확인하기
            </button>
            <button
              type="button"
              className="tasteAnalysis_emptyList_btns_search notoBold fs-20"
            >
              향수 검색하기
            </button>
          </div>
        </div>
        <div className="divide" />
        <div className="tasteAnalysis_charts flex">
          <div className="tasteAnalysis_charts_brand">
            <img src={brandChart} alt="브랜드차트" />
          </div>
          <div className="tasteAnalysis_charts_scent">
            <img src={scentChart} alt="향기차트" />
          </div>
        </div>
        <div className="tasteAnalysis_recommend flex">
          <div className="tasteAnalysis_recommend_title1 notoBold fs-32">
            hanPark님과 비슷한 취향의 사용자들이
          </div>
          <div className="tasteAnalysis_recommend_title2 notoBold fs-32">
            좋아하는 향수입니다
          </div>
          <div className="tasteAnalysis_recommend_img">
            <img src={dummyImg} alt="추천향수" />
          </div>
          <div className="tasteAnalysis_recommend_noData flex">
            <div className="tasteAnalysis_recommend_noData_title notoBold fs-36">
              정보가 없어요!
            </div>
            <div className="tasteAnalysis_recommend_noData_btns flex">
              <button
                type="button"
                className="tasteAnalysis_recommend_noData_btns_info notoBold fs-20"
              >
                정보 입력하기
              </button>
              <button
                type="button"
                className="tasteAnalysis_recommend_noData_btns_search notoBold fs-20"
              >
                향수 검색하기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default TasteAnalysis;
