import React from "react";

import KakaoLogin from '../assets/images/login/kakao_login_medium_narrow.png';  

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = 'http://localhost:3000/oauth/kakao';

const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

function Login() {
    return (
    <a href={KAKAO_AUTH_URI} aria-label="Kakao"><img src={KakaoLogin} alt=""/></a>
    )
};

export default Login;  