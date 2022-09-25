import React from "react";
import { Route, Routes } from "react-router-dom";

import MainNavBar from "@components/common/MainNavBar";
import Home from "@screens/Home";
import Login from "@screens/Login";
import Auth from "../Auth";
import Token from "../Token";
import Footer from "@components/common/Footer";
import PersonalPerfume from "../screens/PersonalPerfume";

function Router() {
  return (
    <>
      <MainNavBar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login/>}/>
        <Route path="/oauth/kakao" element={<Auth/>} />
        <Route path="/oauth/token" element={<Token/>}/>
        <Route path="/personal" element={<PersonalPerfume />} />
      </Routes>
      <Footer/>
    </>
  );
}
export default Router;
