import { React, useState, useRef, useEffect}  from "react";
import { Link } from "react-router-dom";
import dummyProfile from "@images/icon/dummyIcon.png";
import "./InfoEdit.scss";
// import SelectItem from "@components/user/SelectItem";
import Select from "@components/user/SelectItem";
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { userProfileState } from "../../atom";

const sex = [
  { value: "unisex", name: "성별 무관" },
  { value: "men", name: "남성" },
  { value: "women", name: "여성" }
];
const season = [
  { value: "spring", name: "봄" },
  { value: "summer", name: "여름" },
  { value: "fall", name: "가을" },
  { value: "winter", name: "겨울" }
];
const likeScent = [
  { value: "citrus", name: "상큼한 향" },
  { value: "floral", name: "꽃 향" },
  { value: "herbal", name: "허브 향" },
  { value: "fruity", name: "과일 향" },
  { value: "spicy", name: "강렬한 향" },
  { value: "animalic", name: "야성적인 향" },
  { value: "synthetic", name: "합성 향" },
  { value: "sweet", name: "달콤한 향" }
];

function InfoEdit() {
  const userProfile = useRecoilValue(userProfileState);
  const setUserProfile = useSetRecoilState(userProfileState);
  const [isChecked, setIsChecked] = useState(0);
  const inputRef = useRef();
  const nicknameRef = useRef();
  const genderRef = useRef();
  const seasonsRef = useRef();
  const accordClassRef = useRef();


  const completeEdit = () => {
    if (isChecked) {

      // 백엔드 보내서 로직 진행
      console.log(userProfile.profileImg);
      console.log(genderRef.current.value);
      console.log(seasonsRef.current.value);
      console.log(accordClassRef.current.value);
      // window.location.href = "/userreview";
    } else {
      alert("닉네임 중복 체크를 해주세요")
    }
  };


  const editImg = (e) => {
    e.preventDefault();
    inputRef.current.click();
  };


  const handleChangeFile = async (event) => {
    const imgFile = event.target.files[0];
  
    // (동기) 파이어베이스 이미지 전송 로직
    // 
    // const changedProfile = {...userProfile};
    // changedProfile.profileImg = ;
    // setUserProfile(changedProfile);
  

  };


  const checkNickname = () => {

    console.log(nicknameRef.current.value);
    // 닉네임 중복 체크
    setIsChecked(1);
  }

  return (
    <div className="container flex justify-center">
      <div id="infoedit" className="infoedit">
        <div id="infoedit1" className="infoedit1 flex justify-center">
          <div className="infoedit1_title notoBold fs-28">개인정보 수정</div>
          { !userProfile.profileImg && <img
            src={dummyProfile}
            alt="Profile_Image"
            className="infoedit1_profileimg"
          />}
          { !!userProfile.profileImg && <img
            src={userProfile.profileImg}
            alt="Profile_Image"
            className="infoedit1_profileimg"
          />}
          <input type="file" className="infoedit1_imginput" name="imgFile" id="imgFile" ref={inputRef} onChange={handleChangeFile}/>
          <button className="infoedit_top_btn notoBold fs-15" type="button" onClick={editImg} >
            프로필 이미지 변경
          </button>
          <div className="divide" />
        </div>
        <div id="infoedit2" className="infoedit2">
          <div className="infoedit2_title notoBold fs-15">닉네임 변경</div>
          <input
            type="text"
            className="infoedit3_input notoMid fs-14"
            placeholder="2~8자리의 문자로 입력해주세요"
            ref={nicknameRef}
          />
          <button className="infoedit2_btn notoBold fs-15" type="button" onClick={checkNickname}>중복 체크</button>
        </div>
        <div id="infoedit3" className="infoedit3">
          <div className="infoedit3_title notoBold fs-15">성별</div>

          <Select className="infoedit3_input notoMid fs-14" ref={genderRef}>
            <option value="default" disabled>성별을 선택해주세요</option>
            {sex.map((item) => (
              <option key={item.value} value={item.value}>
                {item.name}
              </option>
            ))}
          </Select>
        </div>
        <div id="infoedit4" className="infoedit4">
          <div className="infoedit4_title notoBold fs-15">선호하는 계절</div>
          <Select className="infoedit4_input notoMid fs-14" ref={seasonsRef}>
            <option value="default" disabled>좋아하는 계절을 선택해주세요</option>
            {season.map((item) => (
              <option key={item.value} value={item.value}>
                {item.name}
              </option>
            ))}
          </Select>
        </div>
        <div id="infoedit5" className="infoedit5">
          <div className="infoedit5_title notoBold fs-15">선호하는 향기</div>
          <Select className="infoedit5_input notoMid fs-14" ref={accordClassRef}>
            <option value="default" disabled>좋아하는 향을 선택해주세요</option>
            {likeScent.map((item) => (
              <option key={item.value} value={item.value}>
                {item.name}
              </option>
            ))}
          </Select>
        </div>
        <div id="infoedit6" className="infoedit6 flex justify-center">
          <button
            className="infoedit6_btn notoBold fs-18"
            type="button"
            onClick={completeEdit}
          >
            수정 완료
          </button>
          {/* <div className="infoedit6_drop notnoMid fs-12">
            <Link to="/drop">탈퇴하기</Link>
          </div> */}
        </div>
      </div>
    </div>
  );
}
export default InfoEdit;
