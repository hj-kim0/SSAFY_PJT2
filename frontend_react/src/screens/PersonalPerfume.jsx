import React from 'react';

// import { Button } from '@material-ui/core';

import styled from 'styled-components'

const DivCenter = styled.div`
    margin: 30px 0px 30px 0px;
    text-align: center;
`;

const Wrapper = styled.div`
    display: block;
    padding: 100px 0px 0px 0px;
    height: auto;
`;

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
    padding-left: 15px;
    padding-right: 15px;
    color: white;
    background-color: black;
    border-radius: 8px;
    padding: 3px 45px 3px 45px;
    font-size: 32px;
    font-family: NotoSansBold;
`

function PersonalPerfume (){
    return (
        <Wrapper>
            <DivCenter>
                <Content>
                    PERSONAL PERFUME
                </Content>
                <SubContent>
                    당신의 가치를 빛내줄 퍼스널 향수 추천
                </SubContent>
            </DivCenter>
            <DivCenter>
                <StyledButton>
                    시작하기
                </StyledButton>
            </DivCenter>
        </Wrapper>
    )
}

export default PersonalPerfume;