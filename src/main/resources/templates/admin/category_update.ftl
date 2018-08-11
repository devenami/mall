<link rel="stylesheet" href="/css/admin/category_update.css"/>

<div>
    <div class="category-item-update">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">名称：</span>
            </div>
            <input type="text" class="form-control" id="category-name" value="${category.name}"/>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">上级分类：</span>
            </div>
            <select class="form-control category-all" id="category-parent-id">
                <option value="0"></option>
                <#list list as c>
                    <#if category.parentId == c.id>
                        <option selected value="${c.id}">${c.name}</option>
                    <#else>
                        <option value="${c.id}">${c.name}</option>
                    </#if>
                </#list>
            </select>
        </div>
        <div class="input-group mb-3 error-wrapper" id="category-msg"></div>
        <div class="input-group mb-3">
            <button class="btn btn-success" onclick="updateCategory(this, ${category.id})" style="width: 100%">确定
            </button>
        </div>
    </div>
</div>

<script src="/js/admin/category_update.js"></script>
