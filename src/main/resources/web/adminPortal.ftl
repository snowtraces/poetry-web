<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>

    <title>诗词歌赋-后台管理页面</title>
    <link rel="stylesheet" href="/css/admin.css" type='text/css' media='all'/>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/base.js"></script>
    <script src="/js/admin.js"></script>
</head>

<body>
<div id="wrap">
    <div id="admin-left">
        <div id="admin-post" class="admin-left-1">
            <div class="admin-left-title">文章</div>
            <ul>
                <li id="edit-poetry">编辑文章</li>
                <li id="add-poetry">添加文章</li>
            </ul>
        </div>
    </div>

    <div id="admin-right">
        <div id="edit-poetry-content">
            <div id="search">
                <div method="get" id="search-form">
                    <input id="keyword" type="text" name="keyword" placeholder="">
                    <input id="searchsubmit" type="button" value="搜索">
                </div>
            </div>
            <div id="poetry-list">
            </div>
            <div id="poetry-single">
            </div>
            <div id="poetry-nav">
                <input id="current-page" hidden >


            </div>

        </div>
        <div id="add-poetry-content"></div>


    </div>
</div>

</body>

</html>