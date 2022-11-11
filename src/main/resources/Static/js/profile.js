async function getUserVariables(username){
    const response = await fetch("http://localhost:8081/user?username="+username);
    return await response.json();
}
function dataCorrectForm(maxvisit, minaway, delay){
    return !isNaN(Number(maxvisit)) && !isNaN(Number(minaway)) && !isNaN(Number(delay))
}

function aboutCorrectSize(about){
    var regex = /\S/g;
    var length = about.split(regex).length - 1
    return length<255
}

function getInfoUser(){
    let username = localStorage.getItem("username")
    let userInformation = getUserVariables(username)

    var created = document.getElementById("created").value
    var karma = document.getElementById("karma").value
    var maxvisit = document.getElementById("maxvisit").value
    var minaway = document.getElementById("minaway").value
    var delay = document.getElementById("delay").value
    var about = document.getElementById("about").value
    var showdead = document.getElementById("showdead").checked
    var noprocrast = document.getElementById("noprocrast").checked
    let request = {
        username: username,
        created: created,
        karma: karma,
        maxvisit: maxvisit,
        minaway:minaway,
        delay:delay,
        about:about,
        showdead:showdead,
        noprocrast:noprocrast
    };
    if(dataCorrectForm(maxvisit, minaway, delay) && aboutCorrectSize(about)){
        fetch("http://localhost:8081/userk", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(request)
        })
    }
    else alert("Max visit, Min away and delay must be numbers")
}

async function setUserInfo() {
    let username = localStorage.getItem("username")
    let data = await getUserVariables(username)
    document.getElementById("username").value = data.username
    document.getElementById("created").value = data.created
    document.getElementById("consola").value = data.karma
    document.getElementById("maxvisit").value = data.maxvisit
    document.getElementById("minaway").value = data.minaway
    document.getElementById("delay").value = data.delay
    document.getElementById("about").value = data.about
    if(data.showdead == 1) document.getElementById("showdead").checked = true
    if(data.noprocrast == 1) document.getElementById("noprocrast").checked = true
}

setUserInfo()

function getUserSubmissions(){
    let username = localStorage.getItem("username")
    getNews("news/user?username="+username);
}

function getUserCommentaries(){
    getThreads();
}
