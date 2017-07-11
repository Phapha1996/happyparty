var usernameInput = $("input[name='username']");
var passwordInput = $("input[name='password']");

var usernameCookie = getCookie("username");
var passwordCookie = getCookie("password");
var autoLogin = document.getElementById("autoLogin");

if (usernameCookie != null && passwordCookie != null) {
  usernameInput.val(usernameCookie);
  passwordInput.val(passwordCookie);
  autoLogin.setAttribute("class", "mui-switch mui-active");
}

$("#login").click(function() {
  var username = usernameInput.val();
  var emailRegx = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
  var phoneRegx = /^1\d{10}$/;
  if (!emailRegx.test(username)) {
    mui.toast("请输入正确的用户名");
    return;
  }

  var password = passwordInput.val();
  if (password == null || password == "") {
    mui.toast("请输入密码" + password);
    return;
  }
  var loginData = $("form").serialize();

  if(autoLogin.getAttribute("class").indexOf("mui-active")!=-1){
    setCookie("username", username);
    setCookie("password", password);
  }else{
    delCookie("username");
    delCookie("password");
  }

  $.ajax({
    url: "/login",
    data: loginData,
    type: 'post',
    dataType: 'json',
    beforeSend: function() {
      mui("#login").button('loading');
    },
    success: function(response) {
      if (response.code == 500) {
        mui.toast(response.msg);
      } else {

        if (typeof(response.result) == "undefined") {
          location.href = "/user"
        } else {
          location.href = response.result;
        }
      }
      mui("#login").button('reset');
    }
  });
});
