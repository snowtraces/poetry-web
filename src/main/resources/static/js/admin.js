$(function(){

    $(document).on("click","#edit-poetry", edit)
    $(document).on("click","#searchsubmit", listPoetry)
    $(document).on("click",".admin-list-item", loadPoetry)
    $(document).on("click",".btn-confirm", editSubmit)

    $(document).on("keyup", "#keyword", function (e) {
        let keyCode = 'which' in e ? e.which : e.keyCode;
        if (keyCode == "13") { //回车
            listPoetry()
        }
    })
    $(document).on("click","#add-poetry", function(){
        $("#add-poetry-content").show()
        $("#edit-poetry-content").hide()
    })


    function edit(){
        $("#add-poetry-content").hide()
        $("#edit-poetry-content").show()
        listPoetry()
    }

    function listPoetry(){
        $('#poetry-single form').empty()
        $('.toolBar').empty()

        let keyword = $("#keyword").val() || ''
        let currentPage = parseInt(getVal("#current-page") || 0)
        $.ajax({
            url: "/api/admin/search",
            method: "GET",
            dataType: "json",
            data: {keyword: keyword, page: currentPage + 1},
            success: function (data) {
                buildListPage(data)
            }
        })
    }

    function buildListPage(data){
        $list = $('#poetry-list')
        $list.empty().show()
        $('#poetry-single').hide()

        const page = new AdminPoetryPage(data)
        $.each(page.poetryList, function(index, poetry){
            let item =
                `<div class='admin-list-item'>` +
                `<a poetry-id='${poetry.id}' href="javascript:;"><em>${poetry.title}</em> [${poetry.author}] [${poetry.paragraphs}]<a/>` +
                `</div>`
             $list.append(item)
        })
    }

    /**
    *   poetry 回显
    */
    function loadPoetry() {
        let id = $(this).children('a').attr("poetry-id")
         $.ajax({
            url: "/api/admin/edit/" + id,
            method: "GET",
            dataType: "json",
            success: function (data) {
                buildEditPage(data)
            }
        })
    }

    function buildEditPage(data){
        const p = new FullPoetry(data.poetry)
        let id =  `<input hidden name='id' value='${p.id}'/>`
        let title =
            `<div class='edit-title'>` +
            `<input name='title' value='${p.title}' />` +
            `<input name='titleSp' value='${p.titleSp}' />` +
            `</div>`
        let author =
            `<div class='edit-author'>` +
            `<input name='author' value='${p.author}' disable />` +
            `<input name='authorSp' value='${p.author}' disable />` +
            `</div>`

        let par = `${p.paragraphs}`
        let len = parseInt((par.match(/\n/ig).length) || 1)
        let height = len * 1.85 + 2
        let paragraphs =
            `<div class='edit-paragraphs'>` +
            `<textarea name='paragraphs' style='height:${height}em'>${p.paragraphs}</textarea>` +
            `<textarea name='paragraphsSp' style='height:${height}em'>${p.paragraphsSp}</textarea>` +
            `</div>`

        let toolBar =
            '<div class=toolBar>' +
            '<div class="btn btn-confirm">保存修改</div>'
            '</div>'

        $poetry = $('#poetry-single')
        $poetryForm = $('#poetry-single form')
        $toolBar = $('.toolBar')
        $('#poetry-list').hide()
        $poetryForm.empty()
        $toolBar.empty()
        $poetry.show()

        $poetryForm.append(id)
        $poetryForm.append(title)
        $poetryForm.append(author)
        $poetryForm.append(paragraphs)

        $toolBar.append(toolBar)
    }

    function editSubmit (){
        var $form = $('#poetry-single form')
        $.ajax({
            url: "/api/admin/doEdit",
            method: "POST",
            dataType: "json",
            data: $form.serialize(),
            success: function (data) {

            }
        })
        log($form.serialize())
    }

})