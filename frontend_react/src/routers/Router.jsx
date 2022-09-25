import React from "react";
import { Route, Routes } from "react-router-dom";

import MainNavBar from "@components/common/MainNavBar";
import Home from "@screens/Home";
import Footer from "@components/common/Footer";
import PersonalPerfume from "../screens/PersonalPerfume";

function Router() {
  return (
    <>
      <MainNavBar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/personal" element={<PersonalPerfume />} />
      </Routes>
      <Footer/>
    </>
  );
}
export default Router;
