import React from 'react';
import {
  MDBFooter,
  MDBContainer,
  MDBCol,
  MDBRow,
} from 'mdb-react-ui-kit';
import notion from '../../assets/images/logo/notion.png';
// import 'mdb-react-ui-kit/dist/css/mdb.min.css'

const FooterPage = () => {
  return (
    <MDBFooter bgColor='black' className='text-white text-center text-lg-left'>
      <MDBContainer className='p-4'>
        <MDBRow>
          <MDBCol lg='12' md='12' className='mb-4 mb-md-0'>
            <h5 className='text-uppercase'>Contact Us</h5>
            <br></br>
            <p>
              <a href="https://www.notion.so/PJT2_C105_-8ba2738dcb49470ba9f4e9c1fd1fd88f">
              <img src={notion} width="50px"/>
              </a>
            </p>
          </MDBCol>
        </MDBRow>
    </MDBContainer>

      <div className='text-center p-3' style={{ backgroundColor: 'rgba(0, 0, 0, 0.2)' }}>
        &copy; {new Date().getFullYear()} Copyright:{' '}
        <a className='text-white' href='https://edu.ssafy.com/'>
          SSAFY.com
        </a>
      </div>
    </MDBFooter>
  );
}

export default FooterPage;