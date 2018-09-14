//搜索设置参数cookie
function setCookie(){
    var cname= "keyword";
    var cvalue = $("input[name=keyword]").val();
    var Days = 30; //此 cookie 将被保存 30 天
    var exp  = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = cname + "=" + cvalue + ";"+"expires="+exp.toUTCString();
    location.href = "k_search.html";
}
//通用左上角登陆状态切换
$(function () {
    //加载页面时判断是否登陆上账号
    loginJudge();
    //点击退出账号
    $("#loginOut").click(function () {
        $.cookie("cookie_u_id",null);
        $.cookie("cookieState",null);
        loginJudge();
        window.location.reload();
    })
});
function loginJudge() {
    if($.cookie("cookie_u_id")&& $.cookie("cookie_u_id")!="null"){
        if($.cookie("cookie_userName")){
            $("#changeState").show();
            $("#changeState p").html("你好："+$.cookie("cookie_userName"));
            $(".loginBefore").hide();
            $(".loginAfter").show();
        }else{
            $("#changeState p").html("你好："+$.cookie("cookie_phone"));
            $(".loginBefore").hide();
            $(".loginAfter").show();
        }
        return true;
    }else{
        $("#changeState").hide();
        $(".loginBefore").show();
        $(".loginAfter").hide();
        return false;
    }
};
//TV综合页JS*************************************************************************************************
$(function () {
    //头部第1段 登陆状态下 鼠标用户名  显示隐藏元素
    $("#changeState").mouseover(function () {
        $("#changeState ul").show();
    }).mouseout(function () {
        $("#changeState ul").hide();
    });
    //头部第1段 鼠标悬浮购物车  显示隐藏元素
    $(".y_first_1_2>div ").hover(
        function () {
            $(this).find("#signShoppingCar").css({
                "color": "#ff6700",
                "height": "44px",
                "background-color": "white",
                "background-image": "url(img/y_first_1_1_1_1.png)"
            });
            $(this).find("div").show();
        },
        function () {
            if($(".countSign").html()!=0){
                $(this).find("#signShoppingCar").css({
                    "color": "#ffffff",
                    "height": "40px",
                    "background-color": "#FF6700",
                    "background-image": "url(img/y_first_1_1_1_2.png)"
                });
            }else{
                $(this).find("#signShoppingCar").css({
                    "color": "#b0b0b0",
                    "height": "40px",
                    "background-color": "#424242",
                    "background-image": "url(img/y_first_1_1_1.png)"
                });
            }
            $(this).find("div").hide();
        }
    );

    //底部  第1段5个小图标鼠标悬浮切换

    $(".y_third_1_1 li").hover(
        function () {
            var index = $(".y_third_1_1 li").index($(this));
            $(this).find("a").css({
                "background-image": "url(img/y_third_1_1_" + (index + 1) + "_1.png)",
                "color": "#ff6700"
            })
        },
        function () {
            var index = $(".y_third_1_1 li").index($(this));
            $(this).find("a").css({
                "background-image": "url(img/y_third_1_1_" + (index + 1) + ".png)",
                "color": "#616161"
            })
        }
    )
});
//TV_4A_32in.html*************************************************************************************************
//顶部监控定位
$(function () {
    $(window).scroll(function () {
        var sTop = 0;
        sTop = $(this).scrollTop();
        if (sTop >= 205) {
            $(".y1_sec_1_2").slideDown();
            $(".y1_sec_1_2").css({
                "border-bottom": "1px solid #e0e0e0",
                "box-shadow": "0px 5px 5px rgba(0,0,0,0.07)"
            })
        } else {
            $(".y1_sec_1_2").css({
                "border-bottom": "none",
                "box-shadow": "none"
            });
            $(".y1_sec_1_2").slideUp();
        }
    })
});
//.y1_sec_3段左右悬浮切换图片按钮
var timer;
var ii = 1;
$(function () {
    timer = setInterval("changeImg()", 2000);
    $(".y1_sec_3").hover(
        function () {
            clearInterval(timer)
        },
        function () {
            timer = setInterval("changeImg()", 2000);
        }
    );
    $(".y1_sec_3>a:first").click(function () {
        ii = ii - 2;
        changeImg();
    });
    $(".y1_sec_3>a:last").click(function () {
        changeImg();
    });
});

function changeImg() {
    if (ii > 3) {
        ii = 1;
    } else if (ii < 1) {
        ii= 3;
    };
    $("#y1_sec_3_" + ii).show();
    $(".y1_sec_3>div:not(#y1_sec_3_" + ii + ")").hide();
    ii++;
};

//TV_4A_32in_2.html*************************************************************************************************
var timer2;
var i2 = 1;
$(function () {
    timer2 = setInterval("changeImg2()", 2000);
    $(".y2_sec_2_1 .fl>div").hover(
        function () {
            clearInterval(timer2);
        },
        function () {
            timer2 = setInterval("changeImg2()", 2000);
        }
    );
    $(".y2_sec_2_1 .fl p:nth-of-type(1)").click(function () {
        i2 = i2 - 2;
        changeImg2();
    });
    $(".y2_sec_2_1 .fl p:nth-of-type(2)").click(function () {
        changeImg2();
    });
    $("#y2_sec_2_1_ol li").each(function () {
        $(this).click(function () {
            var temp = $("#y2_sec_2_1_ol li").index($(this));
            console.log(temp);
            i2 = temp + 1;
            changeImg2()
        });
    });
});

function changeImg2() {
    if (i2 > 4) {
        i2 = 1;
    } else if (i2 < 1) {
        i2 = 4;
    };
    $("#y2_sec_2_1_ul li").hide();
    $("#y2_sec_2_ul_" + i2).show();
    $("#y2_sec_2_1_ol li").css("background-color", "#ccc");
    $("#y2_sec_2_ol_" + i2).css("background-color", "#a3a3a3");
    i2++;
};

//顶部监控定位
$(function () {
    $(window).scroll(function () {
        var sTop = 0;
        sTop = $(this).scrollTop();
        if (sTop >= 205) {
            $(".y2_sec_1_2").slideDown();
            $(".y2_sec_1_2").css({
                "border-bottom": "1px solid #e0e0e0",
                "box-shadow": "0px 5px 5px rgba(0,0,0,0.07)"
            });
        } else {
            $(".y2_sec_1_2").css({
                "border-bottom": "none",
                "box-shadow": "none"
            });
            $(".y2_sec_1_2").slideUp();
        };
    });
});
//#y2_sec_li_4  #y2_sec_li_5下选中显示前面小勾,以及点击之后总价及y2_sec_2_1高度变化
$(function () {
    priceCount();
    $("#y2_sec_li_4>div").click(function () {
        $(this).find("p").eq(0).find("span").toggleClass("orange");
        $(this).find("p").eq(2).toggleClass("orange_font");
        $(this).toggleClass("orange_border");
        if ($(this).hasClass("orange_border")) {
            $("#y2_sec_li_6_1").css("display", "block");
            flagService1 = true;
        } else {
            $("#y2_sec_li_6_1").css("display", "none");
            flagService1 = false;
        }
        priceCount();
        changeHeight();
    });
    $("#y2_sec_li_5>div").click(function () {
        if ($(this).hasClass("orange_border")) {
            $("#y2_sec_li_5>div p:nth-of-type(1) span").removeClass("orange");
            $("#y2_sec_li_5>div ").removeClass("orange_border");
            $("#y2_sec_li_5>div p:nth-of-type(3)").removeClass("orange_font");
            $("#y2_sec_li_6_3").css("display", "none");
            $("#y2_sec_li_6_2").css("display", "none");
            flagService2 = false;
            flagService3 = false;
            console.log(1);
            priceCount();
            changeHeight();
            return;
        };
        $("#y2_sec_li_5>div p:nth-of-type(1) span").removeClass("orange");
        $("#y2_sec_li_5>div ").removeClass("orange_border");
        $("#y2_sec_li_5>div p:nth-of-type(3)").removeClass("orange_font");
        $(this).find("p").eq(0).find("span").toggleClass("orange");
        $(this).find("p").eq(2).toggleClass("orange_font");
        $(this).toggleClass("orange_border");
        var index = $("#y2_sec_li_5>div").index($(this));
        if (index == 0) {
            if ($(this).hasClass("orange_border")) {
                $("#y2_sec_li_6_3").css("display", "none");
                flagService3 = false;
                $("#y2_sec_li_6_2").css("display", "block");
                flagService2 = true;
            }
            priceCount();
            changeHeight();
        } else if (index == 1) {
            if ($(this).hasClass("orange_border")) {
                $("#y2_sec_li_6_2").css("display", "none");
                flagService2 = false;
                $("#y2_sec_li_6_3").css("display", "block");
                flagService3 = true;
            };
            priceCount();
            changeHeight();
        };
    });
});
//计算商品价格
var priceGood = 0;
var priceService1 = 0;
var priceService2 = 0;
var priceService3 = 0;
var priceTotal = 0;
var flagService1 = false;
var flagService2 = false;
var flagService3 = false;

