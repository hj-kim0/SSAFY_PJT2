import { useEffect } from "react";
import axios from "axios";
import qs from "qs";
import { useNavigate } from 'react-router-dom';

import { useRecoilState } from "recoil";

import { userState } from "./atom";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
// const REDIRECT_URI = 'http://localhost:3000/oauth/kakao';
const REDIRECT_URI = 'http://j7c105.p.ssafy.io/oauth/kakao';

function Auth() {
    const coder = new URL(window.location.href).searchParams.get("code");

    let accessToken = '';

    const [user, setUser] = useRecoilState(userState);


    const User = user;

    const navigate = useNavigate();
    const getToken = async () => {
        const payload = qs.stringify({
            grant_type: "authorization_code",
            client_id: REST_API_KEY,
            redirect_uri: REDIRECT_URI,
            code: coder,
        });
        try{
            const response = await axios.post(
                "https://kauth.kakao.com/oauth/token", payload
            );
            accessToken = response.data.access_token;   
            
            //카카오 엑세스토큰 저장
            User.kToken = accessToken;
            console.log("카카오 엑세스토큰 저장");
            console.log(User.kToken);
            setUser(User);

        }catch(err){
            console.log(err);
        }
    };     

    const getToken2 = async () => {

        const AT = User.kToken;
        try{
            const response2 = await axios.get("http://j7c105.p.ssafy.io:8083/kakao",{
                headers: {
                    "Context-Type": "application/json",
                    "authToken": AT,
                }
            });

            console.log("스프링 엑세스토큰 저장");
            User.sToken = response2.data["access-token"];
            console.log(User.sToken);
            console.log("스프링 엑세스토큰 저장");
            User.isLogin = true;
            console.log(User.isLogin);
            setUser(User);
            navigate("/")
        }catch(err){
            console.log(err);
        }
    };

    useEffect(() => {
        getToken();
    },[]);

    useEffect(() => {
        getToken2();
    },[]);
    
};
export default Auth;