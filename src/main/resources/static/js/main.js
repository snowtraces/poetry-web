function getPoetryById(id) {
  $.ajax({
    url: "/api/poetry/" + id,
    method: "GET",
    dataType: "json",
    success: function (data) {
      console.log(data)
    }
  })
}