function priceCount() {
    priceTotal = 0;
    priceGood = ($("#priceGood").text()).split("元")[0];
    if (flagService1) {
        priceService1 = ($("#priceService1").text()).split("元")[0];
        console.log("priceService1=" + priceService1);
    } else {
        priceService1 = 0;
    }
    if (flagService2) {
        priceService2 = ($("#priceService2").text()).split("元")[0];
        console.log("priceService2=" + priceService2);
    } else {
        priceService2 = 0;
    }
    if (flagService3) {
        priceService3 = ($("#priceService3").text()).split("元")[0];
        console.log("priceService3=" + priceService3);
    } else {
        priceService3 = 0;
    }
    priceTotal = (+priceGood) + (+priceService1) + (+priceService2) + (+priceService3);
    //	console.log("计算后：priceTotal=" + priceTotal);
    $("#priceTotal").text("  总计  ：" + priceTotal + "元 ");
}

//动态改变.y2_sec_2_1高度
function changeHeight() {
    var height1 = 1315;
    if (flagService1) {
        height1 += 30;
    }
    if (flagService2) {
        height1 += 30;
    }
    if (flagService3) {
        height1 += 30;
    }
    height1 += "px";
    $(".y2_sec_2_1").css("height", height1);
    console.log(height1);
}

//左浮动块定位变化
$(function () {
    $(window).scroll(function () {
        var sTop = 0;
        sTop = $(this).scrollTop();
        if (sTop >= 205 && sTop < 812) {
            $(".y2_sec_2_1 .fl").removeClass("position2");
            $(".y2_sec_2_1 .fl").addClass("position1");
        }
        if (sTop >= 812) {
            $(".y2_sec_2_1 .fl").removeClass("position1");
            $(".y2_sec_2_1 .fl").addClass("position2");
        };
    });
});
//TV_4A_32in3.html*************************************************************************************************
$(function () {
    var url = window.location.href;
    if(url!="http://localhost:8080/TV_4A_32in_3.html"&&url!="http://localhost:8080/TV_4A_32in_4.html"){
            return;
    }
    $(".y3_sec_2_1,y3_sec_3_1").delegate(".addShoppingCar","click",function(){
        var p_idStr = $(this).find(".p_id").text();
        // console.log(p_idStr)
        addShoppingCar(p_idStr);
    })
    // $(".addShoppingCar").each(function () {
    //     $(this).click(function () {
    //         var p_idStr = $(this).find(".p_id").text();
    //         // console.log(p_idStr)
    //         addShoppingCar(p_idStr);
    //     })
    // })
    //推荐商品部分，悬浮显示加入购物车
    $(".y3_sec_2_1").delegate("li","mouseover",function () {
            $(this).find("a").show();
    });
    $(".y3_sec_2_1").delegate("li","mouseout",function () {
            $(this).find("a").hide();
    })

    //点击加入购物车当前li上方增加p标签标示添加成功
    var $p = $("<p>成功加入购物车</p>");
    $(".y3_sec_2_1,y3_sec_3_1").delegate("a","click",function(){
        var $li = $(this).closest("li");
        $li.prepend($p);
        // console.log(111)
        $li.find("p").slideDown();
        setTimeout(function () {
            $li.find("p").remove();
            location.reload();
            window.scrollTo(15,0);

        }, 1000);
    });
    //提取10件好评度最高的商品丢到推荐
    chooseTen(1);
    chooseTen(2);
});

