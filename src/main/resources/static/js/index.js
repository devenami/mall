// 自动加载的项目
$(function () {

    fullCategory();

});

// 启动时填充所有分类
function fullCategory() {
    const url = '/api/category/get';

    $.get(url, function (result) {
        const data = result.data;
        setFirstCategory(data);
    });
}

// 填充一级分类，同时填充子分类
function setFirstCategory(firstCategories) {
    const firstSelect = $('#cat-0');
    firstSelect.empty();
    let options = '';
    for (let i = 0; i < firstCategories.length; i++) {
        const firstCategory = firstCategories[i];
        options += '<option value="' + firstCategory.id + '">' + firstCategory.name + '</option>>'
    }
    firstSelect.html(options);

    // 填充二级分类
    let firstCategory = firstCategories[0];
    if (firstCategory) {
        let secondCategories = firstCategory.subCategories;
        setSecondCategory(secondCategories);
    }
}

// 填充二级分类，同时填充子分类
function setSecondCategory(secondCategories) {
    const secondSelect = $('#cat-1');
    secondSelect.empty();
    let options = '';
    for (let i = 0; i < secondCategories.length; i++) {
        let secondCategory = secondCategories[i];
        options += '<option value="' + secondCategory.id + '">' + secondCategory.name + '</option>'
    }
    secondSelect.html(options);

    // 填充三级分类
    let secondCategory = secondCategories[0];
    if (secondCategory) {
        let thirdCategories = secondCategory.subCategories;
        setThirdCategory(thirdCategories);
    }
}

// 填充三级分类
function setThirdCategory(thirdCategories) {
    let thirdSelect = $('#cat-2');
    thirdSelect.empty();
    let options = '';
    for (let i = 0; i < thirdCategories.length; i++) {
        let thirdCategory = thirdCategories[i];
        options += '<option value="' + thirdCategory.id + '">' + thirdCategory.name + '</option>';
    }
    thirdSelect.html(options);
}

// 用户选择产品分类的触发的函数
function selectCategory(select) {
    let id = select.id;
    let categoryId = select.options[select.selectedIndex].value;

    const url = '/api/category/get';
    $.get(url,
        {
            id: categoryId
        },
        function (result) {
            let categories = result.data;

            if (id === 'cat-0') {
                setSecondCategory(categories);
            }
            if (id === 'cat-1') {
                setThirdCategory(categories)
            }

            loadProducts(categoryId);
        });
}

// TODO 加载用户选择的商品
function loadProducts(categoryId) {

}