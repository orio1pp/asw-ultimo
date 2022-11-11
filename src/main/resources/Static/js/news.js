let container = document.getElementById("main-container");
let newComment = "";

const changeNewComment = (NewComment) => {
    newComment = NewComment;
}

const likeBtn = async (btn) => {
    if (btn.src === "http://localhost:8081/images/heart-regular.svg") {
        btn.src = "../images/heart-solid.svg";
    }
    else {
        btn.src = "../images/heart-regular.svg";
    }
    let jsonSubmit = {
        username: localStorage.getItem("username")
    };
    const type = btn.id[0] + btn.id[1] + btn.id[2];
    if (type == "new") {
        let id = "";
        for (let x = 0; x < btn.id.length; ++x) {
            if (x > 2) id += btn.id[x];
        }
        const response = await fetch("http://localhost:8081/news/" + id + "/like", {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jsonSubmit)
        });
    }
    else {
        let id = "";
        for (let x = 0; x < btn.id.length; ++x) {
            if (x > 2) id += btn.id[x];
        }

        const response = await fetch("http://localhost:8081/comment/" + 70 + "/like", {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jsonSubmit)
        });
    }
}

const addComment = async (id) => {
    let jsonSubmit = {
        user: {
            username: localStorage.getItem("username")
        },
        body: newComment
    };
    const response = await fetch("http://localhost:8081/news/" + id + "/newcomment", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonSubmit)
    });
    await getNewsView(id);
}

async function getNewsView(id) {
    const response = await fetch("http://localhost:8081/news/" + id);
    const json = await response.json();
    container.innerHTML = "";
    let myhtml = `<div class="news-wrapper">`;
    const link = json.type == "ask" ? "http://localhost:8081/news/" + json.itemId : `${json.link}`;
    const idnew = "new"+ id;

    let hasUser = false;
    for (let k = 0; k < json.likedBy.length; ++k) {
        if (json.likedBy[k].username == localStorage.getItem("username"))
            hasUser = true;
    }
    const src = hasUser ? "../images/heart-solid.svg" : "../images/heart-regular.svg";

    if (json.type == "url") {
        myhtml += `<div class="news-upper">
                        <img id="${idnew}" class="like-btn" src='${src}' alt="heart" onclick='likeBtn(${idnew})' />
                        <a class="news-title" href="${link}">${json.title}</a>
                        <a class="news-page" href="${json.page_}">(${json.page_})</a>
                   </div>
                   <div class="news-lower">
                        <p class="news-info">${json.likedBy.length} points by <a>${json.username.username}</a> at ${json.datePublished}</p>
                        <p class="news-info news-comments">${json.comments.length} comments</p>
                   </div>
                   <div class="news-body">
                        ${json.text}
                   </div>
                   <div class="comment-form">
                        <form>
                            <input type="text" onchange="changeNewComment(value)" />
                            <input type="button" value="Add comment" class="add-comm-btn"  onclick="addComment(${id})"/>
                        </form>
                   </div>
                   <div class="news-comments">`;
    } else {
        myhtml += `
                                <div class="news-upper">
                                    <img id="${idnew}" class="like-btn" src='${src}' alt="heart" onclick='likeBtn(${idnew})' />
                                    <a class="news-title" href="${link}">${json.title}</a>
                                </div>
                                <div class="news-lower">
                                    <p class="news-info">${json.likedBy.length} points by <a>${json.username.username}</a> at ${json.datePublished}</p>
                                    <p class="news-info">${json.comments.length} comments</p>
                                </div>
                                <div class="news-body">
                                    ${json.text}
                               </div>
                               <div class="comment-form">
                                    <form>
                                        <input type="text" onchange="changeNewComment(value)" />
                                        <input type="button" value="Add comment" class="add-comm-btn" onclick="addComment(${id})"/>
                                    </form>
                               </div>
                               <div class="news-comments">`;
    }
    for (let i = 0; i < json.comments.length; ++i) {
        const idcomm = "com" + i;
        let cssclass = "comment";
        if (i == json.comments.length - 1) cssclass = "comment last-sub";

        let hasUserComment = false;
        for (let k = 0; k < json.comments[i].likedBy.length; ++k) {
            if (json.comments[i].likedBy[k].username == localStorage.getItem("username"));
                hasUserComment = true;
        }
        const srcComment = hasUserComment ? "../images/heart-solid.svg" : "../images/heart-regular.svg";

        myhtml += `<div class="${cssclass}">
                                        <div class="comment-info">
                                            <img id="${idcomm}" class="like-btn" src='${srcComment}' alt="heart" onclick='likeBtn(${idcomm})' />
                                            <p class="comment-points">0 points </p>
                                            <p class="comment-user"> by <span>${json.comments[i].user.username}</span> </p>
                                            <p class="comment-date"> at ${json.comments[i].time} </p>
                                        </div>
                                        <div class="comment-body">
                                            <p class="comment-text">${json.comments[i].body}</p>
                                        </div>
                                        <div class="comment-reply">
                                            <a class="reply-btn" onclick="() => {}" >reply</a>
                                        </div>
                                    </div>`;
    }
    myhtml += `</div></div>`;
    container.innerHTML = myhtml;
}

//getNewsView(id);