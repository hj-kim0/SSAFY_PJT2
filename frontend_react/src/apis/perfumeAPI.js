const SERVER_URL = 'http://j7c105.p.ssafy.io:8083'

export const surveyResult = async (season,gender,longevity,accordClass) => {
    const URL = `${SERVER_URL}/survey`

    if(longevity === "네"){
        // eslint-disable-next-line no-param-reassign
        longevity = 1
    }else{
        // eslint-disable-next-line no-param-reassign
        longevity = 0
    }
    console.log(season,gender,longevity,accordClass)
    const response = await fetch(URL, {
        method : "POST",
        headers: {
            "Content-type": "application/json",
        },
        body : JSON.stringify({
            season : season,
            gender : gender,
            longevity : longevity,
            accordClass : accordClass
        })
    })
    return response
}

export const fetchMainPerfume = async () => {
    const URL = `${SERVER_URL}/main`

    const response = await fetch(URL,{
        method : "GET",
        headers : {
            "Content-type": "application/json",
        }
    })
    return response
}

export const fetchMainPerfumeUser = async (data) => {
    const URL = `${SERVER_URL}/main`

    const response = await fetch(URL,{
        method : "GET",
        headers : {
            "Content-type": "application/json",
            "Authorization": data,
            
        }
    })
    return response
}

export const fetchSearchPerfume = async ({gender, duration, accordClass}) => {
    const URL = `${SERVER_URL}/search`
    console.log(gender,duration,accordClass)
    const response = await fetch(URL, {
        method : "POST",
        headers : {
            "Content-type": "application/json"
        },
        body : {
            gender : gender,
            duration : duration,
            accordClass : accordClass,
            lastIdx : null,
            pageSize : 1
        }
    })
    return response
}