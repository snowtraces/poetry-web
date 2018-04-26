<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <meta name="google-site-verification" content="r1AIM4LL5RLVLCN5cU80A7Ya7DMcfpWYENJWcH1lzCU"/>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <title></title>
    <link rel="stylesheet" href="/css/style.css" type='text/css' media='all'/>
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/main.js"></script>
</head>
<body>
<div id="wrap">
    <div id="tr-sp">
        <div class="bg-change"></div>
        <span class='tr'>繁</span><span class='sp'>简</span></div>
    <div id="header-wrap" class="wrap">
        <div id="header">
            <#--<div id="title"><h1><a href="/">诗词歌赋</a></h1></div>-->
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
                <div id="user-form">
                    <div id="login-register-switch"><div id="login-switch" class="on-active">登录</div><div id="register-switch">注册</div></div>
                    <form action="/api/user/login" id="login-form" class="on-show">
                        <div class="login-input input-user"><input name="username" placeholder="用户名"/></div>
                        <div class="login-input input-email"><input type="email" name="email" placeholder="邮件地址"/></div>
                        <div class="login-input input-password"><input type="password" name="password" placeholder="密码"/></div>
                        <div class="login-input input-submit"><input  type="button" id="login-submit-btn" name="login-submit-btn" value="登录"/></div>
                    </form>
                    <form action="/api/user/register" id="register-form">
                        <div class="login-input input-user"><input name="username" placeholder="用户名"/></div>
                        <div class="login-input input-email"><input type="email" name="email" placeholder="邮件地址"/></div>
                        <div class="login-input input-password"><input type="password"  ame="password" placeholder="密码"/></div>
                        <div class="login-input input-submit"><input  type="button" id="register-submit-btn" name="login-submit-btn" value="注册"/></div>
                    </form>
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