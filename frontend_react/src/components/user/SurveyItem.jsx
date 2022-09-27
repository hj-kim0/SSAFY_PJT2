import React from "react";
import uuid from 'react-uuid';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { surveyState } from "../../atom";
import "./SurveyItem.scss";

function SurveyItem (props) {
  const survey = useRecoilValue(surveyState);
  const setSurvey = useSetRecoilState(surveyState);

  const surveyClick = (choice) => {
    const copy = JSON.parse(JSON.stringify(survey));
    copy.ptr = survey.ptr + 2;
    copy.data[survey.ptr].sentence = [choice]
    setSurvey(copy)
  }
  const lst = props.item.sentence.map((item) => (<button type="button" key={uuid()} onClick={() => {surveyClick(item)}}> {item}</button>))

  return (
    <div className="serveycontainer">
      {(props.item.step % 2 === 1) && <div> {props.item.sentence}</div>}
      {(props.item.step % 2 === 0) && lst}
    </div>
    
  );
}
export default SurveyItem;