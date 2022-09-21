import { useEffect } from "react";
import axios from "axios";
import qs from "qs";
import { useNavigate } from "react-router-dom";
import cookies from "react-cookies";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = 'http://localhost:3000/oauth/kakao';

function Auth() {
    const coder = new URL(window.location.href).searchParams.get("code");
    const navigate = useNavigate();

    let accessToken = '';

        // 2. 토큰 받기
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
            
        }catch(err){
            console.log("getToken error");
        }
    };

    const sendToken = async () => 
            {
                const headers = {
                    'authToken' : accessToken,
                }
                try{
                    const res = await axios.get(
                        headers,
                        "http://j7c105.p.ssafy.io:8083/kakao",
                        
                    ); 
                    const acToken = res.data[`access-token`];
                    const expires = new Date();
                    expires.setMinutes(1); 
                    cookies.save(
                        'access_token',
                        acToken,
                        {
                            path: '/',
                            expires,
                        }
                    )
                    navigate.replace("/");
                }catch(err){
                    console.log("sendToken error");
                }
            }

            
    useEffect(() => {
        getToken();
    },[]);

    useEffect(() => {
        sendToken();
    },[]);

    
    return null;
};

export default Auth;