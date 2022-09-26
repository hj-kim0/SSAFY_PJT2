import React from "react";
import { Link, NavLink } from "react-router-dom";

import "./MainNavBar.scss";
import navLogo from "@images/logo/logo.png";

import { useRecoilState } from "recoil";

import { Button } from "@material-ui/core";
import { userState } from "../../atom";



function MainNavBar() {
  const [user, setUser] = useRecoilState(userState);

  const isLogged = user.isLogin;

  function handleLogoutClick() {
    setUser({isLogin: false});
  };

  let barInfo;
  
  if(isLogged){
    barInfo =  
    <nav className="right_nav notoReg">
      <Button variant="contained" color="white" onClick={handleLogoutClick()}>로그아웃</Button>
    </nav>
  }else{
    barInfo = 
    <NavLink className="right_nav__link fs-16" to="/login">로그인</NavLink>
  }

  // const chgPage = () =>{{
  //   if(userdata.isLogin){
  //   return <nav className="right_nav notoReg">
  //     <Button variant="contained" color="white" onClick={setUser({isLogin : true})}>로그아웃</Button>
  //     </nav>
  //   }
  //   return <nav className="right_nav notoReg">
  //         <NavLink className="right_nav__link fs-16" to="/login">
  //           로그인
  //         </NavLink>
  //       </nav>
  // }}

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
