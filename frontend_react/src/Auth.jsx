import { useEffect } from "react";
import axios from "axios";
import qs from "qs";
import { useNavigate } from 'react-router-dom';
import cookies from "react-cookies";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
// const REDIRECT_URI = 'http://localhost:3000/oauth/kakao';
const REDIRECT_URI = 'http://j7c105.p.ssafy.io/oauth/kakao';
function Auth() {
    const coder = new URL(window.location.href).searchParams.get("code");

    let accessToken = '';

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
            const expires = new Date();
            expires.setMinutes(1);

            cookies.save("Kakao", accessToken);

            console.log(cookies.load("Kakao"));
            // navigate('/oauth/token');


        }catch(err){
            console.log(err);
        }
    };     

    const getToken2 = async () => {

        const AT = cookies.load("Kakao");

        const headers = {
            'authToken': AT,
        };

        try{
            const response2 = await axios.get(headers, "http://j7c105.p.ssafy.io:8083/kakao");
            console.log(response2.data);
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