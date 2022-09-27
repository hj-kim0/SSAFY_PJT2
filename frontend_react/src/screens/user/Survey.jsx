import { React, useState } from "react";
import uuid from 'react-uuid';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { surveyState } from "../../atom";
import "./Survey.scss";
import SurveyItem from "../../components/user/SurveyItem"





function Survey() {
  const survey = useRecoilValue(surveyState);
  const setSurvey = useSetRecoilState(surveyState);
  const list = Object.values(survey.data).map((item)=>(<SurveyItem key={uuid()} item={item}/>))

  const completeSurvey = () => {
    // 리코일에서 정보 받아다가 백엔드 요청
    window.location.href = "/";
    
  }

  return (
    <div className="container flex justify-center">

          <div className="surveytitle_window">
          {list.slice(0, survey.ptr + 1)}
          </div>

          <button type="button" className="surveytitle_button" onClick={completeSurvey}>설문 완료</button>
    </div>
  )
};

export default Survey;