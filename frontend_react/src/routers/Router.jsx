import React from "react";
import { Route, Routes } from "react-router-dom";
import MainNavBar from "@components/common/MainNavBar";
import Home from "@screens/Home";
import Signin from "@screens/Signin";

function Router() {
  return (
    <>
      <MainNavBar />

      <Routes>
        {/* main */}
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Signin/>}/>
      </Routes>
    </>
  );
}
export default Router;
