import React, {useState} from 'react';
import styled from 'styled-components';
import Question from './Question';

const Wrapper = styled.div`
    display: block;
    padding: 100px 0px 0px 0px;
    height: auto;
`;

const DivCenter = styled.div`
    margin: 30px 0px 30px 0px;
    text-align: center;
`;

const PersonalPerfume = () => {
    const [id, setId] = useState(0);
    // 1. 부모 컴포넌트에서 useState 를 통해 전달받은 데이터를 저장할 변수를 선언한다.
    // 2. 부모 컴포넌트에서 props로 함수를 넣어주면,
    //    자식 컴포넌트에서 그 함수를 이용해 값을 전달한다.

    const parentFnc = (idx) => {
        setId(idx);
    }
    return (
        <Wrapper>
            <DivCenter>
                <div>부모 컴포넌트 값 : {id}</div>
                <Question idx={id} getIdx={parentFnc} />
            </DivCenter>
        </Wrapper>
    );
};

export default PersonalPerfume;