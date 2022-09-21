import React from "react";
import { Route, Routes } from "react-router-dom";
import MainNavBar from "@components/common/MainNavBar";
import Home from "@screens/Home";
import Login from "@screens/Login";
import Auth from "../Auth";
// import Signin from "@screens/Signin";

function Router() {
  return (
    <>
      <MainNavBar />
      <Routes>
        {/* main */}
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login/>}/>
        <Route path="/oauth/kakao" element={<Auth/>} />
      </Routes>
    </>
  );
}
export default Router;
