<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <meta name="google-site-verification" content="r1AIM4LL5RLVLCN5cU80A7Ya7DMcfpWYENJWcH1lzCU" />
    <meta name="keywords" content="诗词歌赋,唐诗,宋词">
    <meta name="description" content="诗词歌赋">

    <title></title>
    <link rel="stylesheet" href="/css/style.css" type='text/css' media='all'/>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/base.js"></script>
    <script src="/js/main.js"></script>
</head>
<body>
<div id="wrap">
    <div id="tr-sp"><div class="bg-change"></div><span class='tr'>繁</span><span class='sp'>简</span></div>
    <div id="header-wrap" class="wrap">
        <div id="header">
            <div id="title"><h1><a href="/">诗词歌赋</a></h1></div>
            <div id="search">
                <div method="get" id="search-form">
                    <input id="keyword" type="text" name="keyword" placeholder="">
                    <input id="searchsubmit" type="button" value="搜索">
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <div id="content-wrap" class="wrap">
        <div id="content">
            <div id="poetry" class="poetry-index">
                <div class="poetry-item index-item">
                    <div id="poetry-header">
                        <div id="poetry-title">
                            <h2>${poetry.title}</h2>
                        </div>
                        <div id="poetry-author">${poetry.author}</div>
                    </div>
                    <div id="poetry-content">
                    <#list poetry.contentList as content>
                        <p class="content-p" style="display: block">${content}</p>
                    </#list>
                    </div>
                </div>
            </div>
            <div id="sidebar"></div>
            <div id="nav-bar">
            </div>
        </div>

    </div>
    <div id="footer"></div>
</div>
</body>
</html>