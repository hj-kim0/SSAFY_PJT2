import React from 'react';

import styled from 'styled-components';

import {Box} from "@mui/material";
import history from '../utils/history';

const Content = styled.div`
    font-family: LoveYaLikeASister;
    text-align: center;
    padding: 100px 0px 0px 0px;
    //margin: 15px 15px 0px 0px;
    font-size: 64px;
    //position: absolute;
`;

const BackgroundWrap = styled.div`
    background-image: url('https://www.10wallpaper.com/wallpaper/medium/1402/Nina-Brand_perfume_wallpaper_medium.jpg');
    background-size: cover;
    width: 100vw;
    height: 100vh;
    padding: 0px;
`;

const SubContent = styled.p`
    font-family: NotoSansMedium;
    text-align: center;
    margin: 15px 15px 0px 0px;
    font-size: 28px;
`;

const StyledButton = styled.button`
    width:360px;
    height:72px;
    margin-top: 15%;
    color: white;
    background-color: black;
    border-radius: 8px;
    padding: 3px 45px 3px 45px;
    font-size: 32px;
    font-family: NotoSansBold;
`


const Question = (props) => {
        const {id, getId} = props; // 비구조화 할당 문법

        const questions = [
            {question: '', ans: ''},
            {question: '선호하는 계절을 알려주세요.', ans: ['봄', '여름', '가을', '겨울']},
            {question: '성별을 알려주세요.', ans: ['남자', '여자', '유니섹스']},
            {question: '향이 오래갔으면 좋겠나요?', ans: ['네', '아니오']},
            {question: '향의 느낌을 선택해주세요', ans: ['강렬함', '부드러운']},
            {question: '다음중 좋아하는 향을 선택해주세요', ans: ['A', 'B','C','D']}   
        ];
        
        const onClick = () => {

            console.log(questions[id].ans.length);
            console.log(questions[id].ans[0]);
            
            if(id === 5){
                history.push('/');
            }
            else{
                getId(id + 1);
            }
            
        };

        

        const list = []

        for(let i = 0; i < questions[id].ans.length; i++){
            const a = questions[id].ans;
            
            list.push(<StyledButton key={a[i]} onClick={
                event => {
                    event.preventDefault();
                    onClick();
                }
            }>{a[i]}</StyledButton>)
        }

        let content = null;

        if(id === 0){
            content = 'PERSONAL PERFUME';
            list.push(<StyledButton key='start' onClick={onClick}>시작하기</StyledButton>)
        }

        console.log(list);
        return (
            <Box>
                {/*<img style={{ width : '100%' }} src="https://static.overlay-tech.com/assets/38a95fc7-fb7d-4c9a-8f8b-acde86a3f47f.svg" />*/}
                <BackgroundWrap>
                    <Content>{content}</Content>
                    <SubContent>{questions[id].question}</SubContent>
                     {list}
                </BackgroundWrap>
            </Box>
        );
    };

export default Question;