import React from "react";
import { Route, Routes } from "react-router-dom";
import MainNavBar from "@components/common/MainNavBar";
// import Home from "@screens/Home";
import PersonalPerfume from "../screens/PersonalPerfume";

function Router() {
  return (
    <>
      <MainNavBar />
      <Routes>
        {/* main */}
        <Route path="/" element={<PersonalPerfume />} />
      </Routes>
    </>
  );
}
export default Router;
