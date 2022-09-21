import React from "react";
import { Route, Routes } from "react-router-dom";
import MainNavBar from "@components/common/MainNavBar";
import Home from "@screens/Home";
import InfoEdit from "@screens/user/InfoEdit";
import UserReview from "@screens/user/UserReview";
import PerfumeSearch from "@screens/perfume/PerfumeSearch";
import PerfumeDetail from "@screens/perfume/PerfumeDetail";
import TasteAnalysis from "@screens/perfume/TasteAnalysis";

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
        <Route path="/perfumesearch" element={<PerfumeSearch />} />
        <Route path="/perfumedetail" element={<PerfumeDetail />} />
        <Route path="/tasteanalysis" element={<TasteAnalysis />} />
      </Routes>
    </>
  );
}
export default Router;
