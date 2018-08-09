// 自动加载的项目
$(function () {
    // 填充分类
    fullCategory();

    // 填充热卖
    loadHotProduct();

});

// 启动时填充所有分类
function fullCategory() {
    const url = '/api/category/get';

    $.get(url, function (result) {
        const data = result.data;
        setFirstCategory(data);

        let cid;
        // 加载产品
        let first = data[0];
        if (first) {
            cid = first.id;
            let second = first.subCategories[0];
            if (second) {
                cid = second.id;
                let third = second.subCategories[0];
                if (third) {
                    cid = third.id;
                }
            }
        }

        loadProducts(cid, null, 0);

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

            loadProducts(categoryId, null, 0);

        });
}

/**
 * 控制当前查询产品列表时的方式
 * 搜索 or 分类
 */
let globalCategoryId;
let globalKeyword;

function loadProducts(categoryId, keyword, pageNo) {

    let url = '/api/product/get/category/keyword';

    let param = {page_no: pageNo, page_size: 20, category_id: null, keyword: null};
    if (categoryId) {
        globalCategoryId = categoryId;
        globalKeyword = null;
        param.category_id = categoryId;
    }
    if (keyword) {
        globalKeyword = keyword;
        globalCategoryId = null;
        param.keyword = keyword;
    }

    loadFromServer(url, 'product-list', param);
}

function loadHotProduct() {
    const url = "/api/hot/get/page/0/5";
    loadFromServer(url, 'hot-list');
}

function loadFromServer(url, htmlId, param) {
    const hotList = $('#' + htmlId);
    hotList.empty();
    let content = '';
    $.get(url, param, function (result) {
        const products = result.data;
        for (let i = 0; i < products.length; i++) {
            let product = products[i];
            content += '<div class="item" onclick="product_detail(' + product.id + ')">' +
                '  <img class="img-thumbnail" src="' + product.image + '">' +
                '  <span>' + product.name + '</span>' +
                '</div>'
        }
        hotList.html(content);

        if (result.pageSize) {
            const pageNo = $('#page-no');
            const pageTotal = $('#page-total');

            pageNo.empty();
            pageTotal.empty();
            pageNo.text(result.pageNo + 1);
            pageTotal.text(result.totalPages);
        }

    });
}

function product_detail(id) {
    location.href = '/user/detail.html?id=' + id;
}

function jumpToPage(flag) {
    let pageNo = $('#page-no').text();
    if (flag) {
        // 上一页
        if (pageNo > 1) {
            btnClickLoadProduct(pageNo - 2);
        }
    } else {
        // 下一页
        let totalPage = $('#page-total').text();
        if (pageNo < totalPage) {
            btnClickLoadProduct(pageNo);
        }
    }
}

function btnClickLoadProduct(pageNo) {
    if (globalCategoryId) {
        loadProducts(globalCategoryId, null, pageNo);
    } else if (globalKeyword) {
        loadProducts(null, globalKeyword, pageNo);
    }
}

function searchProduct() {
    let keyword = $('#search_keyword').val();

    if (keyword) {
        loadProducts(null, keyword, 0);
    }

}

function userLogout() {
    const url = '/api/user/logout';
    $.post(url, function (result) {
        if (result.code === 1) {
            location.href = '/index.html'
        }
    })
}