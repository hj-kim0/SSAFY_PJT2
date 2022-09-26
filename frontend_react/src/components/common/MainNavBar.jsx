import React from "react";
import { Link, NavLink } from "react-router-dom";

import "./MainNavBar.scss";
import navLogo from "@images/logo/logo.png";

import { useRecoilState } from "recoil";

import { Button } from "@material-ui/core";
import  cookies  from "react-cookies";
import { userState } from "../../atom";




function MainNavBar() {
  const [user, setUser] = useRecoilState(userState);

  const User = user;

  function handleLogoutClick() {
    User.isLogin = false;
    cookies.remove("Kakao",[]);
    cookies.remove("Spring",[]);
    setUser(User);
  };

  let barInfo;
  
  console.log("메인네브");
  console.log(user);
  console.log(User);

  if(User.isLogin){
    barInfo =  
    <nav className="right_nav notoReg">
      <Button variant="contained" color="white" onClick={handleLogoutClick()}>로그아웃</Button>
    </nav>
  }else{
    barInfo = 
    <NavLink className="right_nav__link fs-16" to="/login">로그인</NavLink>
  }

  return (

    <div className="wrapper flex align-center">
      <nav id="MainNavBar" className="flex align-center">
        <nav className="left_nav notoBold flex align-center">
          <Link to="/" className="left_nav__link flex">
            <img className="logo" title="!213" alt="logoImage" src={navLogo} />
          </Link>
          <NavLink
            className=""
            to="/board"
          >
            향수모음
          </NavLink>
        </nav>
        {barInfo}
      </nav>
    </div>
  );
}

export default MainNavBar;
