import { atom } from 'recoil'
import {recoilPersist} from "recoil-persist";

const { persistAtom } = recoilPersist()

export const pollAtom = atom({
    key : "atom",
    default : {
        gender : '',
        season : '',
        longevity : '',
        accordDivide : '',
        accordClass : '',
    },
    effects_UNSTABLE : [persistAtom]
})

export const userState = atom({
    key: "userState",
    default: {
        isLogin : null,
        sToken: "",
        kToken: "",
    },
    effects_UNSTABLE: [persistAtom],
});

export const surveyPerfume = atom({
    key : "surveyPerfume",
    default : {
        idx : "",
        perfume_name : "",
        brand_name : "",
        perfume_img : "",
        description : ""
    },
    effects_UNSTABLE: [persistAtom],
})

export const userProfileState = atom({
    key: "userProfileState",
    default: {
        "idx": -1,
        "userId": "",
        "profileImg": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIUAAABpCAIAAACbPneSAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAD8SURBVHhe7dFBDQAwCACx2UPnEIqNPi6pg74/G0cflj4sfVj6sPRh6cPSh6UPSx+WPix9WPqw9GHpw9KHpQ9LH5Y+LH1Y+rD0YenD0oelD0sflj4sfVj6sPRh6cPSh6UPSx+WPix9WPqw9GHpw9KHpQ9LH5Y+LH1Y+rD0YenD0oelD0sflj4sfVj6sPRh6cPSh6UPSx+WPix9WPqw9GHpw9KHpQ9LH5Y+LH1Y+rD0YenD0oelD0sflj4sfVj6sPRh6cPSh6UPSx+WPix9WPqw9GHpw9KHpQ9LH5Y+LH1Y+rD0YenD0oelD0sflj4sfVj6sPRh6cPSh6UPyewBt75BTHRWB6IAAAAASUVORK5CYII=", 
        "nickname": "닉네임",
        "gender": "성별",
        "seasons": "시즌",
        "accordClass": "클래스"
    },
    effects_UNSTABLE: [persistAtom],
});

export const surveyState = atom({
    key: "surveyState",
    default: {
        "ptr": 1,
        "data": [
                { "step" : 1,
                "sentence": ["성별을 알려주세요."],
                },
                { "step" : 2,
                "sentence": ["남자", "유니섹스", "여자"],
                },
                { "step" : 3,
                "sentence": ["선호하는 계절을 알려주세요."],
                },
                { "step" : 4,
                "sentence": ["봄", "여름", "가을", "겨울"],
                },
                { "step" : 5,
                "sentence": ["다음중 좋아하는 향을 선택해주세요."],
                },
                { "step" : 6,
                "sentence": ["매운 향", "톡쏘는 향","야성적인 향","인공적인 향",
                "꽃 향기", "풀 향기", "과일 향", "달콤한 향"],
                },                                        
            ],
               
    }
});

