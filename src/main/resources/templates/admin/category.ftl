<link rel="stylesheet" href="/css/admin/category.css"/>


<div>

    <div class="add">
        <button class="btn btn-success" style="width: 100%" onclick="showOrHidden(this, 'category-item-update')">新增分类
        </button>
        <div class="category-item-update">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">名称：</span>
                </div>
                <input type="text" class="form-control" id="category-name"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">上级分类：</span>
                </div>
                <select class="form-control category-all" id="category-parent-id">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>
            <div class="input-group mb-3 error-wrapper" id="category-msg"></div>
            <div class="input-group mb-3">
                <button class="btn btn-success" onclick="addCategory()" style="width: 100%">确定</button>
            </div>
        </div>
    </div>

    <div class="category-list-s">
        <div class="list-group" id="c1-list">
            一级分类：
            <div class="list-group-item list-group-item-action">
                <span>fghjkll</span>
                <input type="hidden"/>
                <button class="btn btn-warning btn-sm">删除</button>
                <button class="btn btn-success btn-sm">修改</button>
            </div>
        </div>
        <div class="list-group" id="c2-list">
            二级分类：
            <div class="product-item">
                <div class="list-group-item list-group-item-action">
                    <span>fghjkll</span>
                    <button class="btn btn-warning btn-sm">删除</button>
                    <button class="btn btn-success btn-sm">修改</button>
                </div>
            </div>
        </div>
        <div class="list-group" id="c3-list">
            三级分类：
            <div class="product-item">
                <div class="list-group-item list-group-item-action">
                    <span>fghjkll</span>
                    <button class="btn btn-warning btn-sm">删除</button>
                    <button class="btn btn-success btn-sm">修改</button>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="/js/admin/category.js"></script>
