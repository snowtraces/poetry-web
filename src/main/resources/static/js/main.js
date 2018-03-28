var maxId = 332875;
var randomId = Math.floor(Math.random() * maxId);
function buildPoetryPage(poetry) {
  $("meta[name=keywords]").attr("content", poetry.keyWords);
  $("meta[name=description]").attr("content", poetry.description);
  $("title").html(poetry.title);
  $("#poetry-id").html(poetry.id);
  $("#poetry-title h1").html(poetry.title);
  $("#poetry-author").html(poetry.author);
  $.each(poetry.contentList, function (index, value) {
    $("#poetry-content").append("<p class=\"content-p\">" + value + "</p>");
  })
}

function getPoetryById(id) {
  $.ajax({
    url: "/api/poetry/" + id,
    method: "GET",
    dataType: "json",
    success: function (data) {
      console.log(data)
      buildPoetryPage(data)

    }
  })
}

// 下一篇
$(document).on("click",".next-poetry",function () {
  let currentId = $("#poetry-id").value;
  if(currentId >= maxId){
    getPoetryById(maxId);
  } else {
    getPoetryById(currentId + 1);
  }
})

// 上一篇
$(document).on("click",".pre-poetry",function () {
  let currentId = $("#poetry-id").value;
  console.info(currentId);
  if(currentId <= 1){
    getPoetryById(1);
  } else {
    getPoetryById(currentId - 1);
  }
})


// 初始随机加载
getPoetryById(randomId);