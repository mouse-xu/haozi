<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/26
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <script src="js/jquery-3.3.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/jquery.cookie.js" type="text/javascript"></script>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        div {
            display: block;
            margin: 0 auto;
        }

        #denglu {
            width: 1130px;
            margin: 0 auto;
        }

        .x_head {
            /*width: 200px;
            height: 98px;*/
            background: url("img/x_shangcheng.png") no-repeat;
        }

        .x_head a {
            cursor: pointer;
            display: block;
            height: 98px;
            text-indent: -9999em;
        }
        /*             头部                                   */

        .x_banner {
            background:url("img/x_001.jpg");
            width: 100%;
            height: 588px;
            position: absolute;
            left: 0;
            top: 98px;
            background-repeat: no-repeat;
            background-position: top center;
        }

        .x_banner a {
            display: block;
            height: 588px;
            text-indent: -9999em;
        }

        .x_form {
            width: 410px;
            min-height: 524px;
            margin-bottom: 0;
            margin-right: 0;
            font-size: 14px;
            z-index: 4;
            /*background-color: #fff;*/
            width: 854px;
            margin: 0 auto 20px;
            position: relative;
            min-height: 620px;
            margin-left: 720px;
            margin-top: 30px;
            position: relative;
        }

        .x_form>div {
            width: 410px;
            height: 524px;
            background-color: white;
        }

        .x_form_head {
            width: 410px;
            height: 83px;
            line-height: 83px;
            text-align: center;
            font-size: 24px;
            color: #e0e0e0;
        }
        .x_form_head a{
            text-decoration: none;
            color: #848484;
        }

        iframe{
            width: 408px;
        }
        <!--.x_war{
                margin-left: 30px;
            }
        .x_war:after{
            content: '';
            display: block;
            clear: both;
        }
        .x_war_d{
            color: #D8D8D8;
        }
        .x_war_d .x_war_d_1{
            float: left;
            /*color: red;*/
        }
        .x_war_d .x_war_d_2{
            float: right;
            margin-right: 32px;
        }

        .x_war_d a{
            text-decoration: none;
            /*color: #BDBDBD;*/
        }
        .x_war_d a:hover{
            color: red;
        }
        .x_war_d_3{
            color: red;
        }
        .x_war_d_4{
            color: #BDBDBD;
        }
        .x_war_d_5{
            color: #BDBDBD;
        }
        .x_tail{

            padding-top: 0;
            position: absolute;
            top: 400px;
            width: 100%;
        }
        #bodyBase{
            width: 1583px;
            margin: 0 auto;
        }
        #base{
            text-align: center;
            color: #848484;
        }
        #base p{
            margin-bottom: 10px;
        }
        #base p span:hover{
            color: #2E2E2E;
            cursor: pointer;
        }
    </style>
</head>

<body>
<div id="bodyBase">
    <!--头部 -->
    <div id="denglu">
        <div class="x_head">
            <a href="k_file.html" target="_blank">小米网</a>
        </div>
    </div>
    <!--中间部分-->
    <div class="x_banner">
        <a href="k_file.html" target="_blank">小米网</a>

    </div>
    <div class="x_form">
        <div>
            <div class="x_form_head">
                <a href="x_accountLogin.html" target="myframe" style="color: red;">账号登陆</a>
                <span>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</span>
                <a href="javascript:void(0)"target="myframe">扫码登陆</a>
            </div>
            <iframe src="x_accountLogin.html" width="100%" height="100%"
                    onload="this.height=myframe.document.body.scrollHeight"
                    width="100%" scrolling="no" frameborder="0" name="myframe">
            </iframe>

        </div>
    </div>
    <script>
        $(function () {
            loginJudge();
        })
        function loginJudge() {
            if($.cookie("cookieState")==1){
                // alert("您的账号已经登陆，请退出登陆再切换其他账号！");
                location.href = "k_file.html";
            }
            if (!$.cookie("cookie_u_id") || $.cookie("cookie_u_id") == "null") {
                var timer = window.setInterval(function () {
                    // console.log("启动了定时器")
                    if("x_register.html"==$.cookie("cookie_ul")){

                        location.href ="x_register.html";
                        $.cookie("cookie_ul",null);

                    }
                    if ($.cookie("cookie_u_id") && $.cookie("cookie_u_id") != "null") {
                        // console.log("cookie_u_id:" + $.cookie("cookie_u_id"));
                        if(document.referrer!=""){
                            location.href =document.referrer;
                        }else{
                            // console.log("跳往首页");
                            location.href ="k_file.html";
                        }

                    }
                }, 500)
            }

        }

    </script>
    <div id="base">

        <p><span>简体</span>&nbsp;&nbsp;&nbsp;|
            <span>繁体</span>&nbsp;&nbsp;&nbsp;|
            <span>English</span>&nbsp;&nbsp;&nbsp;|
            <span>常见问题</span>&nbsp;&nbsp;&nbsp;|
            <span>政策隐私</span>
        </p>
        <p class="ban">小米公司版权所有-京ICP备10046444-<img src="img/x_guohui.png" alt="">&nbsp;京公安网备11010802020134号-京ICP证110507号
        <p/>

    </div>
</div>
</body>

</html>
