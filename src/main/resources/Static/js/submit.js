let title, url, body;

function changeTitle(newTitle){
    title = newTitle;
}
function changeUrl(newUrl){
    url = newUrl;
}
function changeBody(newBody){
    body = newBody;
}

async function submitNews() {
    let username = localStorage.getItem("username")
    console.log(url)
    if(typeof url != 'undefined' && url !=null) page = url.split('/')[2];
    else{
        url = null;
        page = null;
    }
    let jsonSubmit = {
        title: title,
        page_: page,
        username: {
            username: username
        },
        link: url,
        text: body
    };
    await fetch("http://localhost:8081/submit", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonSubmit)
    });
}