function chooseTen(flagStr) {
    $.ajax({
        url:"product/chooseTen.do",
        data:{
            flag:flagStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            // console.log(data);
            if(flagStr==1){
                // console.log(11)
                $("#y3_sec_2_1_3").html("");
            }else {
                // console.log(22)
                $("#y3_sec_2_1_4").html("");
            }
            $.each(data,function (i,v) {
                // console.log(v.image);
                var str = "<li>"
                    +"<dl>"
                    +"<dt><img src="+v.image+" width='140px' height='140px'/></dt>"
                    +"<dd>"+v.p_name+"</dd>"
                    +"<dd>"+v.shop_price+"元</dd>"
                    +"<dd>"+v.comments+"人好评"
                    +"<a href='javascript:void(0)' class='addShoppingCar'>加入购物车<span class='p_id'>"+v.p_id+"</span></a>"
                    +"</dd>"
                    +"</dl>"
                    +"</li>";
                if(flagStr==1){
                    // console.log(111)
                    $("#y3_sec_2_1_3").append(str);
                }else {
                    // console.log(222)
                    $("#y3_sec_2_1_4").append(str);
                }
            })

        }
    })
}
//点击移动图片
$(function () {
    $("#y3_sec_2_1_1 span:first").click(function () {
        $("#y3_sec_2_1_3").css("margin-left", "0px");
        $("#y3_sec_2_1_1>span>span").removeClass("span_click");
        $("#y3_sec_2_1_1>span span:first").addClass("span_click");
    });
    $("#y3_sec_2_1_1 span:last").click(function () {
        $("#y3_sec_2_1_3").css("margin-left", "-1240px");
        $("#y3_sec_2_1_1>span>span").removeClass("span_click");
        $("#y3_sec_2_1_1>span span:last").addClass("span_click");
    });

    $("#y3_sec_2_1_2 span:first").click(function () {
        $("#y3_sec_2_1_4").css("margin-left", "0px");
        $("#y3_sec_2_1_2>span>span").removeClass("span_click");
        $("#y3_sec_2_1_2>span span:first").addClass("span_click");
    });
    $("#y3_sec_2_1_2 span:last").click(function () {
        $("#y3_sec_2_1_4").css("margin-left", "-1240px");
        $("#y3_sec_2_1_2>span>span").removeClass("span_click");
        $("#y3_sec_2_1_2>span span:last").addClass("span_click");
    });
});
//TV_4A_32in4.html  logo非主页变化***********************************************************************************************
$(function () {
    var locationUrl = window.location.href;
    var hostUrl = "https://www.mi.com/index.html";
    if (locationUrl != hostUrl) {
        $(".y4_first_1_1>div ul").hover(
            function () {
                $(this).css("margin-left", "0px");
            },
            function () {
                $(this).css("margin-left", "-48px");
            }
        );
    };
});
//购物车结算
$(function () {
    //前面多选框的状态判断
    $("#allCheckbox").click(function () {
        if ($("#allCheckbox").hasClass("addcheckbox")) {
            $(".checkbox").removeClass("addcheckbox");
            $(".good ol>li").hide();
            $(".good").each(function () {
                $(this).find(".p_state").text(0);
                $(this).find(".ser_state").text(0);
            })
        } else {
            $(".checkbox").addClass("addcheckbox");
            $(".good .minSer").show();
            $(".good").each(function () {
                $(this).find(".p_state").text(1);
            })
        };
        allTotalPr();
    });

    //商品服务的预览与选中状态切换
    if($(".sureGood").hasClass("addcheckbox")){
        $(this).parent("ul").next("ol").show();
        allTotalPr();
    };
    $("#goods").delegate(".checkbox", "click", function (){
        if ($(this).hasClass("addcheckbox")) {
            $(this).removeClass("addcheckbox");
            $(this).closest(".good").find(".p_state").text(0);
            if ($("#allCheckbox").hasClass("addcheckbox")) {
                $("#allCheckbox").removeClass("addcheckbox");
            }
            allTotalPr();
            //关闭相邻ol标签中后续需要切换隐藏显示的服务标签
            $(this).closest("ul").next().find("ul").hide();
            $(this).closest("ul").next().find("li:first").hide();
            $(this).closest("ul").next().find(".minSer").hide();
        } else {
            $(this).addClass("addcheckbox");
            $(this).closest(".good").find(".p_state").text(1);
            var flag = true;
            $(".checkbox:not(#allCheckbox)").each(function () {
                if (!$(this).hasClass("addcheckbox")) {
                    flag = false;
                }
            });
            if (flag) {
                $("#allCheckbox").addClass("addcheckbox")
            }
            $(this).closest("ul").next().find(".minSer").show();
            allTotalPr();
        };
    });
    //初始加载计算一次各商品服务的单行总价及所有商品总价
    var array = document.getElementsByClassName("unitPrice");
    for (var i = 0; i < array.length; i++) {
        var $ele = $(array[i]);
        totalPr($ele);
    };
    allTotalPr();
    //点击减号时，相关属性的变动显示
    $("#goods").delegate(".sub", "click", function (){
        if ($(this).next().val() > 1) {
            var val = $(this).next().val() - 1;
            $(this).next().val(val);
            var $ele = $(this).parent().prev();
            totalPr($ele);
            var $ol = $(this).closest("ol");
            // console.log($ol);
            if($ol.length==0){
                // console.log(2+$ol);
                var $byGood = $(this).closest(".good").find("ol ul:visible");
                if($byGood){
                    $byGood.each(function () {
                        var val2 = $(this).find("input").val();
                        if(val2>val){
                            $(this).find("input").val(val);
                            var $ele1 = $(this).find(".unitPrice");
                            totalPr($ele1);
                        }
                    })
                }
            }
            allTotalPr();
            //判断还可以买几件同样商品，并适当显示给客户看见
            var val1 = 5 - val;
            if (val1 >= 1 && val1 <= 3) {
                $(this).parent().find("p").css("display", "block");
                $(this).parent().find("p").text("还可买 " + val1 + " 件")
            } else {
                $(this).parent().find("p").css("display", "none");
            };
        };
    });
    //点击加号时，相关属性的变动显示
    $("#goods").delegate(".add", "click", function (){
        if ($(this).prev().val() < 5) {
            if($(this).hasClass("add2")){
                var countMainGood = $(this).closest(".good").find(".countChoose").val();
                var countByGood = $(this).prev().val();
                if(countByGood>=countMainGood){
                    //超过主商品件数，显示遮蔽罩，给出相应提示
                    $("#cover").show();
                    $("#maxCount").show().addClass("showChoose");
                    return;
                }
            }
            var val = +($(this).prev().val()) + 1;
            $(this).prev().val(val);
            var $ele = $(this).parent().prev();
            totalPr($ele);
            allTotalPr();
            //判断还可以买几件同样商品，并适当显示给客户看见
            var val1 = 5 - val;
            if (val1 >= 1 && val1 <= 3) {
                $(this).parent().find("p").css("display", "block");
                $(this).parent().find("p").text("还可买 " + val1 + " 件")
            } else {
                $(this).parent().find("p").css("display", "none");
            }
        }else {
            //超过5件商品，显示遮蔽罩，给出相应提示
            $("#cover").show();
            $("#maxCount").show().addClass("showChoose");
        }
    });
    //遮蔽罩情况处理
    $("#cover,.close,.butn").click(function (){
            $("#cover").nextAll().removeClass("showChoose").addClass("hideChoose");
        var $serCov = $(".servCov:visible");
        $serCov.removeClass("showChoose").addClass("hideChoose");
            setTimeout(function () {
                $serCov.removeClass("hideChoose");
                $serCov.hide();
                $("#cover").nextAll().removeClass("hideChoose");
                $("#cover").nextAll().hide();
                $("#cover").hide();
            },600);
    });
    //商品删除关闭按钮点击事件
    $("#goods").delegate(".subGood", "click", function (){
        $(this).closest("ul").next().remove();
        $(this).closest(".good").remove();
        allTotalPr();
    });
    //商品服务确认弹窗
    var index = -1;
    var parent = null;
    $("#goods").delegate(".minSer", "click", function (){
    // $(".minSer").click(function (){
         index = $(this).index();
         parent = $(this).closest("ol");
         var parent1 = $(this).closest(".good");//调整增加
        $("#cover").show();
        parent1.find(".servCov").eq(index-1).show().addClass("showChoose");//调整修改
    })
    //商品服务确认按钮点击增加当前服务
    $("#goods").delegate(".servCov .butn", "click", function (){
            parent.find("li").eq(0).show();
            parent.find("ul").eq(index-1).show();
            parent.find("ul").eq(index-1).find(".ser_state").text(1);
            parent.find(".minSer").eq(index-1).hide();
        var $serCov = $(this).closest(".servCov");
        $serCov.removeClass("showChoose").addClass("hideChoose");
        setTimeout(function () {
            $serCov.removeClass("hideChoose");
            $serCov.hide();
            $("#cover").hide();
        },600);
        allTotalPr();
    });
    //关闭按钮关闭弹出服务窗
    $("#goods").delegate(".servCov .close", "click", function (){
        var $serCov = $(this).closest(".servCov");
        $serCov.removeClass("showChoose").addClass("hideChoose");
        setTimeout(function () {
            $serCov.removeClass("hideChoose");
            $("#cover").hide();
        },600);
    });
    //删除已经选择的服务
    $("#goods").delegate(".subSer", "click", function (){
    // $(".subSer").click(function () {
        var $parentUl =  $(this).closest("ul");
        $parentUl.find(".ser_state").text(0);
        parent =$(this).closest("ol");
        $parentUl.hide();
        index = $parentUl.index();
        parent.find(".minSer").eq(index).show();
        var flag = true;
        var length = parent.find(".minSer:hidden").length;
        if(length==0){
            parent.find("li").eq(0).hide();
        }
        allTotalPr();
    });
});
//单行总价与单价数量的联动函数
function totalPr($ele) {
    var unitPrice = parseInt($ele.text());
    // console.log(unitPrice);
    var totalPrice = unitPrice * ($ele.next().find("input").val());
    $ele.nextAll(".totalPrice").text(totalPrice + "元");
};
//总价所有行总价的联动函数及总商品数和已选择商品数关系
function allTotalPr(){
    var totalPrice = 0;
    var allTotalPrice=0;
    var countAllGoods = 0;
    var countChooseGoods = 0;
    $(".good>ul").each(function () {
        countAllGoods++;
        if($(this).find(".sureGood").hasClass("addcheckbox")){
            totalPrice = parseInt($(this).find(".totalPrice").text());
            allTotalPrice +=totalPrice;
            countChooseGoods++;
        };
    });
    $(".good ol ul:visible").each(function () {
        if($(this).closest("ol").prev().find(".sureGood").hasClass("addcheckbox")){
            totalPrice = parseInt($(this).find(".totalPrice").text());
            allTotalPrice +=totalPrice;
            countChooseGoods++;
            countAllGoods++;
        };
    });
    $("#countChooseGoods").text(countChooseGoods);
    $("#countAllGoods").text(countAllGoods);
    $("#allTotalPrice").text(allTotalPrice);
}
//TV_4A_32in_5中增加地址****************************************************************************************************
$(function () {
    //添加地址弹出窗口的input输入框监控
    $("#alertAddressChoose input").focus(function () {
        $(this).closest("p").css("border-color","#ff6700");
        $(this).prev().css("color","#ff6700");
        if( $(this).prev().hasClass("removeSign")){
            $(this).prev().removeClass("removeSign");
        };
       if( !$(this).prev().hasClass("addSign")){
           $(this).prev().addClass("addSign");
           if($(this).attr("name")=="userName"){
               $(this).attr("placeholder","收件人姓名");
           }else if($(this).attr("name")=="phone"){
               $(this).attr("placeholder","11位手机号");
           }else if($(this).attr("name")=="addressDetail"){
               $(this).attr("placeholder","详细地址");
           }else if($(this).attr("name")=="postCode"){
               $(this).attr("placeholder","邮政编码");
           }else if($(this).attr("name")=="addressLabble"){
               $(this).attr("placeholder","地址标签");
           };
       };
    }).blur(function () {
        $(this).closest("p").css("border-color","#b0b0b0");
        $(this).prev().css("color","#b0b0b0");
        if($(this).val()==""&&$(this).prev().hasClass("addSign")){
            $(this).prev().removeClass("addSign");
        }
        if($(this).val()==""&& !$(this).prev().hasClass("removeSign")){
            $(this).prev().addClass("removeSign");
            $(this).removeAttr("placeholder");
        };
    });
    //弹出增加地址总div，之前要初始化
    $("#addAddressBtn").click(function () {
        initialize();
        $("#cover").show();
        $("#alertAddressChoose").show().addClass("showChoose");
        $.cookie("r_id_argument","");
    });
    //弹出地址联动选择div
    $("#showChooseAddress").click(function () {
        alertChooseAddress();
    })
    //选择地址的关闭按钮
    $(".closeChooseAddress").click(function (){
        $("#showChoose").css("display","none");
        return false;
    });
    //点击已选择地区名可重选此级地区名
    $("#province").click(function () {
        $("#province").html("");
        $("#city").html("");
        $("#district").html("");
        showChoose("");
        queryArea();
    });
    $("#city").click(function () {
        $("#city").html("");
        $("#district").html("");
        showChoose("");
        queryArea();
    })
    $("#district").click(function () {
        $("#district").html("");
        showChoose("");
        queryArea();
    });
    //选择地址并显示在最上排相应位置
    $("#showChooseOl").delegate("li", "click", function () {
       var str = $(this).text();
       var flag = showChoose(str);
       if(flag){
           var address = $("#province").text()+" "+$("#city").text()+" "+$("#district").text();
           $("#showChooseAddress1").text(address);
           $("#showChoose").hide();
           return false;
       };
    });
    //添加地址时各控件验证
    $("#alertAddressChoose input").bind("input propertychange",function(){
        receiverVerify();
    });

    //点击保存按钮，保存地址进入数据库
    $("#saveAddress").click(function () {
        var r_id_argument = $.cookie("r_id_argument");
        console.log(r_id_argument);
        if(receiverVerify()){
            console.log("可以修改增加地址！");
            insertAndModified(r_id_argument);
            $("#saveAddress").css("display","none");
            $(".cancel").hide();
            initialize();
            if(window.location.href=="http://localhost:8080/peopleCenter.html"){
                $('#iframepage').contents().get(0).location.reload();
            }else{
                window.location.reload();
            }
        }else{
            console.log("地址信息所填有误，不可以修改增加地址！");
            $("#saveAddress").css("display","inline-block");
            $(".cancel").show();
            return;
        }
        $.cookie("r_id_argument","");
    });
    //点击修改地址按钮，进行修改地址
    $(".y5_sec_1_1").delegate(".modiAddress", "click", function () {
        //先获取当前地址的r_id值
        var $getR_id = $(this).closest("ul");
        var r_id_argument = $getR_id.find(".r_id").text();
        // console.log("r_id_argument值为："+r_id_argument);
        $.cookie("r_id_argument",r_id_argument);
        //将此数据传至后台并获得反回数据填入相应的地址信息选项中
        queryReceiver(r_id_argument);
        //打开修改窗口
        $("#cover").show();
        $("#alertAddressChoose").show().addClass("showChoose");
    })
    //点击删除按钮删除指定地址
    $(".y5_sec_1_1").delegate(".deleAddress", "click", function () {
        //先获取当前地址的r_id值
        var $getR_id = $(this).closest("ul");
        var r_id_argument = $getR_id.find(".r_id").text();
        // console.log("r_id_argument值为："+r_id_argument);
        $.cookie("r_id_argument",r_id_argument);
        //将此数据传至后台并获得反回数据填入相应的地址信息选项中
        deleteReceiver(r_id_argument);
        $.cookie("r_id_argument","");
        window.location.reload();
        queryAllReceiver();
    })
    //选择快递邮寄地址
    $(".y5_sec_1_1").delegate(".addressSaved","click",function () {
        $(".addressSaved").removeClass("addressDefault");
        $(this).addClass("addressDefault");
    })
});

