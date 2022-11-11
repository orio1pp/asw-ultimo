const news = document.getElementById("main-container");
function setHtml(htmlUri){
    var xhr = new XMLHttpRequest();
    xhr.open('GET',htmlUri, true);
    xhr.onreadystatechange= function() {
        if (this.readyState!==4) return;
        if (this.status!==200) return; // or whatever error handling you want
        news.innerHTML = this.responseText;
    };
    xhr.send();
}
async function getThreads() {
    await setHtml('threads.html')
    var script = document.createElement('script');
    script.setAttribute("src", "js/threads.js")
    document.head.appendChild(script);
}

async function getProfile() {
    await setHtml('login.html')
    var script = document.createElement('script');
    script.setAttribute("src", "js/profile.js")
    document.head.appendChild(script);
}

async function getSingleNews(newsid) {
    /*var script = document.createElement('script');
    script.setAttribute("src", "js/news.js")
    document.head.appendChild(script);*/
    await getNewsView(newsid);
}

async function getSubmit() {
    await setHtml('submit.html')
    var script = document.createElement('script');
    script.setAttribute("src", "js/submit.js")
    document.head.appendChild(script);
}


const getNews = async (url) => {
    const response = await fetch("http://localhost:8081/" + url);
    const json = await response.json();
    let html = `<ul id="news">`;
    news.innerHTML = ""
    for (let i = 0; i < json.length; i++) {
        let cssclass = "submission";
        if (i == json.length - 1) cssclass = "submission last-sub"
        if (json[i].type == "url")
        {
            html += `<li class="${cssclass}">
                                <div class="submission-upper">
                                    <span class="like-btn"></span>
                                    <a class="submission-title" href="${json[i].link}" >${json[i].title}</a>
                                    <a class="submission-page" href="${json[i].page_}">(${json[i].page_})</a>
                                </div>
                                <div class="submission-lower">
                                    <p class="submission-info">${json[i].likedBy.length} points by <a>${json[i].username.username}</a> at ${json[i].datePublished}</p>
                                    <p class="submission-info sub-comments" onclick="getSingleNews(${json[i].itemId})">${json[i].comments.length} comments</p>
                                </div>
                           </li>`;
        }
        else {
            html += `<li class="${cssclass}">
                                <div class="submission-upper">
                                    <img class="like-btn" />
                                    <p class="submission-title" onclick="getSingleNews(${json[i].itemId})">${json[i].title}</p>
                                </div>
                                <div class="submission-lower">
                                    <p class="submission-info">${json[i].likedBy.length} points by <a>${json[i].username.username}</a> at ${json[i].datePublished}</p>
                                    <p class="submission-info sub-comments" onclick="getSingleNews(${json[i].itemId})">${json[i].comments.length} comments</p>
                                    
                                </div>
                           </li>`;
        }
    }
    news.innerHTML = html;
}

function checkLogged(){
    let username = localStorage.getItem("username");
    if(username !== null){
        changeButton(username);
    }
}
window.onload = function (){
    checkLogged();
    getNews("news");
}
