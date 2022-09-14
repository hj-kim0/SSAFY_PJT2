import React from "react";
function MainNavBar() {
  const activeClassName = active => {
    const prefix = "left_nav__link fs-16 btn--";
    return active ? `${prefix}active` : `${prefix}unactive`;
  };
  return <div className="wrapper flex align-center">네브바</div>;
}
export default MainNavBar;
