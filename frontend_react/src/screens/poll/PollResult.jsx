import React from 'react';
import {Container, Box} from "@mui/material";
import "./PollResult_module.css"
import {useEffect} from "react";
import {Button} from "@mui/material";
import kakaoShare from "../../assets/images/btn-att-kak.png"
import {Link, useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";
import {surveyPerfume} from "../../atom";

const Card = (props) => {
    return(
        <div className="card">
            <img src={props.img} />
            <div className="card-body">
                <h2>{props.title}</h2>
                <p>파조향사 라반의 '100만 럭키'는 남성용 우디향으로 2018년 출시됐습니다. 이 향수의 조향사는 나탈리 그라시아-세토입니다. 탑 노트는 플럼, 오조닉 노트, 그레이프프루트, 베르가못, 미들 노트는 헤이즐넛, 꿀, 시더, 캐시미어 우드, 재스민, 오렌지 블러썸, 베이스 노트는 앰버우드, 파추리, 오크모스, 베티버입니다.</p>
                <h5>{props.author}</h5>
            </div>
        </div>
    )
}


const PollResult = () => {
    const navigate = useNavigate()
    const [resultPerfume, setResultPerfume2] = useRecoilState(surveyPerfume)

    useEffect(() => {
        const script = document.createElement('script');
        script.src = "https://developers.kakao.com/sdk/js/kakao.js";
        script.async = true;
        document.body.appendChild(script)
    }, []);

    const shareToKakao = () => {
        if(window.Kakao){
            const kakao = window.Kakao
            if(!kakao.isInitialized()){
                kakao.init("a6bb9cbde484dc4c12e60c1748f41b94")
            }

            kakao.Link.sendDefault({
                objectType : "feed",
                content : {
                    title : "나만의 향수",
                    description : "향수설명",
                    imageUrl : "https://fimgs.net/mdimg/perfume/375x500.48903.jpg",
                    link : {
                        mobileWebUrl : "http://j7c105.p.ssafy.io/personal",
                        webUrl : "http://j7c105.p.ssafy.io/personal"
                    }
                }
            })
        }
    }


    return (
        <>
            <div className="header">
                <h1>🎉 당신과 어울리는 향수입니다. 🎉</h1>
                <h3>-Recommended by <span className="text-rainbow">Perfectrum</span>-</h3>
            </div>

            <div onClick={() => navigate(`/detail/${resultPerfume.idx}`) } className='cards'>
                <Card
                    img='https://fimgs.net/mdimg/perfume/375x500.48903.jpg'
                    title='1 Million Lucky'
                    author='PACO RABANNE'
                />
            </div>
            {/* eslint-disable-next-line jsx-a11y/click-events-have-key-events,jsx-a11y/no-noninteractive-element-interactions */}
            <Box sx={{display : "flex", justifyContent : "center" }}>
                {/* eslint-disable-next-line jsx-a11y/click-events-have-key-events,jsx-a11y/no-noninteractive-element-interactions */}
                <img src={kakaoShare} style={{ objectFit : "cover", width:"20%", height: "10%", marginBottom : "20px" }} onClick={() => {
                    shareToKakao()
                }}/>
            </Box>

        </>
    )
};

export default PollResult;
