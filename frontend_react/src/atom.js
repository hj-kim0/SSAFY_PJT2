import { atom } from 'recoil'
import {recoilPersist} from "recoil-persist";

const { persistAtom } = recoilPersist()

export const pollAtom = atom({
    key : "atom",
    default : {
<<<<<<< HEAD
        gender : '',
        season : '',
        longevity : '',
        accordDivide : '',
        accordClass : '',
    },
    effects_UNSTABLE : [persistAtom]
})
=======
        gender : "",
        season : "",
        longevity : "",
        accordDivide : "",
        accordClass : "",
    }
})

export const userState = atom({
    key: 'userState',
    default: {
        isLogin : false,
    }
});
>>>>>>> c2038c28d6b159fc6e30ece76f213e2274b566bd
