import React from "react";
import { Route, Routes } from "react-router-dom";
import MainNavBar from "@components/common/MainNavBar";
import Home from "@screens/Home";
import InfoEdit from "@screens/user/InfoEdit";

function Router() {
  return (
    <>
      <MainNavBar />
      <Routes>
        {/* main */}
        <Route path="/" element={<Home />} />
        {/* user */}
        <Route path="/infoedit" element={<InfoEdit />} />
      </Routes>
    </>
  );
}
export default Router;
