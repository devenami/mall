<link rel="stylesheet" href="/css/admin/product.css"/>


<div>
    <div class="add">
        <button class="btn btn-success" style="width: 100%" id="product-add-btn" onclick="showOrHidden(this, 'product-item-update')">新增商品
        </button>
        <div class="product-item-update">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">名称：</span>
                </div>
                <input type="text" class="form-control" id="product-name"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">价格：</span>
                </div>
                <input type="number" class="form-control" id="product-price"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">数量：</span>
                </div>
                <input type="number" class="form-control" id="product-total"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">类别：</span>
                </div>
                <select class="form-control category-all" id="product-category">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">图片：</span>
                </div>
                <input type="file" class="form-control" id="product-image"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">描述：</span>
                </div>
                <textarea class="form-control" id="product-desc"></textarea>
            </div>
            <div class="input-group mb-3 error-wrapper" id="product-msg"></div>
            <div class="input-group mb-3">
                <button class="btn btn-success" onclick="addProduct()" style="width: 100%">确定</button>
            </div>
        </div>
    </div>



    <div class="list-group" id="product-list" style="margin-top: 20px">
        <div class="product-item">
            <a href="javascript:" class="list-group-item list-group-item-action"
               onclick="showOrHidden(this, 'product-item-update')">Cras
                justo odio</a>
            <div class="product-item-update" style="margin-top: 10px">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">名称：</span>
                    </div>
                    <input type="text" class="form-control"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">价格：</span>
                    </div>
                    <input type="number" class="form-control"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">数量：</span>
                    </div>
                    <input type="number" class="form-control"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">类别：</span>
                    </div>
                    <select class="form-control category-all">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">描述：</span>
                    </div>
                    <textarea class="form-control"></textarea>
                </div>
                <div class="input-group mb-3 error-wrapper"></div>
                <div class="input-group mb-3">
                    <button class="btn btn-success" onclick="addProduct()" style="width: 50%">修改</button>
                    <button class="btn btn-warning" onclick="addProduct()" style="width: 50%">删除</button>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="/js/admin/product.js"></script>
