$(function () {
    $('#sub').click(sub);
    $('#add').click(add);
});

function addToCar(id) {

    let num = $('#order-number').val();
    const url = '/api/car/add';
    $.post(url,
        {product_id: id, total: num},
        function (result) {

            let msg = $('#id-float-msg');
            msg.empty();

            if (result.code === 1 && result.data) {
                msg.text('加入购物车成功');
                msg.css('display', 'block');
            }
            else if (result.code === 4) {
                location.href = '/user/login.html'
            }
            else {
                msg.text(result.msg);
                msg.css('display', 'block');
            }

            setInterval(function () {
                msg.css('display', 'none');
            }, 3000);
        });
}

function add() {
    opt('+');
}

function sub() {
    opt('-');
}

function opt(o) {
    const price = $('#price').val().replace(',', '');
    const total = $('#order-total-price');
    const less = $('#less');
    const iptNum = $('#order-number');
    let num = iptNum.val();

    if (o === '+') {
        if (num < less) {
            iptNum.val(++num);
            total.empty();
            total.text(num * price);
        }
    } else if (o === '-') {
        if (num > 1) {
            iptNum.val(--num);
            total.empty();
            total.text(num * price);
        }
    }
}