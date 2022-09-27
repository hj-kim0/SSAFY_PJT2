import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { Link } from "react-router-dom";
import Carousel from 'react-material-ui-carousel';
import CarouselSlider from 'react-carousel-slider';
import ImgItem from '../components/items/ImgItem';


const Wrapper = styled.div`
    display: block;
    height: auto;
`

const ContentWrapper = styled.div`
    display: block;
    height: auto;
    margin-top: 200px;
    margin-bottom: 200px;

`

const DivCenter = styled.div`
    margin: 30px 0px 30px 0px;
    text-align: center;
`;

function Home (){

    const [position, setPosition] = useState(0);

    function onScroll(){
      setPosition(window.scrollY);
    }

    useEffect(()=>{
      window.addEventListener("scroll", onScroll);
      return()=>{
        window.removeEventListener("scroll", onScroll);
      };
    },[]);


    const items = [
        {
            idx : 0,
            name: "first",
            src: require("../assets/images/carousel/8.jpg"),
        },
        {
            idx: 1,
            name: "second",
            src: require("../assets/images/carousel/2.jpg"),
        },
        {
            idx: 2,
            name: "third",
            src: require("../assets/images/carousel/3.jpg"),
        },
    ]

    const [cindex, setCindex] = useState(0);

    const handleChangeCindex = (cur, prev) =>{
        setCindex(cur);
        console.log(cur,prev);
    }

    const data = [
        {
          imgSrc:
            require("../assets/images/carousel/dummy.jpg"),

        },
        {
          imgSrc:
          require("../assets/images/carousel/dummy.jpg"),
        },
        {
          imgSrc:
          require("../assets/images/carousel/dummy.jpg"),
        }
      ];

  const SubContent = styled.p`
    font-family: NotoSansMedium;
    text-align: center;
    margin: 15px 15px 0px 0px;
    font-size: 32px;
`;

    const sliderBoxStyle = {
        height: "600px",
        width: "80%",
        background: "transparent",
        border: "1px solid #e1e4e8"
      };
      
      const itemsStyle = {
        width: "400px",
        height: "580px",
        padding: "5px",
        background: "transparent",
        border: "1px solid #e1e4e8",
        borderRadius: "2px",
      };
      

    const buttonSetting = {
        placeOn: "middle-outside",
        style: {
          left: {
            color: "#929393",
            background: "transparent",
            border: "1px solid #e1e4e8",
            borderRadius: "50%"
          },
          right: {
            color: "#929393",
            background: "transparent",
            border: "1px solid #e1e4e8",
            borderRadius: "50%"
          }
        }
      };

    const customSlideCpnts = data.map((item, index) => (
        <Link to={"/detail/" + index} key={index}>
            <img src={item.imgSrc}/>
        </Link>
        ));

    return (
        <><Wrapper>
            <Carousel
                index={cindex}
                onChange={handleChangeCindex}
                interval={4000}
                animation="fade"
                indicators={false}
                stopAutoPlayOnHover
                swipe
                className="main-carousel"
            >
                {items.map((item, i) => (
                    <ImgItem key={i} item={item} />
                ))}
            </Carousel>
        </Wrapper>
        <Wrapper>
            <ContentWrapper>
              <DivCenter style={{opacity: (position - 300)/1000}}>
                <SubContent>오늘의 향수</SubContent></DivCenter>
                <CarouselSlider 
                    slideCpnts={customSlideCpnts}
                    manner={{ circular: false }}
                    sliderBoxStyle={sliderBoxStyle}
                    buttonSetting={buttonSetting}
                    itemsStyle={itemsStyle}
                ></CarouselSlider>        
            </ContentWrapper>
            <ContentWrapper>
            <DivCenter style={{opacity: (position - 1100)/1000}}>
              <SubContent>BEST 향수</SubContent></DivCenter>
            <CarouselSlider 
                    slideCpnts={customSlideCpnts}
                    manner={{ circular: false }}
                    sliderBoxStyle={sliderBoxStyle}
                    buttonSetting={buttonSetting}
                    itemsStyle={itemsStyle}
                ></CarouselSlider>   
            </ContentWrapper>

        </Wrapper></>
    )
}

export default Home;