import React from "react";
// import { Link } from "react-router-dom";
import dummyProfile from "@images/icon/dummyIcon.png";
import InfoReview from "@components/user/InfoReview";
import "./UserReview.scss";

function UserReview() {
  return (
    <div className="container flex justify-center">
      <div id="userReview" className="userReview">
        <div id="userReview1" className="userReview1 flex justify-center">
          <img
            src={dummyProfile}
            alt="Profile_Image"
            className="userReview1_img"
          />
          <div className="userReview1_nickname notoBold fs-24">
            닉네임
          </div>
        </div>
        <div id="userReview2" className="userReview2 flex">
          <div className="userReview2_title notoBold fs-24">작성한 리뷰</div>
          <div className="divide1" />
          <div className="userReview2_contents flex">
            <div className="userReview2_contents_count flex notoBold fs-20">
              <div className="userReview2_contents_count_title">
                전체
              </div>
              <div className="userReview2_contents_count_num">
                10
              </div>
            </div>
            <div className="userReview2_contents_rating flex notoMid fs-20">
              <div className="userReview2_contents_rating_title">
                평균
              </div>
              <div className="userReview2_contents_rating_num">
                5.0
              </div>
            </div>
            <button className="userReview2_contents_new flex notoBold fs-20" type="button">
              최신순
            </button>
            <button className="userReview2_contents_rate flex notoMid fs-20" type="button">
              평점순
            </button>
          </div>
          <div className="divide2" />
        </div>
        <div className="userReview3">
          <InfoReview />
          <InfoReview />
          <InfoReview />
        </div>
      </div>
    </div>
  );
}
export default UserReview;
