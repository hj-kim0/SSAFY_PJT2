import React from "react";
import dummyImg from "@images/icon/perfumeImg.svg";
import mainToday from "@images/icon/main_today.svg";
import mainBest from "@images/icon/main_best.svg";
import "./Home.scss";

function Home() {
  return (
    <div className="container flex">
      <div id="home" className="home flex">
        <div className="home_today flex">
          <div className="home_today_title notoBold fs-40">오늘의 향수</div>
          <div className="home_today_imgs flex">
            <div className="home_today_imgs_img">
              <img src={mainToday} alt="오늘의향수" />
            </div>
            <div className="home_today_imgs_img">
              <img src={dummyImg} alt="향수이미지1" />
            </div>
            <div className="home_today_imgs_img">
              <img src={dummyImg} alt="향수이미지2" />
            </div>
          </div>
        </div>
        <div className="divide" />
        <div className="home_best flex">
          <div className="home_best_title notoBold fs-40">BEST 향수</div>
          <div className="home_best_imgs flex">
            <div className="home_best_imgs_img">
              <img src={dummyImg} alt="향수이미지3" />
            </div>
            <div className="home_best_imgs_img">
              <img src={dummyImg} alt="향수이미지4" />
            </div>
            <div className="home_best_imgs_img">
              <img src={mainBest} alt="베스트향수" />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
