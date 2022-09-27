import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import dummyImg from "@images/icon/perfumeImg.svg";
import favorite from "@images/icon/favorite_black(2).svg";
import dummyProfile from "@images/icon/dummyIcon.png";
import ratingStar from "@images/icon/star.svg";
// import InfoReview from "@components/user/InfoReview";
import PerfumeReview from "@components/user/PerfumeReview";
import { getDetail } from "../../apis/perfume";
import "./PerfumeDetail.scss";

function PerfumeDetail() {
  const { id } = useParams();
  const [perfumeDetail, setPerfumeDetail] = useState({});
  const detail = async () => {
    const body = {
      type: "최신순",
      lastIdx: null,
      lastTotalScore: null,
      lastLikeCount: null,
      pageSize: 4
    };
    const res = await getDetail(id, body);
    setPerfumeDetail(res.perfume);
  };
  console.log(perfumeDetail);

  useEffect(() => {
    detail();
  }, []);
  return (
    <div className="container flex justify-center">
      <div id="perfumeDetail" className="perfumeDetail">
        <div id="perfumeDetail1" className="perfumeDetail1 flex">
          <div className="perfumeDetail1_img">
            <img src={perfumeDetail.perfumeImg} alt="Perfume_Img" />
          </div>
          <div className="perfumeDetail1_contents flex justify-center align-center notoBold fs-16">
            <p>{perfumeDetail.description}</p>
          </div>
        </div>
        <div id="perfumeDetail2" className="perfumeDetail2 flex">
          <div className="perfumeDetail2_title flex">
            <div className="perfumeDetail2_title_name notoBold fs-20">
              {perfumeDetail.perfumeName}
            </div>
            <div className="perfumeDetail2_title_like flex">
              <div className="perfumeDetail2_title_like_img">
                <img src={favorite} alt="favorite_Img" />
              </div>
              <div className="perfumeDetail2_title_like_number notoBold fs-18">
                20
              </div>
            </div>
          </div>
          <div className="perfumeDetail2_info flex align-center notoBold fs-18">
            <div className="perfumeDetail2_info_season">{perfumeDetail.seasons}</div>
            <div className="perfumeDetail2_info_time">{perfumeDetail.timezone}</div>
          </div>
        </div>
        <div className="divide1" />
        <div id="perfumeDetail3" className="perfumeDetail3 flex align-center">
          <div className="perfumeDetail3_profile flex">
            <div className="perfumeDetail3_profile_img">
              <img src={dummyProfile} alt="프로필이미지" />
            </div>
            <div className="perfumeDetail3_profile_rating">
              <img src={ratingStar} alt="프로필별점" />
            </div>
          </div>
          <div className="perfumeDetail3_input flex align-center">
            {/* <input className="perfumeDetail3_input_input" type="input" /> */}
            <button
              className="perfumeDetail3_input_btn notoReg fs-12"
              type="button"
            >
              입력
            </button>
          </div>
        </div>
        <div className="perfumeDetail4 flex">
          <div className="perfumeDetail4_sort flex">
            <div className="perfumeDetail4_sort_new notoBold fs-18">최신순</div>
            <div className="perfumeDetail4_sort_best notoReg fs-18">추천순</div>
          </div>
        </div>
        <div className="divide2" />
        <div className="perfumeDetail5 flex justify-center">
          <PerfumeReview />
          <PerfumeReview />
          <PerfumeReview />
        </div>
      </div>
    </div>
  );
}
export default PerfumeDetail;
