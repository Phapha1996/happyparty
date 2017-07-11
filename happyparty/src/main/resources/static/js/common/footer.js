//$(function() {

  var base = "/";

  var html = '<nav  class="mui-bar mui-bar-footer mui-bar-tab">' +
    '<a class="mui-tab-item"  href="'+base+'index">' +
    '<span class="mui-icon mui-icon-home"></span>' +
    '<span class="mui-tab-label">首页</span>' +
    '</a>' +
    '<a class="mui-tab-item"  href="'+base+'topic">' +
    '<span class="mui-icon mui-icon-chatboxes "></span>' +
    '<span class="mui-tab-label">发现</span>' +
    '</a>' +
    '<a class="mui-tab-item" href="'+base+'">' +
    '<img class="mui-icon" style="width:40px;height:40px;" src="'+base+'img/logo.png"></img>' +
    '</a>' +
    '<a class="mui-tab-item" href="'+base+'order">' +
    '<span class="mui-icon mui-icon-settings"></span>' +
    '<span class="mui-tab-label">清单</span>' +
    '</a>' +
    '<a class="mui-tab-item" href="'+base+'user">' +
    '<span class="mui-icon mui-icon-person"></span>' +
    '<span class="mui-tab-label ">我的</span>' +
    '</a>' +
    '</nav>';

    document.getElementById("footer").innerHTML = html;
    var url = location.href;

    if(url.indexOf("user")!=-1 || url.indexOf("login")!=-1){
      document.getElementsByClassName("mui-tab-item")[4].setAttribute("class","mui-tab-item mui-active");
      //$("nav .mui-tab-item").eq(4).addClass("mui-active");
    }else if(url.indexOf("order")!=-1){
      document.getElementsByClassName("mui-tab-item")[3].setAttribute("class","mui-tab-item mui-active");
    }else if(url.indexOf("topic")!=-1){
      document.getElementsByClassName("mui-tab-item")[1].setAttribute("class","mui-tab-item mui-active");
    }else{
      document.getElementsByClassName("mui-tab-item")[0].setAttribute("class","mui-tab-item mui-active");
    }
//});
