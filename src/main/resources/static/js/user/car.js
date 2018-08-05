function htmlToggle(car) {
    $(car).siblings('.car-item-update').toggle();
}

function changeTotal(input) {
    // 下单数量
    let num = $(input).val();
    let operate = $(input).parent().siblings().eq(0).children().eq(0).children().eq(1).children().eq(0);
    let price = operate.children().eq(1).children('input').val().replace(',', '');
    $(input).parent().siblings().eq(1).children('input').val(num * price);

}

function delFromCar(btn, id) {

    let url = '/api/car/delete/' + id;

    $.ajax({
        url: url,
        type: 'delete',
        success: function (result) {
            if (result.code === 1 && result.data === 1) {
                $(btn).parent().parent().parent().css('display', 'none')
            }
        }
    })

}

function addToOrder(btn, id) {

    const url = '/api/order/add';

    $.post(url,
        {
            car_id: id
        },
        function (result) {
            console.log(result);

            let msg = $('#id-float-msg');
            msg.empty();

            if (result.code === 1 && result.data) {
                msg.text('下单成功');
                msg.css('display', 'block');
                $(btn).parent().parent().parent().css('display', 'none')
            } else {
                msg.text(result.msg);
                msg.css('display', 'block');
            }

            setInterval(function () {
                msg.css('display', 'none');
            }, 3000);

        });

}