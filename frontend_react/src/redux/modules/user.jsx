// import { createAction } from "@reduxjs/toolkit";
// import produce from "immer";
// import { renderSync } from "node-sass";

// //actions
// const SET_USER = "SET_USER";
// const LOG_OUT = "LOG_OUT";
// const USER_IMG = "USER_IMG";

// //init
// const initialState = {
//     user: null,
//     is_login: null
// }

// //actionCreators
// const setUser = createAction(SET_USER, (user, is_login) => ({
//     user,
//     is_login
// }));

// const logOut = createAction(LOG_OUT, () => {});

// const userImg = createAction(USER_IMG, (userImage) => ({userImage}));

// //middleware

// const loginCheckApi = () => {
//     return function (dispatch, { history }) {
//         instance
//             .get("/user/islogin")
//             .then((res) => {
//                 dispatch(
//                     setUser({
//                         nickname : res.data.nickname,
//                         profile : res.data.profile
//                     })
//                 );
//             })
//             .catch((err) => {
//                 console.log("ERR", err.respnose);
//                 localStorage.removeItem("token");
//                 window.location.replace("/login");
//             });
            
//     };
// };


// const logOutApi = () => {
//     return function (dispatch) {
//         localStorage.removeItem("token");
//         dispatch(logOut());
//         window.location.replace("/login");
//     };
// };

// const loginByKakao = (code) => {
//     return function (dispatch, getState, { history }){
//         instance
//             .get(`/user/kakao?code=${code}`)
//             .then((res) => {
//                 const token = res.headers.authorization;
//                 localStorage.setItem("token", token[1]);
//                 history.push("/");
//                 instance
//                     .get("/user/isLogin")
//                     .then((res) =>{
//                         dispatch(
//                             setUser({
//                                 nickname: res.data.nickname,
//                             })
//                         );
//                     })
//                     .catch((err) => console.log("ERR", err));
//             })
//             .catch((err) => {
//                 console.log("ERR", err);
//                 history.replace("/login");
//             });
//     };
// };


// //reducer
// export default handleActions(
//     {
//         [SET_USER] : (state, action) =>
//             produce(state, (draft) =>{
//                 draft.is_login = true;
//                 draft.user = action.payload.user;
//             }),
//             [LOG_OUT]: (state, action) =>
//             produce(state, (draft) => {
//                 draft.is_login = false;
//                 draft.user = null;
//                 localStorage.clear();
//             }),
//             [USER_IMG]: (state, action) =>
//             produce(state, (draft) => {
//                 draft.user = { ...state.user, imageUrl: action.payload.userImage };
//             }),
//     },
//     initialState
// );

// const actionCreators = {

//     loginCheckApi,
//     logOutApi,
//     loginByKakao
// }

// export { actionCreators };