//查询指定u_id编号的地址信息，并输出到前台页面
function queryAllReceiver() {
    var u_idStr = $.cookie("cookie_u_id");
    // console.log(u_idStr);
    if (!u_idStr ||u_idStr == ""||u_idStr == "null") {
        // alert("请先登录账号！");
        location.href ="../x_login.html";
        return;
    }
    $.ajax({
        url:"account/queryAllReceiver.do",
        data:{
            u_id:u_idStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            // if(!data.u_id){
            //     console.log("您还没有设置地址")
            // }

                // console.log(data);
                //清空ul元素，准备放查询到的地区名字
                $("#showChooseOl").html("");
                $.each(data,function (i,v) {
                    var phone = v.receiverPhone.substring(0,3)+"*****"+v.receiverPhone.substring(8);
                    var sul = "<ul class='addressSaved'>"
                                +"<li>"+v.receiverName+"</li>"
                                +"<li>"+phone+"</li>"
                                +"<li>"+v.receiverAddress+"</li>"
                                +"<li class='r_id'>"+v.r_id+"</li>"
                                +"<li class='deleAddress'>删除</li>"
                                +"<li class='modiAddress'>修改</li>"
                                +"</ul>";
                    $(".y5_sec_1_1").prepend($(sul));
                });
            var height = $(document.body).height();
                // console.log("height:"+height)
                $(parent.document.getElementById("iframepage")).height(height);

        },
        error: function() {
            alert("查询指定u_id账户下地址数据异常");
        }
    })
}
//删除指定r_id编号的地址信息，并输出到前台页面
function deleteReceiver(r_id_argument) {
    var  u_idStr =$.cookie("cookie_u_id");
    // console.log(u_idStr);
    if(!u_idStr||u_idStr==""||u_idStr == "null"){
        // alert("请先登录账号！");
        location.href ="../x_login.html";
        return;
    }
    $.ajax({
        url:"account/deleteReceiver.do",
        data:{
            r_id:r_id_argument
        },
        type:"post",
        dataType:"test",
        success:function (data) {
            console.log("删除返回的命令："+data);
            if(data>0){
                console.log("删除成功！")
            }else{
                console.log("删除失败！")
            }
        },

    })
}
//查询指定r_id编号的地址信息，并输出到前台页面
function queryReceiver(r_id_argument) {
    var  u_idStr =$.cookie("cookie_u_id");
    // console.log(u_idStr);
    if(!u_idStr||u_idStr==""||u_idStr == "null"){
        // alert("请先登录账号！");
        location.href ="../x_login.html";
        return;
    }
    $.ajax({
        url:"account/queryReceiver.do",
        data:{
             r_id:r_id_argument
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            if(data == 1){
                alert("指定编号地址不存在！");
            }else{
                // console.log("data返回的数据是："+data);
                $("#receiverName").val(data.receiverName);
                $("#receiverPhone").val(data.receiverPhone);
                var addressArray = (data.receiverAddress).split(/\s+/);
                $("#province").text(addressArray[0]);
                $("#city").text(addressArray[1]);
                $("#district").text(addressArray[2]);
                $("#receiverDetail").val(addressArray[3]);
                if(data.postcode!=""&&data.postcode!=null){
                    $("#postcode").val(data.postcode);
                };
                if(data.receiverAddressNickName!=""&&data.receiverAddressNickName!=null){
                    $("#receiverAddressNickName").val(data.receiverAddressNickName);
                };
                //注以下代码放在此函数内有效，放在调用此函数的上级函数却无效
                //填完信息后刷新下几个input输入框，以达到模拟失去焦点的格式检查
                $("#alertAddressChoose input").each(function () {
                    if($(this).val()!=null&&$(this).val()!=""){
                        // console.log($(this).val());
                        $(this).prev().css("color","#ff6700");
                        $(this).prev().removeClass("removeSign");
                        $(this).prev().addClass("addSign");
                    }
                })
                var address = $("#province").text()+" "+$("#city").text()+" "+$("#district").text();
                $("#showChooseAddress1").text(address);
                var str = "";
                showChoose(str);
            }

        },
        error: function() {
            alert("查询指定r_id序号地址数据异常");
        }
    })
}
//增加修改地址函数
function insertAndModified(r_id_argument) {
    var  u_idStr =$.cookie("cookie_u_id");
    // console.log(u_idStr);
    if(!u_idStr||u_idStr==""||u_idStr == "null"){
        // alert("请先登录账号！");
        location.href ="../x_login.html";
        return;
    }
    var address = $("#province").text()+" "+$("#city").text()+" "+$("#district").text()+" "+$("#receiverDetail").val();
    $.ajax({
        url:"account/insertReceiver.do",
        data:{
            r_id:r_id_argument,
            u_id:u_idStr,
            receiverName:$("#receiverName").val(),
            receiverPhone:$("#receiverPhone").val(),
            receiverAddress:address,
            postcode:$("#postcode").val(),
            receiverAddressNickName:$("#receiverAddressNickName").val(),
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            // console.log("增加地址"+data)
            if(data>0){
                console.log("增加地址成功！");
            }else{
                console.log("增加地址失败！");
            }
        },
        error: function() {
            alert("增加修改地址数据异常");
        }
    })
}
//弹出联动地址选择框
function alertChooseAddress() {
    if($("#province").text()==""){
        $("#signSpan").text("选择省份/自治区");
    }else if($("#city").text()==""){
        $("#signSpan").text("选择城市/地区");
    }else if($("#district").text()==""){
        $("#signSpan").text("选择区县");
    };
    queryArea();
    $("#showChoose").show();
}
//地址验证函数
function receiverVerify() {
    var flagSaveAddress = true;
    var strSign = "" ;
    var reg=/^([\u4e00-\u9fa5]){2,7}$/;
    var str = $("#receiverName").val();
    var reg1=/^[1][3,4,5,7,8][0-9]{9}$/;
    var str1 = $("#receiverPhone").val();
    var str2 = $("#province").text();
    var str3 = $("#city").text();
    var str4 = $("#district").text();
    var str5 = $("#receiverDetail").val();
    var str6 = $("#postcode").val();
    var reg6=/^\d{6}$/;
    if(!(reg.test(str))){
        strSign = "姓名栏必填2~7位汉字";
        flagSaveAddress = false;
    }else if (!(reg1.test(str1))){
        strSign = "请填写13、14、15、17、18开头的11位手机号";
        flagSaveAddress = false;
    }else if (str2==""){
        strSign = "请选择省级名称";
        flagSaveAddress = false;
    }else if (str3==""){
        strSign = "请选择市级名称";
        flagSaveAddress = false;
    }else if (str4==""){
        strSign = "请选择区级名称";
        flagSaveAddress = false;
    }else if (str5==""){
        console.log(str5)
        strSign = "请输入区级以下具体地址名称";
        flagSaveAddress = false;
    }else if (str6!=""){
        if (!(reg6.test(str6))){
            strSign = "请输入6位数字邮编号码，不记得可以为空";
            flagSaveAddress = false;
        }
    }else{
        $("#exceptionSign").html("").hide();
        flagSaveAddress = true;
    }
   if(strSign!=null){
       $("#exceptionSign").show().html(strSign);
       strSign = null;
   }
   if(flagSaveAddress){
        $("#saveAddress").css("display","inline-block");
       $(".cancel").hide();
   }else{
       $("#saveAddress").css("display","none");
       $(".cancel").show();
   }
   return flagSaveAddress;
}
//初始化弹框
function initialize() {
    $("#receiverName").val("");
    $("#receiverPhone").val("");
    $("#province").text("");
    $("#city").text("");
    $("#district").text("");
    $("#receiverDetail").val("");
    $("#postcode").val("");
    $("#signSpan").text("选择省份/自治区");
    $("#showChooseAddress1").text("选择省 / 市 / 区");
    $("#receiverAddressNickName").val("");
    $("#alertAddressChoose input").each(function () {
        if($(this).val()==null||$(this).val()==""){
            // console.log($(this).val());
            $(this).attr("placeholder","");
            $(this).prev().removeClass("addSign");
        }
    })
    $(".span_move").css("color","#b0b0b0");
}
//查询地区
function queryArea() {
    $.ajax({
        url:"account/queryArea.do",
        data:{
            provinceName:$("#province").text(),
            cityName:$("#city").text(),
            districtName:$("#district").text()
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            //清空ul元素，准备放查询到的地区名字
            $("#showChooseOl").html("");
            $.each(data,function (i,v) {
                var sli = "<li>"+v.areaName+"</li>";
                $("#showChooseOl").append($(sli));
            });
        },
        error: function() {
            alert("查询地区数据异常");
        }
    });

};
//三级联动选择其他元素伴随变动
function  showChoose(str) {
    if($("#province").text()==""){
        $("#province").text(str);
        if($("#province").text()==""){
            $("#province").hide();
            $("#city").hide();
            $("#district").hide();
        }else{
            $("#province").show();
        };
        $("#signSpan").text("选择城市/地区");
    }else if($("#city").text()==""){
        $("#city").text(str);
        if($("#city").text()==""){
            $("#city").hide();
            $("#district").hide();
        }else{
            $("#city").show();
        }
        $("#signSpan").text("选择区县");
    }else if($("#district").text()==""){
        $("#district").text(str);
        if($("#district").text()==""){
            $("#district").hide();
        }else{
            $("#district").show();
        };
        $("#signSpan").text("");
        receiverVerify();
        return true;
    }else{
        $("#province").show();
        $("#city").show();
        $("#district").show();
    }
    receiverVerify();
}

