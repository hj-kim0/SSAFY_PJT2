import React from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import "./MainNavBar.scss";
import navLogo from "@images/logo/logo.png";

import { useRecoilState } from "recoil";

import { Button } from "@material-ui/core";
import { userState } from "../../atom";

function MainNavBar() {
  const user = useRecoilState(userState);

  const handleLogoutClick = (e) => {
    e.preventDefault();
    window.localStorage.removeItem('recoil-persist');
    window.location.replace("/");
    };

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
        {
        user.isLogin ? 
        <nav className="right_nav notoReg">
          <Button variant="contained" color="white" onClick={handleLogoutClick}>로그아웃</Button>
        </nav> : 
        <NavLink className="right_nav__link fs-16" to="/login">
          로그인
        </NavLink>
    }
    <Button variant="contained" color="white" onClick={handleLogoutClick}>로그아웃</Button>
    <NavLink className="right_nav__link fs-16" to="/login">
          로그인
        </NavLink>
      </nav>
    </div>
  );
}

export default MainNavBar;
