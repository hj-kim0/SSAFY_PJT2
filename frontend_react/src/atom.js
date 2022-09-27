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
