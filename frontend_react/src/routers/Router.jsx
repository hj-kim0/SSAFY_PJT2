import React from "react";
import { Route, Routes } from "react-router-dom";

import MainNavBar from "@components/common/MainNavBar";
import Home from "@screens/Home";
import InfoEdit from "@screens/user/InfoEdit";
import UserReview from "@screens/user/UserReview";
import PerfumeSearch from "@screens/perfume/PerfumeSearch";
import PerfumeDetail from "@screens/perfume/PerfumeDetail";
import TasteAnalysis from "@screens/perfume/TasteAnalysis";
import Login from "@screens/Login";
import Auth from "../Auth";
import Token from "../Token";
import Footer from "../components/common/Footer";
import PersonalPerfume from "../screens/PersonalPerfume";
import PollGender from "../screens/poll/PollGender";
import PollStart from "../screens/poll/PollStart";

function Router() {
  return (
    <>
      <MainNavBar />
      <Routes>
        <Route path="/" element={<Home />} />
        {/* user */}
        <Route path="/infoedit" element={<InfoEdit />} />
        <Route path="/userreview" element={<UserReview />} />
        {/* perfume */}
        <Route path="/perfumesearch" element={<PerfumeSearch />} />
        <Route path="/detail/:id" element={<PerfumeDetail />} />
        <Route path="/tasteanalysis" element={<TasteAnalysis />} />
        <Route path="/login" element={<Login/>}/>
        <Route path="/oauth/kakao" element={<Auth/>} />
        <Route path="/oauth/token" element={<Token/>}/>
        <Route path="/personal" element={<PersonalPerfume />} />
        <Route path="/poll" element={<PollStart/>} />
      </Routes>
      <Footer/>
    </>
  );
}
export default Router;
