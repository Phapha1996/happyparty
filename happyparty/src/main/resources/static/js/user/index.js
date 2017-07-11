//获取用户信息
getUserInfo();

//用户签到
$("#signIn").click(function() {
  $.ajax({
    url: "/user/signin",
    type: "post",
    dataType: "json",
    success: function(response) {
      if(response.code == 200) {
        mui.toast(response.result);
        getUserInfo();
      } else {
        mui.toast(response.msg);
      }
    }
  });
});

function getUserInfo() {
  $.ajax({
    url: "/user/userMessage",
    type: "post",
    dataType: "json",
    success: function(response) {
      $("#userName").text(response.result.nickname);
      console.log(response);
      $("#integral").text("积分:" + response.result.integral);
    }
  });
}

//退出登录
mui(".mui-card").on("tap","#loginOut",function(){
  var btnArray = ['否', '是'];
  console.log(btnArray);
  mui.confirm('您要退出登录，确认？', '确认', btnArray, function(e) {
    if(e.index == 1) {
      $.ajax({
        url: "/logout",
        type: "post",
        success: function(response) {
          console.log("555");
          location.href = "/login";
        }
      });
    }
  });
});
