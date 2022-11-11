let commentsection = document.getElementById("comments");

function getCommentariesAndRepleis(commentaries){
    if(commentaries.replies.length === 0)
        return;
    for(let i = 0; commentaries.replies.length>i; i++){
        commentsection.innerHTML += `<div class="reply comment">
                            <div class="comment-info">
                                <p class="comment-points">0 points ${commentaries.id}</p>
                                <p class="comment-user"> by ${commentaries.user.username}</p>
                                <p class="comment-date"> at ${commentaries.time}</p>
                            </div>
                                <div class="comment-body">
                                    <p class="comment-date">${commentaries.body}</p>
                                </div>
                            </div>
                        </div>`
        getCommentariesAndRepleis(commentaries.replies[i])
    }
}

async function printCommentaries(){
    let username = localStorage.getItem("newId") // en un futuro cambiar por username
    const response = await fetch("http://localhost:8081/comment/" + "1");
    const json = await response.json();
    getCommentariesAndRepleis(json)
}

window.onload = function (){
    printCommentaries();
}