//需要登录账号之后才能打开的页面
$(function () {
    var url = window.location.href;
    if(url=="http://localhost:8080/TV_4A_32in_4.html"||url=="http://localhost:8080/TV_4A_32in_5.html"||url=="http://localhost:8080/peopleCenter.html"){
        if(!judgeLoginAndReturn()){
            return;
        }
    }

})

//需要登录账号之后才能打开的页面判断登陆的函数
function judgeLoginAndReturn() {
    var u_idStr = $.cookie("cookie_u_id");
    // console.log(u_idStr);
    if (!u_idStr ||u_idStr == ""||u_idStr == "null") {
        // alert("请先登录账号！");
        location.href ="../x_login.html";
        return false;
    }
    return true;
}
//TV_4A_32in_4.html动态展示购物车部分**********************************************************************************************
$(function () {
    var url = window.location.href;
    if(url=="http://localhost:8080/TV_4A_32in_4.html"){
        console.log(judgeLoginAndReturn())
        if(!judgeLoginAndReturn()){
            return;
        }
        var  u_idStr = $.cookie("cookie_u_id");
        // console.log(u_idStr);
        //获得购物车中所有商品及其伴随服务产品
        getProduct(u_idStr);
        //点击结算按钮后判断所有选中商品及服务的数量及库存
        $("#goToPay").click(function () {
            judgeProduct();
        })
    }
})
function getProduct(u_idStr) {
    // console.log(u_idStr);
    $.ajax({
        url:"product/querySomePro.do",
        data:{
            u_id:u_idStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            console.log(data);
            if(data == 1){
                // console.log("没有取到购物车中产品数据");
                return;
            }
            $("#goods").html("");
            $.each(data,function (i,v) {
                var str = "<div class='good'>"
                    +"<p class='p_id'>"+v.p_id+"</p><p class='p_state'>"+v.state+"</p><p class='car_id'>"+v.car_id+"</p>"
                    +"<ul>"
                    +"<li><i class='checkbox sureGood'></i></li>"
                    +"<li><img src="+v.image+" alt='' width='80px' height='80px'/></li>"
                    +"<li>"
                    +"<a href='javascript:void(0)'>"+v.p_name+"</a>"
                    +"<span></span>"
                    +"</li>"
                    +"<li class='unitPrice'>"+v.shop_price+"元<span></span> </li>"
                    +"<li><a href='javascript:void(0)' class='sub'>-</a><input type='text' name='count' value='"+v.count+"' class='countChoose'/><a href='javascript:void(0)' class='add'>+</a>"
                    +"<p></p>"
                    +"</li>"
                    +"<li class='totalPrice'> </li>"
                    +"<li><span class='subGood'></span></li>"
                    +"</ul>"
                    +"<ol><li class='checkedSer'></li></ol>"
                    +"</div>";
                // var $str = $(str);
                $("#goods").append(str);

            })
            var array = document.getElementsByClassName("unitPrice");
            for (var i = 0; i < array.length; i++) {
                var $ele = $(array[i]);
                totalPr($ele);
                allTotalPr();
            };
            $(".p_id").each(function () {
                var pidStr = $(this).text();
                getSerProduct(pidStr,this);

            })
            $(".subGood").click(function () {
                var car_id = $(this).closest(".good").find(".car_id").text();
                deleteShoppingCar(car_id);
            })
            $(".p_state").each(function () {
                if($(this).text()==1){
                    $(this).closest("good").find(".checkbox").addClass("addcheckbox");
                }
            })
        }
    })
}
function getSerProduct(pidStr,obj) {
    // console.log(2+pidStr);
    $.ajax({
        url: "product/querySomeSerPro.do",
        data: {
            p_id: pidStr
        },
        type: "post",
        dataType: "json",
        success: function (data) {
            if (data == 1) {
                // console.log("没有取到购物车中产品的服务产品数据");
                return;
            }
            $.each(data,function (i,v) {
                var str = "<ul >"
                    +"<p class='ser_id'>"+v.ser_id+"</p><p class='ser_state'>0</p>"
                    + "<li></li>"
                    + "<li><img src=" + v.ser_picture + " alt='' width='60px' height='60px'/></li>"
                    + "<li>"
                    + "<a href='javascript:void(0)'> "+v.ser_name+" </a>"
                    + "<span> "+v.ser_description+" </span>"
                    + "</li>"
                    + "<li class='unitPrice'>"+v.ser_priceNow+"元<span>"+(v.ser_priceOld?(v.ser_priceOld+'元'):'')+"</span> </li>"
                    + "<li><a href='javascript:void(0)' class='sub'>-</a><input type='text' name='count' value='1' class='countChooseSer'/><a href='javascript:void(0)' class='add add2'>+</a>"
                    + "<p></p>"
                    + "</li>"
                    + "<li class='totalPrice'> </li>"
                    + "<li><span class='subSer'></span></li>"
                    + "</ul>";

                var difference = v.ser_priceOld-v.ser_priceNow;
                var $checkedSer = $(obj).closest(".good").find(".checkedSer");
                $checkedSer.append(str);
                var str1 = "<li class='minSer'><i></i>"+v.ser_name+" "+v.ser_priceNow+"元 <span> "+(v.ser_priceOld?('(省'+difference+' 元)'):'')+"</span><a href='javascript:void(0)'>了解会员服务 ></a></li>";
                var $ol = $(obj).closest(".good").find("ol");
                $ol.append(str1);
                var str2 ="<div class='servCov' >"
                          +"<span class='close'></span>"
                          +"<h2>购买服务</h2>"
                          +"<dl>"
                          +"<dt></dt>"
                          +"<dd><a href='javascript:void(0)'><img src="+v.ser_picture+" alt='' width='60px' height='60px'></a></dd>"
                          +"<dd>"
                          +"<span>"+v.ser_name+"</span>"
                          +"<span>"+v.ser_description+"</span>"
                          +"</dd>"
                          +"<dd>"+v.ser_priceNow+"元<span>"+(v.ser_priceOld?('(省'+difference+' 元)'):'')+"</span></dd>"
                          +"</dl>"
                          +"<div class='butn'>确认购买</div>"
                          +"</div>"
                var $good = $(obj).closest(".good");
                $good.append(str2);
            })
            var array = document.getElementsByClassName("unitPrice");
            for (var i = 0; i < array.length; i++) {
                var $ele = $(array[i]);
                totalPr($ele);
                allTotalPr();
            };
        }
    })
}
function judgeProduct() {
    $(".good ").each(function () {
        var p_id ;
        var p_count ;
        var ser_id;
        var ser_count;
        if($(this).find(".p_state").text()==1){
            p_id = $(this).find(".p_id").text();
            p_count = $(this).find(".countChoose").val();
            // console.log("p_id:"+p_id+",p_count:"+p_count);
            changePro(p_id,p_count);
            $(this).find(".ser_state").each(function () {
                if($(this).text()==1) {
                    ser_id = $(this).closest("ul").find(".ser_id").text();
                    ser_count = $(this).closest("ul").find(".countChooseSer").val();
                    // console.log("ser_id:"+ser_id+",ser_count:"+ser_count)
                    changeSer(ser_id,ser_count);
                }
            })
        }
    })
    // return;
    location.href="TV_4A_32in_5.html";
}
function changePro(p_id1,p_count) {
    var u_idStr = $.cookie("cookie_u_id");
    console.log("p_id:"+p_id1+" count:"+p_count);
    $.ajax({
        url:"product/changeInfo.do",
        data:{
            u_id:u_idStr,
            p_id:p_id1,
            count:p_count
        },
        async:false,
        type:"post",
        dataType:"text",
        success:function (data) {
            if(data==1){
                console.log("p_id:"+p_id1+"库存不够")
            }else if(data==2){
                console.log("p_id:"+p_id1+"修改插入失败")
            }
        }
    })
}
function changeSer(ser_id1,ser_count) {
    console.log("ser_id:"+ser_id1+" ser_count:"+ser_count)
    var u_idStr = $.cookie("cookie_u_id");
    $.ajax({
        url:"product/changeInfo.do",
        data:{
            u_id:u_idStr,
            ser_id:ser_id1,
            count:ser_count
        },
        async:false,
        type:"post",
        dataType:"text",
        success:function (data) {
            if(data==2){
                console.log("ser_id:"+ser_id1+"修改插入失败")
            }
        },
        error:function () {
            console.log("异常ser_id:"+ser_id1);
        }
    })
}

