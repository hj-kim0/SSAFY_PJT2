import React from "react";
import { Link, NavLink } from "react-router-dom";
import "./MainNavBar.scss";
import navLogo from "@images/logo/logo.png";
import { useNavigate }  from "react-router-dom";
import { useRecoilState } from "recoil";

import { Button } from "@material-ui/core";
import { userState , userProfileState } from "../../atom";
import Rec from "./Rec";


function MainNavBar() {
  const user = useRecoilState(userState);
  console.log(user);
  const userProfile = useRecoilState(userProfileState);

  const navigate = useNavigate();

  console.log(userProfile);
  const handleLogoutClick = (e) => {
    e.preventDefault();
    window.localStorage.removeItem('recoil-persist');
    window.location.replace("/");
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
          {(user[0].isLogin !== null)&&
            <NavLink
            className="left_nav_향 분석"
            to="/tasteanalysis"
          >
            향 분석
          </NavLink>}
        </nav>
        <nav>

          {user[0].isLogin ? 
            <><button className="notoBold fs-16" type="button" onClick={handleLogoutClick}>로그아웃</button>
            <button className="notoBold fs-16" type="button" onClick={() => {
              console.log(`/userreview/${userProfile[0][0].nickname}`);
              navigate(`/userreview/${userProfile[0][0].nickname}`)}}>마이페이지</button></> 
            : 
          <NavLink className="right_nav__link notoBold fs-16" to="/login">
            로그인
          </NavLink>
          }
        </nav>
      </nav>
    </div>
  );
}

export default MainNavBar;
