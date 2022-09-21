import React from "react";
import dummyProfile from "@images/icon/dummyIcon.png";
import starRating from "@images/icon/star.svg";
import "./PerfumeReview.scss";

function PerfumeReview() {
  return (
    <div id="perfumeReview" className="perfumeReview flex">
      <div className="perfumeReview_profile flex">
        <div className="perfumeReview_profile_img">
          <img src={dummyProfile} alt="프로필이미지" />
        </div>
        <div className="perfumeReview_profile_rating">
          <img src={starRating} alt="프로필별점" />
        </div>
      </div>
      <div className="perfumeReview_info flex">
        <div className="perfumeReview_info_nickname notoBold fs-24">닉네임</div>
        <div className="perfumeReview_info_review notoMid fs-20">
          리뷰 내용이 엄청 길면 생략을 하게 되는 건가요? 네 맞습니다 물론 당연히
          생략을 해야죠
        </div>
      </div>
    </div>
  );
}
export default PerfumeReview;