//TV_4A_32in_5.html加载页面******************************************************************
$(function (){

    var url = window.location.href;
    if(url=="http://localhost:8080/TV_4A_32in_5.html"){
        if(!judgeLoginAndReturn()){
            // console.log(111111)
            return;
        }else {
            // console.log(22222)
            queryChooseInfo();
            setTimeout(function () {
                location.href = "TV_4A_32in_4.html";
            },100*10*1000)
            $(".y5_sec_2_2 .butn").click(function () {
                readyToPay();
            })
        }

    }
})
function queryChooseInfo() {
    var u_idStr = $.cookie("cookie_u_id");
    // console.log("u_idStr:"+u_idStr);
    $.ajax({
        url:"product/queryChooseInfo.do",
        data:{
            u_id:u_idStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            // console.log(data);
            if(data == 1){
                console.log(data);
            }else {
                $("#table").html("");
                var totalPrice = 0;
                var totalCount = 0;
                $.each(data,function (i,v) {
                      var str = "<tr>"
                                +"<td><img src="+v.image+" alt='' width='30px' height='30px'></td>"
                                +"<td><a href='javascript:void(0)'> "+v.name+" </a></td>"
                                +"<td>"+v.price+"元x"+v.count+"</td>"
                                +"<td></td>"
                                +"<td>"+(v.price*v.count)+"元</td>"
                                +"</tr>";
                    $("#table").append(str);
                    totalCount +=v.count;
                    totalPrice +=v.price*v.count;
                })
                $("#totalCount").html(totalCount+"件")
                $("#totalPrice").html(totalPrice+"元")
                if(totalPrice>99){
                    $("#freight").html("0元")
                }else{
                    $("#freight").html("10元")
                }
                $("#allTotalPrice").html(totalPrice);
            }
        },
        error:function () {
            console.log(111)
        }
    })
}
function readyToPay() {
    var u_idStr = $.cookie("cookie_u_id");
    // console.log("u_idStr:"+u_idStr);
    var r_idStr = $(".addressDefault").find(".r_id").text();
    if(r_idStr==""){
        alert("请选择收货地址！");
    }
    var allTotalPriceStr = $("#allTotalPrice").text();
    $.ajax({
        url:"orders/insertOrders.do",
        data:{
            u_id:u_idStr,
            r_id:r_idStr,
            orderPrice:allTotalPriceStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            // console.log("data"+data);
            if(data == 0 ){
                console.log("插入订单失败");
            }else{
                $.cookie("WIDout_trade_no",data);
                $.cookie("WIDtotal_amount",allTotalPriceStr);
                location.href="toPay.html";
            }
        }

    })

}

