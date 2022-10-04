import React, { useState, useEffect, useRef } from "react";
import uuid from "react-uuid";
import { useNavigate, useParams } from "react-router-dom";
import { useRecoilValue } from 'recoil';
import { userProfileState } from "../../atom";
// import dummyImg from "@images/icon/perfumeImg.svg";
import favorite from "@images/icon/favorite_black(2).svg";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import dummyProfile from "@images/icon/dummyIcon.png";
import ratingStar from "@images/icon/star.svg";
// import InfoReview from "@components/user/InfoReview";
import PerfumeReview from "@components/user/PerfumeReview";
import { getDetail } from "../../apis/perfume";
import "./PerfumeDetail.scss";
import axios from "axios";
import { fetchRecommendCos } from "../../apis/perfumeAPI";

function PerfumeDetail() {
  const [position, setPosition] = useState(0)
  const navigate = useNavigate();
  const reviewRef = useRef();
  const { id } = useParams();
  const [flip, setFlip] = useState("평점순");
  const [perfumeDetail, setPerfumeDetail] = useState({});
  const [getReviewList, setGetReviewList] = useState([]);
  const userProfile = useRecoilValue(userProfileState);
  const detail = () => {
    axios({
      method: "post",
      url: `http://j7c105.p.ssafy.io:8083/detail/${id}`,
      data: {
        "type": flip,
        "lastIdx": null,
        "lastTatalScore": null,
        "lastLikeCount": null,
        "pageSize": 4
      }
    })
    .then((res) => {
      // console.log(res);
      setPerfumeDetail(res.data.perfume);
      setGetReviewList(res.data.reviewList);
    })
    .catch((err) => console.log(err))
  };
  console.log(perfumeDetail);

  function onScroll(){
    setPosition(window.scrollY)
  }
  useEffect(() => {
    window.addEventListener("scroll", onScroll);
    return () => {
      window.removeEventListener("scroll", onScroll)
    }
  }, [])

  useEffect(() => {
    fetchRecommendCos(id)
      .then((res) => {res.json().then(() => {
        console.log(res)
      })})
  })


  // console.log(perfumeDetail);
  // console.log(getReviewList);
  useEffect(() => {
    detail();
  }, [flip]);
  const perfumeReviewList = getReviewList?.map((item) => (<PerfumeReview key={uuid()} item={item}/>))
  const newclick = () => {
    setFlip("최신순");
  }
  const scoreclick = () => {
    setFlip("평점순");
  }
  return (
    <div className="container flex justify-center">
      <div id="perfumeDetail" className="perfumeDetail">
        <div id="perfumeDetail1" className="perfumeDetail1 flex">
          <div className="perfumeDetail1_img">
            <img src={perfumeDetail.perfumeImg} alt="Perfume_Img" />
          </div>
          <div className="perfumeDetail1_contents flex justify-center align-center kyobo fs-22">
            <p>{perfumeDetail.description}</p>
          </div>
        </div>
        <div id="perfumeDetail2" className="perfumeDetail2 flex">
          <div className="perfumeDetail2_title flex">
            <div className="perfumeDetail2_title_name loveYa fs-24">
              {perfumeDetail.perfumeName}
            </div>
            <div className="perfumeDetail2_title_count flex">
              <div className="perfumeDetail2_title_count_like flex">
                <button className="perfumeDetail2_title_count_like_img" type="button">
                  <img src={favorite} alt="favorite_Img" />
                </button>
                <div className="perfumeDetail2_title_count_like_number roBold fs-24">
                  {perfumeDetail.wishCount}
                </div>
              </div>
              <div className="perfumeDetail2_title_count_have flex">
                <button className="perfumeDetail2_title_count_have_img" type="button">
                  <ShoppingCartIcon sx={{ fontSize: 36, color: "black"}}/>
                </button>
                <div className="perfumeDetail2_title_count_have_number roBold fs-24">
                  {perfumeDetail.haveCount}
                </div>
              </div>
            </div>
          </div>
          <div className="perfumeDetail2_info flex align-center loveYa fs-24">
            <div className="perfumeDetail2_info_season">
              {perfumeDetail.seasons}
            </div>
            <div className="perfumeDetail2_info_time">
              {perfumeDetail.timezone}
            </div>
          </div>
        </div>
        <div className="divide1" />
        <div>
          <p style={{
            transform : `translateX(${position}px)`,
          }}>
            Parall Scrollll~~~~
          </p>
        </div>
        <div className="divide1"/>
        <div id="perfumeDetail3" className="perfumeDetail3 flex align-center">
          <div className="perfumeDetail3_profile flex">
            <div className="perfumeDetail3_profile_img">
              {userProfile?.profileImg && (
                <img src={userProfile.profileImg} alt="프로필이미지" />
                )}
              {!userProfile?.profileImg && (
                <img src={dummyProfile} alt="프로필이미지" />
              )}
            </div>
            <div className="perfumeDetail3_profile_rating">
              <img src={ratingStar} alt="프로필별점" />
            </div>
          </div>
          <div className="perfumeDetail3_input flex align-center">
            {/* <input className="perfumeDetail3_input_input" type="input" /> */}
            <textarea type="textarea" className="perfumeDetail3_input_text notoReg fs-18" ref={reviewRef} />
            <button
              className="perfumeDetail3_input_btn notoReg fs-18"
              type="button"
            >
              입력
            </button>
          </div>
        </div>
        <div className="perfumeDetail4 flex">
          {flip === "평점순" && (
            <div className="perfumeDetail4_sort flex">
              <button className="perfumeDetail4_sort_best notoBold fs-30" type="button" onClick={scoreclick}>평점순</button>
              <button className="perfumeDetail4_sort_new notoReg fs-30" type="button" onClick={newclick}>최신순</button>
            </div>
            )}
          {flip === "최신순" && (
            <div className="perfumeDetail4_sort flex">
              <button className="perfumeDetail4_sort_best notoReg fs-30" type="button" onClick={scoreclick}>평점순</button>
              <button className="perfumeDetail4_sort_new notoBold fs-30" type="button" onClick={newclick}>최신순</button>
            </div>
          )}
        </div>
        <div className="divide2" />
        <div className="perfumeDetail5 flex justify-center">
          {perfumeReviewList}
        </div>
      </div>
    </div>
  );
}
export default PerfumeDetail;
