$(function () {
    loadPage('/admin/product.html');
});

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

function showOrHidden(btn, divClass) {
    $(btn).siblings('.' + divClass).toggle();
}


function getAllCategory(callback) {
    const url = '/api/category/get/all';
    $.get(url, function (result) {
        if (result.code === 1) {
            callback(result.data);
        }
    });
}