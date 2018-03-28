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
    <div id="header-wrap" class="wrap">
        <div id="header">
            <div id="search">
                <form method="get" id="search-form" action="/poetry/search">
                    <input id="keyword" type="text" name="keyword" placeholder="">
                    <input id="searchsubmit" type="submit" value="搜索">
                </form>
            </div>
        </div>
    </div>
    <div id="content-wrap" class="wrap">
        <div id="content">
            <div id="poetry">
                <div id="poetry-header">
                    <div id="poetry-id" hidden></div>
                    <div id="poetry-title"><h1></h1></div>
                    <div id="poetry-author"></div>
                </div>
                <div id="poetry-content">
                </div>
            </div>
            <div id="nav-bar">
                <div class="pre-poetry">上一篇</div>
                <div class="next-poetry">下一篇</div>
                <div class="clearfix"></div>
            </div>
        </div>

    </div>
    <div id="footer"></div>
</div>
</body>
</html>