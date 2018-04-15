<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <meta name="google-site-verification" content="r1AIM4LL5RLVLCN5cU80A7Ya7DMcfpWYENJWcH1lzCU">
    <meta name="keywords" content="${keyword}">
    <meta name="description" content="${keyword} - 搜索结果">

    <title>${keyword} - 搜索结果</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css" media="all">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/main.js"></script>
</head>
<body>
<div id="wrap">
    <div id="tr-sp">
        <div class="bg-change"></div>
        <span class="tr">繁</span>
        <span class="sp">简</span>
    </div>
    <div id="header-wrap" class="wrap">
        <div id="header">
            <div id="title"><h1><a href="/">诗词歌赋</a></h1></div>
            <div id="search">
                <div method="get" id="search-form">
                    <input id="keyword" type="text" name="keyword" placeholder="" value="${keyword}">
                    <input id="searchsubmit" type="button" value="搜索">
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <div id="content-wrap" class="wrap">
        <div class="title-bar">获得约 ${total?string(',###')} 条结果（第${page}页）</div>
        <div id="content">
            <div id="poetry">
            <#list poetryBeanList as poetry>
                <div class="poetry-item search-item"><span class="search-item-title">
                    <a href="/poetry/${poetry.id?c}"  title="${poetry.title}">${poetry.title?replace(keyword, '<em>'+keyword+'</em>', 'r')}</a></span><span
                        class="search-item-author">[${poetry.author?replace(keyword, '<em>'+keyword+'</em>', 'r')}]</span>
                    <div class="search-item-content">${poetry.description?replace(keyword, '<em>'+keyword+'</em>', 'r')}</div>
                </div>
            </#list>
            </div>
            <div id="sidebar"></div>
            <div id="nav-bar">
                <input id="current-page" hidden="" value="${page?c}">
                <input id="total-page" hidden="" value="${totalPage?c}">
                <div class="pre-page pre-item">上一页</div>
                <div class="next-page next-item">下一页</div>
                <div class="clearfix"></div>
            </div>
        </div>

    </div>
    <div id="footer"></div>
</div>

</body>
</html>