//mini购物车显示购物车中的商品**********************************************************
$(function () {
    var url = window.location.href;
    if(url=="http://localhost:8080/TV.html"||
        url=="http://localhost:8080/header.html"||
        url=="http://localhost:8080/k_search.html"||
        url=="http://localhost:8080/TV_4A_32Comment.html"||
        url=="http://localhost:8080/TV_4A_32in.html"||
        url=="http://localhost:8080/TV_4A_32in_2.html"||
        url=="http://localhost:8080/TV_4A_32in_3.html"||
        url=="http://localhost:8080/peopleCenter.html"||
        url=="http://localhost:8080/mishangou.html"||
        url=="http://localhost:8080/pingjia.html"||
        url=="http://localhost:8080/Publish.html"||
        url=="http://localhost:8080/k_file.html"){
        queryShoppingCarInfo();
        $(".goToShoppingCar").click(function () {
            location.href="TV_4A_32in_4.html";
        })
    }

})
function queryShoppingCarInfo() {
    var u_idStr = $.cookie("cookie_u_id");
    if(!u_idStr||u_idStr==""||u_idStr == "null"){
        $(".countSign").html(0);
        var str = " <span>购物车中还没有商品，赶紧选购吧！</span>";
        $("#miniShoppingCar").prepend(str);
        $("#miniShoppingCar p").hide();
        return;
    }
    $.ajax({
        url:"product/queryShoppingCarInfo.do",
        data:{
            u_id:u_idStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            // console.log(data);
            var totalCount = 0;
            var totalPrice = 0;
            if(data =="" ){
                $(".countSign").html(0);
                $("#miniShoppingCar ol").html("");
                var str = " <span>购物车中还没有商品，赶紧选购吧！</span>";
                $("#miniShoppingCar").prepend(str);
                $("#miniShoppingCar p").hide();
            }else{
                $("#miniShoppingCar ol").html("");
                $.each(data,function (i,v) {
                    var str =  "<li>"
                        +"<a href='javascript:void(0)'><img src="+v.image+" height='60px'  width='60px' alt=''></a>"
                        +"<a href='javascript:void(0)'> "+v.name+" </a>"
                        +"<span>"+v.price+"元x"+v.count+"</span>"
                        +"<a href='javascript:void (0)'class='miniDele'>x</a>"
                        +"<i class='car_id'>"+v.car_id+"</i>"
                        +"</li>";
                    totalCount +=v.count;
                    totalPrice +=v.count*v.price;
                    $("#miniShoppingCar ol").append(str);
                    $(".totalCount").html(totalCount);
                    $(".countSign").html(totalCount);
                    $(".totalPrice").html(totalPrice+"元");
                    $(".miniDele").hide();
                })
            }
            $("#miniShoppingCar ol li").each(function () {
                $(this).hover(
                    function () {
                        $(this).find(".miniDele").show();
                    },
                    function () {
                        $(this).find(".miniDele").hide();
                    }
                )
            })
            $("#miniShoppingCar .miniDele").each(function () {
                $(this).click(function () {
                    var car_idStr = $(this).next().text();
                    deleteShoppingCar(car_idStr);
                })
            })
            if($(".countSign").html()!=0){
                $("#signShoppingCar").css({
                    "color": "#ffffff",
                    "height": "40px",
                    "background-color": "#FF6700",
                    "background-image": "url(img/y_first_1_1_1_2.png)"
                });
            }
        }
    })
}
function deleteShoppingCar(car_idStr) {
    $.ajax({
        url:"product/deleteShoppingCar.do",
        data:{
            car_id:car_idStr
        },
        type:"post",
        dataType:"text",
        success:function (data) {
            // console.log("data:"+data);
            if(data>0){
                console.log("删除成功！");
                    queryShoppingCarInfo();
            }else {
                console.log("删除失败！")
            }
        }
    })
}

//点击购物车按钮将指定物品加入购物车*************************************************************
$(function () {
    var url = window.location.href;
    if(url=="http://localhost:8080/k_search.html"||url=="http://localhost:8080/TV_4A_32in_2.html"){
        $(".addShoppingCar").each(function () {
            $(this).click(function () {
                var p_idStr = $(this).find(".p_id").text();
                // console.log(p_idStr)
                addShoppingCar(p_idStr);
            })
        })
    }
})

function addShoppingCar(p_idStr) {
    var u_idStr = $.cookie("cookie_u_id");
    if(!u_idStr||u_idStr==""||u_idStr == "null"){
        return;
    }
    $.ajax({
        url:"product/addShoppingCar.do",
        data:{
            u_id:u_idStr,
            p_id:p_idStr
        },
        type:"post",
        dataType:"text",
        success:function (data) {
            // console.log(data);
            if(data!=1){
                console.log("添加购物车失败！")
            }else {
                // var p_idCookie = $.cookie("cookie_p_id")+"/"+p_idStr;
                // console.log(p_idCookie);
                // $.cookie("cookie_p_id",p_idCookie);
                console.log("添加成功！")
                var url = window.location.href;
                if(url=="http://localhost:8080/k_search.html"){
                    location.reload();
                }

            }
        }
    })
}

//订单页面订单展示****************************************************************
function showOrdersInfo(orderStateStr) {
    console.log(orderStateStr);
    var u_idStr = $.cookie("cookie_u_id");
    if(!u_idStr||u_idStr==""||u_idStr == "null"){
        location.href ="../x_login.html";
        return;
    };
    $("#resultSign").hide();
    $.ajax({
        url:"orders/showOrdersInfo.do",
        data:{
            u_id:u_idStr,
            orderState:orderStateStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            console.log(data);
            $("#x_allOrder1").html("");
            if(data!=0){
                $.each(data,function (i,v) {
                    var stateStr = "";
                    var signStr = "";
                    if(v.orderState==0){
                        stateStr = "待支付";
                        signStr = "立即付款";
                    }else if(v.orderState==1){
                        stateStr = "待收货"
                        signStr = "确认收货"
                    }else if(v.orderState==2){
                        stateStr = "已收货"
                    }else if(v.orderState==3){
                        stateStr = "已关闭"
                    }
                    var str = "<ul class='x_order_list'>"
                                +"<li class='x_ord_item'>"
                                +"<div class='x_ord_detail'>"
                                +"<div class='x_ord_sum'>"
                                +"<div class='x_ord_status'>"+stateStr+"</div></div>"
                                +"<table class='x_ord_tetail'>"
                                +"<thead><tr><th class='x_col_main'>"
                                +"<p class=' x_info'>"+v.orderCreatTime
                                +"<span class='sep'>|</span><i class='receiverName'></i> "
                                +"<span class='sep'>|</span> 订单号："
                                +"<a href='javascript:void(0)' class='o_id'>"+v.o_id+"</a>"
                                +"<span class='sep'>|</span> 在线支付</p></th>"
                                +"<th class='col_sub'><p class='x_price'>订单金额："
                                +"<span class='num'>"+v.orderPrice+"</span> 元</p></th></tr></thead><tbody>"
                                +"<tr><td class='ord_items'><ul class='goods_list'></ul></td>"
                                +"<td class='ord_actions'>"
                                +"<a href='javascript:void(0)' class='btn btn_small payNow'>"+signStr+"</a>"
                                +"<a href='orderDetail.html' target='_blank' class='btn btn_small'>订单详情</a>"
                                +"</td></tr></tbody></table></div></li></ul>";
                    $("#x_allOrder1").append(str);
                    $(".payNow").hide();
                    $(".x_ord_status").each(function () {

                        if($(this).text()=="待支付"||$(this).text()=="待收货"){
                            // console.log($(this).text())
                           var $payNow = $(this).closest(".x_order_list").find(".payNow");
                           // console.log($payNow)
                            $payNow.show();
                            $payNow.css({"color":"white","border":"none","display":"block"});
                            $payNow.hover(
                                function () {
                                    $(this).css("background-color","#f25807")
                                },
                                function () {
                                    $(this).css("background-color","#FF6700")
                                }
                            );
                        }
                    })
                    // console.log("orderState:"+v.orderState);
                    // if(v.orderState!=0){
                    //     $(".payNow").hide();
                    // }else{
                    //     // console.log("payNow:"+$(".payNow"));
                    // }
                    $(".x_order_list").each(function () {
                        if($(this).find(".o_id").text() == v.o_id){
                            // console.log(this)
                            // console.log(v.o_id)
                            $(this).hide();
                            showorderdetails(this,v.o_id);
                        }
                    })
                })

            }else{
                $("#resultSign").show();
            }
            if($("#x_allOrder1 ul").length==0){
                // console.log($("#x_allOrder1 ul:visible").length)
                $("#resultSign").show();
            }
            var height = $(document.body).height();
            // console.log("height:"+height)
            $(parent.document.getElementById("iframepage")).height(height);

        }
    })
}
function showorderdetails(object,o_idStr) {
    // console.log(object);
    // console.log(o_idStr)
    $.ajax({
        url:"orders/showOrderDetails.do",
        data:{
            o_id:o_idStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            // console.log("o_idStr:"+o_idStr+" "+data);
            if(data!=""){
                $(object).find(".goods_list").html("");
                $.each(data,function (i,v) {
                    $(object).find(".receiverName").text(v.receiverName)
                    // console.log(v);
                    var str =   "<li>"
                                +"<div class='x_figure'>"
                                +"<a href='javascript:void(0)'><img src="+v.image+" width='60px' height='60px'/></a>"
                                +"</div>"
                                +"<p class='name'>"
                                +"<a href='javascript:void(0)'>"+v.proName+"</a>"
                                +"</p>"
                                +"<p class='price'>"+v.proPrice+"元*"+v.count+"</p>"
                                +"</li>";
                    $(object).find(".goods_list").append(str)
                    $(object).show();
                })
            }else{
                $(object).remove();
                if($("#x_allOrder1 ul").length==0){
                    // console.log($("#x_allOrder1 ul:visible").length)
                    $("#resultSign").show();
                }
            }
            var height = $(document.body).height();
            // console.log("height:"+height)
            $(parent.document.getElementById("iframepage")).height(height);
        }

    })
}
//订单搜索功能************************************************************
function queryOrders(argument) {
    var u_idStr = $.cookie("cookie_u_id");
    if(!u_idStr||u_idStr==""||u_idStr == "null"){
       alert("请先登录您的账号！");
        return;
    };
    $.ajax({
        url:"orders/queryOrdersInfo.do",
        data:{
            "u_id":u_idStr,
            "argument":argument
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            // console.log(data)
            $("#x_allOrder1").html("");
            $("#resultSign").hide();
            if(data!=0){
                $.each(data,function (i,v) {
                    var stateStr = "";
                    if(v.orderState==0){
                        stateStr = "待支付"
                    }else if(v.orderState==1){
                        stateStr = "待收货"
                    }else if(v.orderState==2){
                        stateStr = "已收货"
                    }else if(v.orderState==3){
                        stateStr = "已关闭"
                    }
                    var str = "<ul class='x_order_list'>"
                        +"<li class='x_ord_item'>"
                        +"<div class='x_ord_detail'>"
                        +"<div class='x_ord_sum'>"
                        +"<div class='x_ord_status'>"+stateStr+"</div></div>"
                        +"<table class='x_ord_tetail'>"
                        +"<thead><tr><th class='x_col_main'>"
                        +"<p class=' x_info'>"+v.orderCreatTime
                        +"<span class='sep'>|</span><i class='receiverName'></i> "
                        +"<span class='sep'>|</span> 订单号："
                        +"<a href='javascript:void(0)' class='o_id'>"+v.o_id+"</a>"
                        +"<span class='sep'>|</span> 在线支付</p></th>"
                        +"<th class='col_sub'><p class='x_price'>订单金额："
                        +"<span class='num'>"+v.orderPrice+"</span> 元</p></th></tr></thead><tbody>"
                        +"<tr><td class='ord_items'><ul class='goods_list'></ul></td>"
                        +"<td class='ord_actions'>"
                        +"<a href='orderDetail.html' target='_blank' class='btn btn_small'>订单详情</a>"
                        +"</td></tr></tbody></table></div></li></ul>";
                    $("#x_allOrder1").append(str);
                    $(".x_order_list").each(function () {
                        if($(this).find(".o_id").text() == v.o_id){
                            showorderdetails(this,v.o_id);
                        }
                    })
                })

            }else{
                if($("#x_allOrder1 ul").length==0){
                    // console.log($("#x_allOrder1 ul:visible").length)
                    $("#resultSign").show();
                }
            }
            var height = $(document.body).height();
            // console.log("height:"+height)
            $(parent.document.getElementById("iframepage")).height(height);

        }
    })
}
//跳转订单详情页面*********************************************************
function showorderdetail(o_idStr,ele) {
    $.ajax({
        url:"orders/showOrderDetails.do",
        data:{
            o_id:o_idStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            // console.log(data);
            $.each(data,function (i,v) {
                if(i==0){
                    $(ele).find(".o_id").text(v.o_id);
                    var stateStr = "";
                    if(v.orderState==0){
                        stateStr = "待支付"
                    }else if(v.orderState==1){
                        stateStr = "待收货"
                    }else if(v.orderState==2){
                        stateStr = "已收货"
                    }else if(v.orderState==3){
                        stateStr = "已关闭"
                    };
                    $(ele).find(".m_status").text(stateStr);
                    $(ele).find(".orderCreatTime").text(v.orderCreatTime);
                    $(ele).find(".orderPayTime").each(function () {
                        if(v.orderPayTime){
                            $(this).text(v.orderPayTime);
                        }else{
                            $(this).text("")
                        }

                    });
                    if(v.orderFinishTime){
                        $(ele).find(".orderFinishTime").text(v.orderFinishTime);
                    }else{
                        $(ele).find(".orderFinishTime").text("")
                    }
                    $(ele).find("#receiverName").text(v.receiverName);
                    $(ele).find("#receiverAddress").text(v.receiverAddress);
                    var phone = v.receiverPhone.substring(0,3)+"*****"+v.receiverPhone.substring(9)
                    $(ele).find("#receiverPhone").text(phone);
                    $(ele).find(".orderPrice").each(function () {
                        $(this).text(v.orderPrice);
                    })
                }
                var str ="<tr>"
                         +"<td class='m_thumb'>"
                         +"<div class='m_figure'>"
                         +"<a  href='javascript: void(0);' >"
                         +"<img src="+v.image+" width='60px' height='60px'>"
                         +"</a>"
                         +"</div>"
                         +"</td>"
                         +"<td  class='m_name'>"
                         +"<p>"
                         +"<a target='_blank' href='javascript: void(0);'>"+v.proName+"</a>"
                         +"</p>"
                         +"</td>"
                         +"<td>"
                         +"<p class='price'>"+v.proPrice+"元 × "+v.count+"</p>"
                         +"</td>"
                         +"</tr>";
                $(".m_tupi tbody").append(str);
                var str1 = "<p id='cancel'>取消订单</p><p id='payNow'>立即付款</p>";
                if(i==0&&v.orderState==0){
                    // console.log(111)
                    $(".m_status").append(str1);
                }
            })

        }
    })
}

