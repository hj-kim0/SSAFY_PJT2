import React from "react";
import { Route, Routes } from "react-router-dom";
import MainNavBar from "@components/common/MainNavBar";
import Home from "@screens/Home";
import InfoEdit from "@screens/user/InfoEdit";
import UserReview from "@screens/user/UserReview";
import PerfumeDetail from "@screens/perfume/PerfumeDetail";

function Router() {
  return (
    <>
      <MainNavBar />
      <Routes>
        {/* main */}
        <Route path="/" element={<Home />} />
        {/* user */}
        <Route path="/infoedit" element={<InfoEdit />} />
        <Route path="/userreview" element={<UserReview />} />
        {/* perfume */}
        <Route path="/perfumedetail" element={<PerfumeDetail />} />
      </Routes>
    </>
  );
}
export default Router;
