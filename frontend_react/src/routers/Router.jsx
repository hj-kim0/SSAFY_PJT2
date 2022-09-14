import React from "react";
import { Route, Routes } from "react-router-dom";
import MainNavBar from "@components/common/MainNavBar";
import Home from "@screens/Home";

function Router() {
  return (
    <>
      <MainNavBar />

      <Routes>
        {/* main */}
        <Route path="/" element={<Home />} />
      </Routes>
    </>
  );
}
export default Router;
