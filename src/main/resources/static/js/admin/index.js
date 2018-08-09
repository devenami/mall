function loadPage(url) {
    $('#content').load(url);
}


function userLogout() {
    const url = '/api/user/logout';
    $.post(url, function (result) {
        if (result.code === 1) {
            location.href = '/index.html'
        }
    })
}