//个人中心主页展示
function showCenter() {
    var u_idStr = $.cookie("cookie_u_id");
    if(!u_idStr||u_idStr==""||u_idStr == "null"){
        return;
    };
    $.ajax({
        url:"account/showCenter.do",
        data:{
            u_id:u_idStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            console.log(data);
            // console.log(data.userName)
            console.log(data.userimg);
            if(data.userimg){

                $("#userImg").attr("src",data.userimg);
            }
            if(data.userName){
                $("#u_id").text(data.userName);
            }else {
                $("#u_id").text(u_idStr);
            }
            var phone = data.userPhone.substring(0,3)+"*****"+data.userPhone.substring(9);
            $(".tel").text(phone);
            if(data.email){
                var email = data.email.substring(0,3)+"*****.com";
                $(".email").text(email);
            }else {
                $(".email").text("");
            }
            $(".countWaitPay").text(data.countWaitPay);
            $(".countWaitReceive").text(data.countWaitReceive);
            $(".countNoComment").text(data.countNoComment);
            $(".lovePro").text(data.countCollection);
        }
    })
}
//支付功能
function toPay(i) {
    var WIDout_trade_noStr = $.cookie("WIDout_trade_no");
    var WIDtotal_amountStr = $.cookie("WIDtotal_amount");
    // console.log(WIDout_trade_noStr);
    // console.log(WIDtotal_amountStr);
    if(i==1){
        location.href="alipay.trade.page.pay.jsp?WIDout_trade_no="+WIDout_trade_noStr+"&WIDtotal_amount="+WIDtotal_amountStr+"&WIDsubject="+WIDout_trade_noStr+"&WIDbody=";
    }else {
        window.parent.location.href="alipay.trade.page.pay.jsp?WIDout_trade_no="+WIDout_trade_noStr+"&WIDtotal_amount="+WIDtotal_amountStr+"&WIDsubject="+WIDout_trade_noStr+"&WIDbody=";
    }
}
//取消订单
function cancelOrder(o_idStr) {
    console.log(o_idStr);
    if(!judgeLoginAndReturn()){
        return;
    }
    $.ajax({
        url:"orders/cancelOrder.do",
        data:{
            o_id:o_idStr
        },
        type:"post",
        dataType:"text",
        success:function (data) {
            console.log(data)
            if(data==1){
                console.log("取消订单成功！")
                window.location.reload();
            }else if(data==0){
                console.log("取消订单失败！")
            }else{
                console.log("出现了难以预料的第三种情况！")
            }

        }
    })
}
//确认收货
function sureGot(o_idStr){
    console.log(o_idStr);
    $.ajax({
        url:"orders/sureGot.do",
        data:{
            o_id:o_idStr
        },
        type:"post",
        dataType:"text",
        success:function (data) {
            console.log(data)
            if(data==1){
                console.log("确认收货修改成功！")
                location.reload();
            }
        }
    })
}
//个人信息页面展示
function showUserInfo() {
    var u_idStr = $.cookie("cookie_u_id");
    if(!u_idStr||u_idStr==""||u_idStr == "null"){
        return;
    };
    $.ajax({
        url:"account/showUserInfo.do",
        data:{
            u_id:u_idStr
        },
        type:"post",
        dataType:"json",
        success:function (data) {
            // console.log(data);
            if(data==0){
                console.log("没有查到用户相关信息")
            }else{
                $("#userimg").attr("src",data.userimg);
                $(".x_name").text(data.userName);
                $(".x_num").text(data.u_id);
            }

        }
    })
}



