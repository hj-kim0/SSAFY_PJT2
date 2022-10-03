import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import dummyImg from "@images/icon/perfumeImg.svg";
import CarouselSlider from 'react-carousel-slider';
import "./TasteAnalysis.scss";
import "./Pie Chart";
import PieChart from "./Pie Chart";
import { fetchAccordClassListUser, fetchHaveListUser, fetchWishListUser } from "../../apis/perfumeAPI";
import { userState } from "../../atom";
import { useRecoilState } from "recoil";

function TasteAnalysis() {

  // 현재 로그인 유저 정보
  const [user, setUser] = useRecoilState(userState);

  //위시리스트 가져오기
  const [wishList, setWishList] = useState();

  //보유리스트 가져오기
  const [haveList, setHaveList] = useState();

  //향 순위 분석 가져오기
  const [accordClassList, setAccordClassList ] = useState();


  const wishData = wishList;

  const haveData = haveList;

  const accordClassData = accordClassList;

  const customSlideWishCpnts = 
    wishData.filter(item => item.isDelete !== true).map((item) => (
      <Link to={"/detail/" + item.perfumeIdx} key={item.perfumeIdx}>
        <img src = {item.perfumeImg}/>
      </Link>
    ));
  
    const customSlideHaveCpnts = 
    haveData.filter(item => item.isDelete !== true).map((item) => (
      <Link to={"/detail/" + item.perfumeIdx} key={item.perfumeIdx}>
        <img src = {item.perfumeImg}/>
      </Link>
    ));

    useEffect(() => {
      fetchWishListUser(user.sToken)
      .then((res) => {res.json().then((res) => {
        setWishList(res.setWishList)
      })})
    });

    useEffect(() => {
      fetchHaveListUser(user.sToken)
      .then((res) => {res.json().then((res) => {
        setHaveList(res.setHaveList)
      })})
    });
    
    useEffect(() => {
      fetchAccordClassListUser(user.sToken)
      .then((res) => {res.json().then((res) => {
        setAccordClassList(res.accordClassList)
      })})
    });
    
    const sliderBoxStyle = {
      height: "600px",
      width: "80%",
      background: "transparent",
      border: "1px solid #e1e4e8"
  };
  
  const itemsStyle = {
      width: "400px",
      height: "580px",
      padding: "5px",
      background: "transparent",
      border: "1px solid #e1e4e8",
      borderRadius: "2px",
      };
      

  const buttonSetting = {
      placeOn: "middle-outside",
      style: {
          left: {
          color: "#929393",
          background: "transparent",
          border: "1px solid #e1e4e8",
          borderRadius: "50%"
          },
          right: {
          color: "#929393",
          background: "transparent",
          border: "1px solid #e1e4e8",
          borderRadius: "50%"
          }
      }
      };
      
  return (
    <div className="container flex">

      <div id="tasteAnalysis" className="tasteAnalysis flex">
        <div className="tasteAnalysis_wishList flex">
          <div className="tasteAnalysis_wishList_title notoBold fs-36">
            위시리스트
          </div>
          <CarouselSlider 
                        slideCpnts={customSlideWishCpnts}
                        manner={{ circular: true }}
                        sliderBoxStyle={sliderBoxStyle}
                        buttonSetting={buttonSetting}
                        itemsStyle={itemsStyle}
          ></CarouselSlider>

          <div className="tasteAnalysis_wishList_title notoBold fs-36">
            보유리스트
          </div>
          <CarouselSlider 
                        slideCpnts={customSlideHaveCpnts}
                        manner={{ circular: true }}
                        sliderBoxStyle={sliderBoxStyle}
                        buttonSetting={buttonSetting}
                        itemsStyle={itemsStyle}
          ></CarouselSlider>
        </div>
        <div className="tasteAnalysis_emptyList flex">
          {/* <div className="tasteAnalysis_emptyList_title notoBold fs-36">
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
          </div> */}
        </div>
        <div className="divide" />
        <div className="tasteAnalysis_charts flex">
        <PieChart data={accordClassList}/>
        </div>
        <div className="tasteAnalysis_recommend flex">
          <div className="tasteAnalysis_recommend_title1 notoBold fs-32">
            님과 비슷한 취향의 사용자들이
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
