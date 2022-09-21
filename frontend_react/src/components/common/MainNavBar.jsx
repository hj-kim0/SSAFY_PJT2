import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

// style import
import "./MainNavBar.scss";
import navLogo from "@images/logo/logo2.svg";
import dummyProfile from "@images/icon/dummyIcon.png";
import "./NavToolTip.scss";

function MainNavBar() {
  const navigate = useNavigate();

  // const activeClassName = active => {
  //   const prefix = "left_nav__link fs-16 btn--";
  //   return active ? `${prefix}active` : `${prefix}unactive`;
  // };
  const logoutClick = () => {
    navigate("/");
  };
  const [openTool, setOpenTool] = useState(false);
  const openTooltip = () => {
    setOpenTool(!openTool);
  };
  const moveMyReview = () => {
    navigate("/userreview");
    if (openTool === true) {
      setOpenTool(!openTool);
    }
  };
  const moveEdit = () => {
    navigate("/infoedit");
    if (openTool === true) {
      setOpenTool(!openTool);
    }
  };

  return (
    <div className="wrapper flex align-center">
      <nav id="MainNavBar" className="flex align-center">
        <nav className="left_nav notoBold flex align-center">
          <Link to="/" className="left_nav__link flex">
            <img className="logo" title="!213" alt="logoImage" src={navLogo} />
          </Link>
          {/* <NavLink
            className={({ isActive }) => activeClassName(isActive)}
            to="/board"
          >
            향수모음
          </NavLink> */}
        </nav>
        <nav className="nav notoReg">
          <button type="button" className="nav_img fs-16" onClick={openTooltip}>
            <img src={dummyProfile} alt="" />
          </button>
        </nav>
      </nav>
      {openTool && (
        <div className="my_tool">
          <div className="my_tool_box flex justify-center column">
            <button
              type="button"
              to="/mypage/myfeed"
              className="my_tool_info flex align-center fs-13"
              onClick={moveMyReview}
            >
              내 리뷰
            </button>
            <button
              type="button"
              to="/infoedit"
              className="my_tool_edit flex align-center fs-13"
              onClick={moveEdit}
            >
              개인정보 수정
            </button>
            <button
              type="button"
              to="/myfeed"
              className="my_tool_logout flex align-center fs-13"
              onClick={logoutClick}
            >
              로그아웃
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default MainNavBar;
