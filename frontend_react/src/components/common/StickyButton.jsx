import React, { useState } from "react";
import LiveHelpTwoToneIcon from '@mui/icons-material/LiveHelpTwoTone';
import CloseRoundedIcon from '@mui/icons-material/CloseRounded';
import navLogo from "@images/logo/logo.png";
import book from "@images/icon/book.png";
import "./StickyButton.scss";

function StickyButton() {
  const [openMenu, setOpenMenu] = useState(false);
  const [openBook, setOpenBook] = useState(false);
  const openMenuBar = () => {
    setOpenMenu(!openMenu);
  };
  const openBookOpt = () => {
    setOpenBook(!openBook);
    console.log(openBook);
  };
  return (
    <div className="float">
      <div className="sticky">
        {!openMenu && (
          <button className="sticky_btn" type="button" onClick={openMenuBar}>
            <LiveHelpTwoToneIcon fontSize="large" />
          </button>
        )}
        {openMenu && (
          <div className="sticky_menuBar flex">
            <div className="sticky_menuBar_title flex">
              <img className="sticky_menuBar_title_img" title="!213" alt="logoImg" src={navLogo} />
              <button className="sticky_menuBar_title_btn" type="button" onClick={openMenuBar}>
                <CloseRoundedIcon />
              </button>
            </div>
            <div className="sticky_menuBar_toBook flex">
              <button className="sticky_menuBar_toBook_btn" type="button" onClick={openBookOpt}>
                정보 알아보기
              </button>
            </div>
          </div>
        )}
      </div>
      {openBook && (
        <div className="book">
          <img className="book_img" alt="rollImg" src={book} />
        </div>
      )}
    </div>
  );
}
export default StickyButton;