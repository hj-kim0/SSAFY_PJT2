import { useEffect } from "react";
import axios from "axios";
import qs from "qs";
import { useNavigate } from "react-router-dom";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = 'http://localhost:3000/oauth/kakao';

function Auth() {
    const coder = new URL(window.location.href).searchParams.get("code");
    const navigate = useNavigate();

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

            // let a = response.data.access_token;
            window.Kakao.init(REST_API_KEY);
            window.Kakao.Auth.setAccessToken(response.data.access_token);
            // console.log(response);
            navigate.replace("/");
        }catch(err){
            // console.log("response err");
        }
    };

    useEffect(() => {
        getToken();
    },[]);

    return null;
};

export default Auth;