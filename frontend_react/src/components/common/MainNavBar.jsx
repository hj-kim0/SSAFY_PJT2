import React from "react";
import { Link, NavLink } from "react-router-dom";

import "./MainNavBar.scss";
import navLogo from "@images/logo/logo.png";

import { useRecoilState } from "recoil";

import { Button } from "@material-ui/core";
import { userState } from "../../atom";

function MainNavBar() {
  const [user, setUser] = useRecoilState(userState);

  const User = user;

  const handleLogoutClick = (e) => {
    e.preventDefault();
    console.log("로그아웃 수행")
    User.isLogin = false;
    User.sToken = '';
    User.kToken = '';
    setUser(User);
    console.log(user);
  };

  return (

    <div className="wrapper flex align-center">
      <nav id="MainNavBar" className="flex align-center">
        <nav className="left_nav notoBold flex align-center">
          <Link to="/" className="left_nav_home flex">
            <img className="logo" title="!213" alt="logoImage" src={navLogo} />
          </Link>
          <NavLink
            className="left_nav_personal"
            to="/personal"
          >
            향수추천
          </NavLink>
          <NavLink
            className="left_nav_search"
            to="/perfumesearch"
          >
            향수찾기
          </NavLink>
          <NavLink
            className="left_nav_향 분석"
            to="/tasteanalysis"
          >
            향 분석
          </NavLink>
        </nav>
        { !(!User.isLogin) && <nav className="right_nav notoReg">
      <Button variant="contained" color="white" onClick={handleLogoutClick}>로그아웃</Button>
    </nav>}
        { !(User.isLogin) &&
    <NavLink className="right_nav__link fs-16" to="/login">로그인</NavLink>}
      </nav>
    </div>
  );
}

export default MainNavBar;
