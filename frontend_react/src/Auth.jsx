import { useEffect } from "react";
import axios from "axios";
import qs from "qs";
import { useNavigate } from "react-router-dom";
import cookies from "react-cookies";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = 'http://j7c105.p.ssafy.io:80/oauth/kakao';

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
            // console.log("카카오");
            const response = await axios.post(
                "https://kauth.kakao.com/oauth/token", payload
            );
            const sendToken = async () => 
            {
                const accessToken = response.data.access_token;
                const headers = {
                    'authToken' : accessToken,
                }

                // console.log(headers);
                try{
                    const res = await axios.get(
                        "http://j7c105.p.ssafy.io:8083/kakao",
                        headers,
                        { withCredentials: true }
                    ); 

                    const acToken = res.data[`access-token`];
                    
                    // console.log(acToken);

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

                }catch(err){
                    navigate.replace("/");
                    // console.error("sendToken err");
                }
            }

            sendToken();
            // console.log("백엔드");
            navigate.replace("/");
        }catch(err){
            navigate.replace("/");
            // console.error("getToken err");
        }
    };



    // const sendToken = async () => {
        // const accessToken = window.Kakao.Auth.getAccessToken();
        // console.log(accessToken);
        // const headers = {
        //     authToken : accessToken,
        // }
        // try{
        //     const response = await axios.post(
        //         "http://http://j7c105.p.ssafy.io:8083/kakao", headers
        //     );

        //     const expires = new Date();

        //     expires.setMinutes(10);
        //     cookies.save(
        //         'refresh_token',
        //         response.data.refresh_token,
        //         {
        //             path: '/',
        //             expires,
        //         }
        //     )
            // navigate.replace("/");
        // }catch(err){
        //     console.error();
        // }
    // };

    useEffect(() => {
        getToken();
    },[]);

    
    // useEffect(() => {
    //     sendToken();
    // },[]);


    return null;
};

export default Auth;