import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import axios from "axios";
import { useRecoilValue } from 'recoil';
import { userProfileState, userState } from "../../atom";
import dummyProfile from "@images/icon/dummyIcon.png";
// import starRating from "@images/icon/star.svg";
import ystar from "@images/icon/ystar.png";
import long from "@images/icon/long.png";
import sillage from "@images/icon/sillage.png";
import "./PerfumeReview.scss";

function PerfumeReview(props) {
  const userProfile = useRecoilValue(userProfileState);
  const userLoginState = useRecoilValue(userState);
  const { id } = useParams();
  const [editInfo, setEditInfo] = useState(false);
  const navigate = useNavigate();
  const move = () => {
    navigate(`/detail/${id}`);
    window.location.reload();
  };
  const perfumeItem = props.item;
  const starList = [];
  let i = 0;
  for (; i < perfumeItem.totalScore; ++i) {
    starList.push(<span><img className="perfumeReview_profile_rating_ystar" src={ystar} alt="" /></span>)
  };
  const longList = [];
  let j = 0;
  for (; j < perfumeItem.longevity; ++j) {
    longList.push(<span><img className="perfumeReview_info_rating_long_emo" src={long} alt="" /></span>)
  };
  const sillageList = [];
  let k = 0;
  for (; k < perfumeItem.sillageScore; ++k) {
    sillageList.push(<span><img className="perfumeReview_info_rating_sil_emo" src={sillage} alt="" /></span>)
  };
  const deleteReview = () => {
    if (window.confirm("리뷰를 삭제하시겠습니까?")) {
      axios({
        method : "put",
        url : `http://j7c105.p.ssafy.io:8083/detail/${id}/review/delete/${perfumeItem.idx}`,
        headers : {
          Authorization : userLoginState.sToken
        }
      })
      .then(res => console.log(res))
      .catch(error => console.log(error))
      move();
    }
  };
  const editReview = () => {
    
  };
  console.log(perfumeItem);
  // console.log(userProfile[0].nickname);
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
            {perfumeItem.userNickname}
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
      <div className="perfumeReview_btns flex">
        <button className="perfumeReview_btns_edit" type="button">
          <EditIcon sx={{ fontSize: 36, color: "black"}} />
        </button>
        <button className="perfumeReview_btns_del" type="button" onClick={deleteReview}>
          <DeleteIcon sx={{ fontSize: 36, color: "black"}} />
        </button>
      </div>
    </div>
  );
}
export default PerfumeReview;
