<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <title></title>
    <link rel="stylesheet" href="/css/style.css" type='text/css' media='all'/>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/main.js"></script>
</head>
<body>
<div id="wrap">
    <input id="poetry-page-id" hidden value="${poetryBean.id}"/>
    <div id="tr-sp"><input id="tr-sp-value" value="0" hidden ><span class='tr'>繁</span><span class='sp'>简</span></div>
    <div id="header-wrap" class="wrap">
        <div id="header">
            <div id="search">
                <div method="get" id="search-form">
                    <input id="keyword" type="text" name="keyword" placeholder="">
                    <input id="searchsubmit" type="button" value="搜索">
                </div>
            </div>
        </div>
    </div>
    <div id="content-wrap" class="wrap">
        <div id="content">
            <div id="poetry">
            </div>
            <div id="nav-bar">
            </div>
        </div>

    </div>
    <div id="footer"></div>
</div>
</body>
</html>