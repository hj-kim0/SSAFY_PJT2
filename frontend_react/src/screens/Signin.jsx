/* eslint-disable jsx-a11y/control-has-associated-label */
/* eslint-disable jsx-a11y/alt-text */
import React from 'react';
// { useEffect} from 'react';
// import axios from "axios";
// import { useNavigate } from "react-router-dom";
import KakaoLogin from '../assets/images/login/kakao_login_medium_narrow.png';  

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = 'http://j7c105.p.ssafy.io:8083/oauth/kakao';

const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

// const code = new URL(window.location.href).searchParams.get('code');

// const navigate = useNavigate();

// useEffect(() => {
//     (async () => {
//         try {
//         const res = await axios.get(`api/code=${code}`);
//         const token = res.headers.authorization;
//         window.localStorage.setItem('token', token);
//         navigate('/');
//     } catch (e) {
//         navigate('/');
//     }
//     })();
// }, []);

function Signin (){
    return (    
        // eslint-disable-next-line global-require
        <a href={KAKAO_AUTH_URI}><img src={KakaoLogin}/></a>
    )
}

export default Signin;  