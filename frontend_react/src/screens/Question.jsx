import React from 'react';

import styled from 'styled-components';

const Content = styled.p`
    font-family: LoveYaLikeASister;
    text-align: center;
    margin: 15px 15px 0px 0px;
    font-size: 64px;
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
    margin-top: 30%;
    color: white;
    background-color: black;
    border-radius: 8px;
    padding: 3px 45px 3px 45px;
    font-size: 32px;
    font-family: NotoSansBold;
`


const Question = (props) => {
        const {idx, getIdx} = props; // 비구조화 할당 문법

        const onClick = () => {
            getIdx(idx + 1);
        };

        const questions = [
            {question: '', ans: ''},
            {question: '선호하는 계절을 알려주세요.', ans: ['spring', 'summer', 'fall', 'winter']},
            {question: '성별을 알려주세요.', ans: ['man', 'woman', 'unisex']},
            {question: '활동하는 시간을 알려주세요?', ans: ['day', 'night']},
            {question: '선호하는 메인향은?', ans: ['floral', 'modern']}   
        ];

        const list = []

        console.log(questions[idx].ans.length);
        console.log(questions[idx].ans[0]);
        for(let i = 0; i < questions[idx].ans.length; i+1){
            const a = questions[idx].ans;
            list.push(<StyledButton key={a[i]} onClick={
                event => {
                    event.preventDefault();
                    onClick();
                }
            }>{a[i]}</StyledButton>)
        }

        let content = null;

        if(idx === 0){
            content = 'PERSONAL PERFUME';
            list.push(<StyledButton key='start' onClick={onClick}>시작하기</StyledButton>)
        }

        console.log(list);

        return (
            <div>
                <Content>{content}</Content>
                <SubContent>{questions[idx].question}</SubContent>
                {list}
            </div>
        );
    };

export default Question;