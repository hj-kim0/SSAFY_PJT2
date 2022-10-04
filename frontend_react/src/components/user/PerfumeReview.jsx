import React from "react";
import dummyProfile from "@images/icon/dummyIcon.png";
// import starRating from "@images/icon/star.svg";
import ystar from "@images/icon/ystar.png";
import long from "@images/icon/long.png";
import sillage from "@images/icon/sillage.png";
import "./PerfumeReview.scss";

function PerfumeReview(props) {
  const perfumeItem = props.item;
  const starList = []
  let i = 0;
  for (; i < perfumeItem.totalScore; ++i) {
    starList.push(<span><img className="perfumeReview_profile_rating_ystar" src={ystar} alt="" /></span>)
  }
  const longList = []
  let j = 0;
  for (; j < perfumeItem.longevity; ++j) {
    longList.push(<span><img className="perfumeReview_info_rating_long_emo" src={long} alt="" /></span>)
  }
  const sillageList = []
  let k = 0;
  for (; k < perfumeItem.sillageScore; ++k) {
    sillageList.push(<span><img className="perfumeReview_info_rating_sil_emo" src={sillage} alt="" /></span>)
  }
  // console.log(perfumeItem);
  return (
    <div id="perfumeReview" className="perfumeReview flex">
      <div className="perfumeReview_profile flex">
        <div className="perfumeReview_profile_img flex">
          {/* {perfumeItem.review} */}
          {perfumeItem?.userProfileimg && (
            <img src={perfumeItem.userProfileimg} alt="프로필이미지" />
          )}
          {!perfumeItem?.userProfileimg && (
            <img src={dummyProfile} alt="프로필이미지" />
          )}
        </div>
        <div className="perfumeReview_profile_rating">
          {starList}
        </div>
      </div>
      <div className="perfumeReview_upload flex">
        {perfumeItem?.reviewImg && (
          <img src={perfumeItem.reviewImg} alt="업로드한 이미지" />
        )}
        {!perfumeItem?.reviewImg && (
          <img src={dummyProfile} alt="업로드한 이미지" />
        )}
      </div>
      <div className="perfumeReview_info flex">
        <div className="perfumeReview_info_writer flex">
          <div className="perfumeReview_info_writer_nickname notoBold fs-22">
            닉네임
          </div>
          <div className="perfumeReview_info_writer_date notoMid fs-20">
            {perfumeItem.time?.slice(0,10)}
          </div>
        </div>
        <div className="perfumeReview_info_rating flex">
          <div className="perfumeReview_info_rating_long">{longList}</div>
          <div className="perfumeReview_info_rating_sil">{sillageList}</div>
        </div>
        <div className="perfumeReview_info_review notoMid fs-20">
          {perfumeItem.content}
        </div>
      </div>
    </div>
  );
}
export default PerfumeReview;
