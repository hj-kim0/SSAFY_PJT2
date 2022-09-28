import { React, useEffect }  from "react";
// import { Link } from "react-router-dom";
import dummyProfile from "@images/icon/dummyIcon.png";
import gear from "@images/icon/gear.png";
import InfoReview from "@components/user/InfoReview";
import "./UserReview.scss";
import { useNavigate } from "react-router-dom";
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { userProfileState } from "../../atom";
import { getUserProfile } from "../../apis/user";

function UserReview() {
  const navigate = useNavigate();

  const userProfile = useRecoilValue(userProfileState);
  const setUserProfile = useSetRecoilState(userProfileState);
  console.log(userProfile)
  
  const editProfile = () => {
    // 라우터 이동
    navigate("/infoedit");
  }
  useEffect(() => {
    // 사용자 정보 받아오기
  }, [])
  
  return (
    <div className="container flex justify-center">
      <div id="userReview" className="userReview">
        <div id="userReview1" className="userReview1 flex justify-center">
        { !userProfile.profileImg && <img
            src={dummyProfile}
            alt="Profile_Image"
            className="userReview1_profileimg"
          />}
          { !!userProfile.profileImg && <img
            src={userProfile.profileImg}
            alt="Profile_Image"
            className="userReview1_profileimg"
          />}
          {/* 수정 버튼 위치 */}
          <button type="button" className="userReview1_editbutton" onClick={editProfile}>
            <img src={gear} className="userReview1_gear" alt="" />
          </button>
          <div className="userReview1_nickname notoBold fs-24">
            {userProfile.nickname}
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
