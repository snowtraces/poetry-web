var maxId = 332875;
var randomId = Math.floor(Math.random() * maxId);
function buildPoetryPage(poetry) {
    $("meta[name=keywords]").attr("content", poetry.keyWords);
    $("meta[name=description]").attr("content", poetry.description);
    $("title").html(poetry.title);

    var poetryId = "<input id='poetry-id' hidden value='" + poetry.id + "'/>";
    var poetryHeader = "<div id='poetry-header'><div id='poetry-title'><h2>" + poetry.title +
        "</h2></div> <div id='poetry-author'>" + poetry.author + "</div></div>";
    var poetryContent = "<div id='poetry-content'>";
    $.each(poetry.contentList, function (index, value) {
        poetryContent += "<p class='content-p'>" + value + "</p>";
    })
    poetryContent += "</div>";

    $("#poetry").empty();
    $("#poetry").append("<div class='poetry-item'>" + poetryId + poetryHeader + poetryContent + "</div>");

    $("#nav-bar").html("<div class='pre-poetry pre-item'>上一篇</div><div class='next-poetry next-item'>下一篇</div><div class='clearfix'></div> ")
}

function searchPoetryPage(resultMap) {
    let keyword = resultMap.keyword_tr;
    $("meta[name=keywords]").attr("content", keyword);
    $("meta[name=description]").attr("content", keyword + " - 搜索结果");
    $("title").html(keyword + " - 搜索结果");

    $("#poetry").empty();
    $.each(resultMap.poetryBeanList, function (index, poetry) {
        let content = "";
        $.each(poetry.contentList, function (index, value) {
            content += value;
        })
        let item = "<div class='search-item'><span class='search-item-title'>" + poetry.title.replace(keyword, "<span class='search-keyword'>" + keyword + "</span>") +
            "</span><span class='search-item-author'>[" + poetry.author.replace(keyword, "<span class='search-keyword'>" + keyword + "</span>") +
            "]</span><div class='search-item-content'>" + content.replace(keyword, "<span class='search-keyword'>" + keyword + "</span>") +
            "</div></div>";
        $("#poetry").append(item);
    })
    $("#nav-bar").html("<input id='current-page' hidden value='" +  resultMap.page + "'>" +
        "<input id='current-keyword' hidden value='" +  resultMap.keyword + "'>" +
        "<div class='pre-page pre-item'>上一页</div><div class='next-page next-item'>下一页</div>" +
        "<div class='clearfix'></div> ")
}

function getPoetryById(id) {
    $.ajax({
        url: "/api/poetry/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            buildPoetryPage(data)
        }
    })
}

function getPoetryByKeyword(keyword, page) {
    $.ajax({
        url: "/api/poetry/search",
        method: "GET",
        dataType: "json",
        data: "keyword=" + keyword + "&page=" + page,
        success: function (data) {
            searchPoetryPage(data)
        }
    })
}

// 下一篇
$(document).on("click", ".next-poetry", function () {
    let currentId = parseInt($("#poetry-id").val());
    if (currentId >= maxId) {
        getPoetryById(maxId);
    } else {
        getPoetryById(currentId + 1);
    }
})

// 上一篇
$(document).on("click", ".pre-poetry", function () {
    let currentId = parseInt($("#poetry-id").val());
    console.info(currentId);
    if (currentId <= 1) {
        getPoetryById(1);
    } else {
        getPoetryById(currentId - 1);
    }
})

// 下一页
$(document).on("click", ".next-page", function () {
    let page = parseInt($("#current-page").val());
    let keyword = parseInt($("#current-keyword").val());
    getPoetryByKeyword(keyword, page + 1 );
})


// 上一页
$(document).on("click", ".pre-page", function () {
    let page = parseInt($("#current-page").val());
    let keyword = parseInt($("#current-keyword").val());
    getPoetryByKeyword(keyword, page - 1 );
})


// 搜索
$(document).on("click", "#searchsubmit", function () {
    getPoetryByKeyword($("#keyword").val(), 1);
})

// 初始随机加载
getPoetryById(randomId);