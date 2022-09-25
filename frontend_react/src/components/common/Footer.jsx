import React from 'react';

function Footer(){
    return(
        <footer className="bottom"
        style={{
            background: "#343a40",
            color: "#ffffff",
            textAlign: "justify",
            bottom: 0,
            width: "100%",
            position: "relative",
            margin: 0,
            padding: 30,
            borderSize: "border-box"
        }}>
    <div className="container">
        <div className="row">
            <div className="col-md-4 col 12">
            <h3
            style={{
                backgroundColor: "white",
                color: "black",
                width: 110,
                height: 35,
                marginTop: 5,
                borderStyle: "solid",
                borderColor: "black",
                border: 1,
                borderRadius: 7
            }}
            >
                Find Us
            </h3>

            <div>
                <div>SSAFY</div>
                <div>C105</div>
            </div>
            </div>
            <div className="col-md-4 col-12" style={{ marginTop: 40 }}>
            <h4 style={{ color: "lightgrey" }}> Resources </h4>
            <ul className="list-unstyled">
                <li>Privacy Policy </li>
            </ul>
            </div>
            <div className="col-md-4 col-12" style={{ marginTop: 40 }}>
            <h4 style={{ color: "lightgrey", textAlign: "justify" }}>
                {" "}
                About{" "}
            </h4>
            <p>
                -
            </p>
            </div>
            <div className=" col-12">
            <i
            className="fa fa-facebook-official"
            aria-hidden="true"
            style={{
                padding: 10,
                color: "white",
                float: "left",
                fontSize: 25
            }}
            >
            {" "}
            </i>
            <i
            className="fa fa-twitter-square"
            aria-hidden="true"
            style={{
                padding: 10,
                color: "white",
                float: "left",
                fontSize: 25
            }}
            ></i>
            <i
            className="fa fa-github-square"
            aria-hidden="true"
            style={{
                padding: 10,
                color: "white",
                float: "left",
                fontSize: 25
            }}
            ></i>
        </div>
        <div
            className="col-12"
            style={{ fontSize: 14, color: "lightgrey", textAlign: "center" }}
        >
            &copy; 2022 SSAFY | All Rights Reserved
        </div>
        </div>
        </div>
        </footer>
    )
}

export default Footer;