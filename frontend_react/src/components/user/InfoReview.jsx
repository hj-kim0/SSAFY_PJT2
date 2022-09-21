import React from "react";
import perfumeImg from "@images/icon/perfumeImg.svg";
import starRating from "@images/icon/star.svg";
import "./InfoReview.scss";

function InfoReview () {
  return (
    <div id="infoReview" className="infoReview flex">
      <div className="infoReview_img">
        <img src={perfumeImg} alt="향수이미지" />
      </div>
      <div className="infoReview_extra">
        <div className="infoReview_extra_name notoBold fs-24">향수 이름</div>
        <div className="infoReview_extra_rating flex">
          <div className="infoReview_extra_rating_star">
            <img src={starRating} alt="별점" />
          </div>
          <div className="infoReview_extra_rating_date notoMid fs-15">2022.09.15</div>
        </div>
        <div className="infoReview_extra_comment notoMid fs-20">
          리뷰 내용이 엄청 길면 잘리게 되는 건가요? 네 맞습니다 당연히 생략을 해야죠
        </div>
      </div>
    </div>
  );
}
export default InfoReview;