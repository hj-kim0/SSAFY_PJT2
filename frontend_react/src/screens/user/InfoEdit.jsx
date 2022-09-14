import React from "react";
import { Link } from "react-router-dom";
import dummyProfile from "@images/icon/dummyIcon.png";
import "./InfoEdit.scss";
import SelectItem from "@components/user/SelectItem";

const sex = [
  { value: "men", name: "남성" },
  { value: "women", name: "여성" },
  { value: "unisex", name: "성별 무관" }
];
const season = [
  { value: "spring", name: "봄" },
  { value: "summer", name: "여름" },
  { value: "fall", name: "가을" },
  { value: "winter", name: "겨울" }
];
const likeScent = [
  { value: 1, name: "1" },
  { value: 2, name: "2" },
  { value: 3, name: "3" },
  { value: 4, name: "4" },
  { value: 5, name: "5" },
  { value: 6, name: "6" },
  { value: 7, name: "7" },
  { value: 8, name: "8" }
];

function InfoEdit() {
  const infoEdit = () => {
    window.location.href = "/infoedit";
  };
  return (
    <div className="container flex justify-center">
      <div id="infoedit" className="infoedit">
        <div id="infoedit1" className="infoedit1 flex justify-center">
          <div className="infoedit1_title notoBold fs-28">개인정보 수정</div>
          <img
            src={dummyProfile}
            alt="Profile_Image"
            className="infoedit1_img"
          />
          <button className="infoedit1_btn notoBold fs-15" type="button">
            프로필 사진 변경
          </button>
          <div className="divide" />
        </div>
        <div id="infoedit2" className="infoedit2">
          <div className="infoedit2_title notoBold fs-15">닉네임</div>
          <input
            type="text"
            className="infoedit3_input notoMid fs-14"
            placeholder="2~8자리의 문자로 입력해주세요"
          />
        </div>
        <div id="infoedit3" className="infoedit3">
          <div className="infoedit3_title notoBold fs-15">성별</div>
          {/* <input
            type="text"
            className="infoedit3_input notoMid fs-14"
            placeholder="2~8자리의 문자로 입력해주세요"
          /> */}
          <SelectItem options={sex} />
        </div>
        <div id="infoedit4" className="infoedit4">
          <div className="infoedit4_title notoBold fs-15">선호하는 계절</div>
          {/* <input
            type="number"
            className="infoedit4_input notoMid fs-14"
            placeholder="10~11자리의 숫자로 입력해주세요"
          /> */}
          <SelectItem options={season} />
        </div>
        <div id="infoedit5" className="infoedit5">
          <div className="infoedit5_title notoBold fs-15">선호하는 향기</div>
          {/* <input
            type="text"
            className="infoedit5_input notoMid fs-14"
            value="카카오톡"
            readOnly
          /> */}
          <SelectItem options={likeScent} />
        </div>
        <div id="infoedit6" className="infoedit6 flex justify-center">
          <button
            className="infoedit6_btn notoBold fs-18"
            type="button"
            onClick={infoEdit}
          >
            수정 완료
          </button>
          <div className="infoedit6_drop notnoMid fs-12">
            <Link to="/drop">탈퇴하기</Link>
          </div>
        </div>
      </div>
    </div>
  );
}
export default InfoEdit;
