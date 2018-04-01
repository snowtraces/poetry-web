$(function () {

    let maxId = 332875;
    let randomId = Math.floor(Math.random() * maxId);
    let language = 1; // 默认简体

//写cookies
    function setCookie(name, value) {
        let Days = 30;
        let exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + encodeURI(value) + ";expires=" + exp.toGMTString() + ";path=/";
    }

//读取cookies
    function getCookie(name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg))
            return decodeURI(arr[2]);
        else
            return null;
    }

    // 数字千分位
    function toThousands(num) {
        return (num || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
    }

    function getAbstract(contentList, keyword) {
        let maxLength = 144
        let re = new RegExp(keyword, "g");
        let textContent = contentList.join('');
        if (textContent.length <= maxLength) {
            return textContent.replace(re, "<em>" + keyword + "</em>");
        } else {
            let index = textContent.indexOf(keyword);
            if (index == -1) { //没有匹配项
                return textContent.substr(0, maxLength) + "...";
            } else if (index <= maxLength) { // 初次匹配在maxLength之内
                return textContent.substr(0, maxLength).replace(re, "<em>" + keyword + "</em>") + "...";
            } else { // // 初次匹配在maxLength之外
                if (textContent.length > (index + 100)) {
                    return "..." + textContent.substr(index - 20, maxLength).replace(re, "<em>" + keyword + "</em>") + "...";
                } else {
                    return "..." + textContent.substr(index - 20).replace(re, "<em>" + keyword + "</em>");
                }
            }
        }
    }


    function buildPoetryPage(data) {
        let poetry = data.poetry;
        let author = data.author;
        let keywords = poetry.keywords;

        $("meta[name=keywords]").attr("content", keywords);
        $("meta[name=description]").attr("content", poetry.description);
        $("title").html(poetry.title);

        let poetryId = "<input id='poetry-id' hidden value='" + poetry.id + "'/>";
        let poetryHeader = "<div id='poetry-header'><div id='poetry-title'><h2>" + poetry.title +
            "</h2></div> <div id='poetry-author'>" + poetry.author + "</div></div>";
        let poetryContent = "<div id='poetry-content'>";
        let pStyle = poetry.contentList.length > 8 ? "inline-block" : "block";
        $.each(poetry.contentList, function (index, value) {
            poetryContent += "<p class='content-p' style='display: " + pStyle + "'>" + value + "</p>";
        })
        poetryContent += "</div>";

        let authorDetail = "";
        if (author) {
            authorDetail = "<span class='author-name'><a href='/poetry/search?keyword=" + author.name + "&page=1'>" + author.name + "</a></span>" +
                "<span class='author-dynasty'>" +
                ((author.dynasty == "tang") ? "唐" : (author.dynasty == "song") ? "宋" : "") + "</span>" +
                "<span class='author-desc'>" + author.desc + "</span> ";
        }

        let poetryKeywords = "<div class='poetry-keywords'>";
        $.each(keywords, function (index, value) {
            poetryKeywords += "<span><a href='/poetry/search?keyword=" + value + "&page=1'>" + value + "</a></span>";
        })
        poetryKeywords += "</div>"

        $("#poetry").empty();
        $("#poetry").append("<div class='poetry-item poetry-single'>" + poetryId + poetryHeader + poetryContent + "</div>");
        if(author) $("#poetry").append("<div class='author-detail'>" + authorDetail + "</div>");

        $("#sidebar").empty();
        $("#sidebar").append(poetryKeywords);

        $("#nav-bar").html("<div class='pre-poetry pre-item'>上一篇</div><div class='next-poetry next-item'>下一篇</div><div class='clearfix'></div> ")

        initToolbar();
    }

    function searchPoetryPage(resultMap) {
        let keyword = resultMap.keyword;
        let page = resultMap.page;
        let total = resultMap.total;

        $("meta[name=keywords]").attr("content", keyword);
        $("meta[name=description]").attr("content", keyword + " - 搜索结果");
        $("title").html(keyword + " - 搜索结果");
        $("#keyword").val(keyword);
        $(".title-bar").remove();
        $("#content-wrap").prepend("<div class='title-bar'>获得约 " + toThousands(total) + " 条结果（第" + page + "页）</div>")

        $("#poetry").empty();
        $.each(resultMap.poetryBeanList, function (index, poetry) {
            let content = "";
            let re = new RegExp(keyword, "g");
            let item = "<div class='poetry-item search-item'>" +
                "<span class='search-item-title'><a href='/poetry/" + poetry.id + "' title='" + poetry.title + "'>" + poetry.title.replace(re, "<em>" + keyword + "</em>") + "</a></span>" +
                "<span class='search-item-author'>[" + poetry.author.replace(re, "<em>" + keyword + "</em>") + "]</span>" +
                "<div class='search-item-content'>" + getAbstract(poetry.contentList, keyword) + "</div>" +
                "</div>";
            $("#poetry").append(item);
        });

        $("#sidebar").empty();

        $("#nav-bar").html("<input id='current-page' hidden value='" + page + "'>" +
            "<input id='current-keyword' hidden value='" + keyword + "'>" +
            "<div class='pre-page pre-item'>上一页</div><div class='next-page next-item'>下一页</div>" +
            "<div class='clearfix'></div> ")
    }

    function initToolbar() {
        $(".poetry-single").append("<div class='tool-bar'>" +
            "<span class='copy-poetry'><i class='material-icons'>content_copy</i></span></div>")
    }

    function getPoetryById(id, unPushState) {
        $.ajax({
            url: "/api/poetry/" + id,
            method: "GET",
            dataType: "json",
            data: "language=" + language,
            success: function (data) {
                buildPoetryPage(data);
                if (!unPushState) {
                    history.pushState({}, "", "/poetry/" + id);
                }
            }
        })
    }

    function getPoetryByKeyword(keyword, page, unPushState) {
        $.ajax({
            url: "/api/poetry/search",
            method: "GET",
            dataType: "json",
            data: "language=" + language + "&keyword=" + keyword + "&page=" + page,
            success: function (data) {
                searchPoetryPage(data);
                if (!unPushState) {
                    history.pushState({}, "", "/poetry/search?keyword=" + keyword + "&page=" + page);
                }
            }
        })
    }

    function switchLanguage(targetValue) {
        console.log("当前语言模式为：" + (targetValue ? "简体" : "繁体"));
        setCookie("language", targetValue);
        language = targetValue;
        if (targetValue == 1) { // 简
            $("#tr-sp .sp").css({top: ".1em", right: ".1em"});
            $("#tr-sp .tr").css({top: "1.4em", right: "1.4em"});
        } else { // 繁
            $("#tr-sp .tr").css({top: ".1em", right: ".1em"});
            $("#tr-sp .sp").css({top: "1.4em", right: "1.4em"});
        }
    }

    function loadByUrl() {
        let location = document.location + '';
        let re = /\/poetry\/(\d+)$/i;
        let found = location.match(re);
        if (found) {
            getPoetryById(found[1], true);
            return;
        }

        re = /\/poetry\/search\?keyword=([^&]*)&page=(\d*)$/i;
        found = location.match(re);
        if (found) {
            getPoetryByKeyword(found[1], found[2], true);
            return;
        }

        getPoetryById(randomId);
    }

// 下一篇
    $(document).on("click", ".next-poetry", function () {
        let currentId = parseInt($("#poetry-id").val());
        if (currentId >= maxId) {
            getPoetryById(maxId);
        } else {
            getPoetryById(currentId + 1);
        }
    });

// 上一篇
    $(document).on("click", ".pre-poetry", function () {
        let currentId = parseInt($("#poetry-id").val());
        if (currentId <= 1) {
            getPoetryById(1);
        } else {
            getPoetryById(currentId - 1);
        }
    });

// 下一页
    $(document).on("click", ".next-page", function () {
        let page = parseInt($("#current-page").val());
        let keyword = $("#current-keyword").val();
        getPoetryByKeyword(keyword, page + 1);
    });


// 上一页
    $(document).on("click", ".pre-page", function () {
        let page = parseInt($("#current-page").val());
        let keyword = $("#current-keyword").val();
        if (page == 1) {
            getPoetryByKeyword(keyword, page);
        } else {
            getPoetryByKeyword(keyword, page - 1);
        }
    });


// 搜索
    $(document).on("click", "#searchsubmit", function () {
        getPoetryByKeyword($("#keyword").val(), 1);
    });
    $(document).on("keyup", "#keyword", function (e) {
        let keycode = 'which' in e ? e.which : e.keyCode;
        if (keycode == "13") { //回车
            getPoetryByKeyword($(this).val(), 1);
        }
    });

// 繁简切换
    $(document).on("click", "#tr-sp", function () {
        switchLanguage(language ^ 1);
        loadByUrl();
    });

// 复制
    $(document).on("click", ".copy-poetry", function () {
        //初始化
        $("textarea").remove("#targetId");

        var poetryHeader = $("#poetry-header");
        var poetryContent = $("#poetry-content");
        var poetryText = poetryHeader[0].outerText + "\n" + poetryContent[0].outerText.replace(/\n\n/g, "\n");

        //添加 <textarea> DOM节点，将获取的代码写入
        var target = document.createElement("textarea");
        target.style.position = "absolute";
        target.style.left = "-9999px";
        target.style.top = "0";
        target.id = "targetId";
        poetryHeader.append(target);
        target.textContent = poetryText;

        //选中textarea内的代码
        target.focus();
        target.setSelectionRange(0, target.value.length);

        // 复制选中的内容
        document.execCommand("copy");

        //删除添加的节点
        $("textarea").remove("#targetId");

        let thisCopied = $(this);
        thisCopied.empty();
        thisCopied.html("<i class='material-icons'>done</i>");
        setTimeout(function () {
            thisCopied.empty();
            thisCopied.html("<i class='material-icons'>content_copy</i>");
        }, 1000)

    });

    window.addEventListener('popstate', function (event) {
        loadByUrl();
    });

    function initAll() {
        // 初始化语言
        let _language = getCookie("language");
        if (_language == null) {
            _language = 1;
        } else {
            _language = parseInt(_language)
        }
        switchLanguage(_language);

        // 初始加载
        loadByUrl();
    }

    initAll();
});