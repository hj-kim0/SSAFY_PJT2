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