<!DOCTYPE html>
<html lang="zh-CN">

<#include "template/head.ftl" >

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <meta name="google-site-verification" content="r1AIM4LL5RLVLCN5cU80A7Ya7DMcfpWYENJWcH1lzCU">
    <meta name="keywords" content="<#if (poetry.tags)??><#list poetry.tags as tag>${tag}, </#list></#if><#if (poetry.keywords)??><#list poetry.keywords as keyword>${keyword}, </#list></#if>">
    <meta name="description" content="${poetry.description}">

    <title>${poetry.title} - ${poetry.author}</title>
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
            <div id="title">
                <h1>
                    <a href="/">诗词歌赋</a>
                </h1>
            </div>
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
            <div id="poetry">
                <div class="poetry-item poetry-single">
                    <input id="poetry-id" hidden="" value="${poetry.id?c}">
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
                    <div class="tool-bar">
              <span class="copy-poetry">
                <i class="material-icons">content_copy</i>
              </span>
                    </div>
                </div>
                <#if (author)??>
                <div class="author-detail">
                    <span class="author-name"><a
                            href="/poetry/search?keyword=author:${author.name}&amp;page=1">${author.name}</a></span><span
                        class="author-dynasty"><#if author.dynasty == "tang">唐<#elseif author.dynasty == "song">
                    宋</#if></span><span
                        class="author-desc"><#if author.desc?length gt 128>${author.desc?substring(0,128)}<span
                        class="more author-desc-more">...</span><#else>${author.desc}</#if>
            </span>
                    <input id="author-switch" name="author-switch" value="0" hidden="">
                    <textarea class="author-desc-abstract"
                              hidden=""><#if author.desc?length gt 128>${author.desc?substring(0,128)}<span
                            class="more author-desc-more">...</span><#else>${author.desc}</#if></textarea>
                    <textarea class="author-desc-full" hidden="">${author.desc}</textarea>
                </div>
                </#if>
            </div>
            <div id="sidebar">
                <div class="poetry-meta">
                <#if (poetry.tags)??>
                    <#list poetry.tags as tag><span class="poetry-tags"><a
                            href="/poetry/search?keyword=tag:${tag}&amp;page=1">${tag}</a></span></#list>
                </#if>
                <#if (poetry.keywords)??>
                    <#list poetry.keywords as keyword><span class="poetry-keywords"><a
                            href="/poetry/search?keyword=${keyword}&amp;page=1">${keyword}</a></span></#list>
                </#if>
                </div>
            </div>
            <div id="nav-bar">
                <div class="pre-poetry pre-item">上一篇</div>
                <div class="next-poetry next-item">下一篇</div>
                <div class="clearfix"></div>
            </div>
        </div>

    </div>
    <div id="footer"></div>
</div>

</body>

</html>