log = console.log.bind(console)

/**
 * 写cookies
 */
function setCookie(name, value) {
    let Days = 30
    let exp = new Date()
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000)
    document.cookie = name + "=" + encodeURI(value) + ";expires=" + exp.toUTCString() + ";path=/"
}

/**
 * 读取cookies
 */
function getCookie(name) {
    let arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)")
    if (arr = document.cookie.match(reg))
        return decodeURI(arr[2])
    else
        return null
}

/**
 * 数字千分位
 */
function toThousands(num) {
    return (num || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,')
}


function abstractWithKeyword(content, keyword, maxLen) {
    maxLen = maxLen || 144
    let re = new RegExp(keyword, "g")
    if (content.length <= maxLen) {
        return content.replace(re, "<em>" + keyword + "</em>")
    } else {
        let index = content.indexOf(keyword)
        if (index === -1) { //没有匹配项
            return content.substr(0, maxLen) + "..."
        } else if (index <= maxLen) { // 初次匹配在maxLen之内
            return content.substr(0, maxLen).replace(re, "<em>" + keyword + "</em>") + "..."
        } else { // // 初次匹配在maxLen之外
            if (content.length > (index + 120)) {
                return "..." + content.substr(index - 24, maxLen).replace(re, "<em>" + keyword + "</em>") + "..."
            } else {
                return "..." + content.substr(index - 24).replace(re, "<em>" + keyword + "</em>")
            }
        }
    }
}

/**
 * 摘要
 */
function abstract(content, maxLen) {
    if (content.length <= maxLen) {
        return content;
    } else {
        return content.substr(0, maxLen) + "..."
    }
}

function getVal(selector) {
    let node = document.querySelector(selector)
    if(node){
        return node.getAttribute("value")
    }
    return null
}

class Poetry{
    constructor(o){
        this.id = o.id
        this.title = o.title
        this.author = o.author
        this.dynasty = o.dynasty
        this.style = o.style
        this.authorId = o.authorId
        this.contentList = o.contentList
        this.description = o.description
        this.keywords = o.keywords
        this.tags = o.tags
    }
}

class FullPoetry extends Poetry{
    constructor(o){
        super(o)
        this.authorSp = o.authorSp
        this.titleSp = o.titleSp
        this.paragraphsSp = o.paragraphsSp
        this.paragraphs = o.paragraphs
    }
}

class Author{
    constructor(o){
        this.id = o.id
        this.name = o.name
        this.desc = o.desc
        this.dynasty = o.dynasty
    }
}
class ShangXi{
    constructor(o){
        this.id = o.id
        this.author = o.author
        this.content = o.content
        this.poetryId = o.poetryId
        this.source = o.source
    }
}

class PoetryPage{
    constructor(o){
        this.keyword = o.keyword
        this.page = o.page
        this.total = o.total
        this.poetryBeanList = o.poetryBeanList
        this.relationTag = o.relationTag
        this.author = o.author
    }
}
class AdminPoetryPage{
    constructor(o){
        this.keyword = o.keyword
        this.page = o.page
        this.total = o.total
        this.poetryList = o.poetryList
    }
}