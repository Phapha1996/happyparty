var code = '';
$("#getVerify").click(function(){
  var username = $("input[name='username']").val();
  var phoneRegx = /^1\d{10}$/;
  if(!phoneRegx.test(username)){
      mui.toast("请输入正确的手机号");
      return;
  }
  var self = this;
  var con = self.innerHTML;
  if(con=='获取验证码'){
      $.ajax({
        'url':'/user/getSecretKey?phone='+username,
        dataType:'json',
        success:function(res){
          var count = 60;
          code = res.result;
          $("input[name='seed']").val(code);
          var downTime = setInterval(function(){
             self.innerHTML = count+"秒后重新获取";
             count--;
             console.log(count);
             if(count==1){
               self.innerHTML="获取验证码";
               clearInterval(downTime);
             }
          },1000);
        }
      });

  }
  console.log(con);
});

$("#reg").click(function() {
  //检查邮箱
  var username = $("input[name='username']").val();
  var phoneRegx = /^1\d{10}$/;
  var emailRegx = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
  if (emailRegx.test(username) || phoneRegx.test(username)) {
    var res;
    $.ajax({
      'url': '/user/userNameIsNo?userName=' + username,
      'dataType': 'json',
      'async': false,
      'success': function(response) {
        res = response;
      }
    });
    if (res.code === 500) {
      mui.toast(res.msg);
      return;
    }

  } else {
    mui.toast("请输入正确的邮箱");
    return;
  }
  //检查密码
  var password = $("input[name='password']").val();
  if (password == null || password == "") {
    mui.toast("请输入密码");
    return;
  }

  if (password.length < 6) {
    mui.toast("密码长度不能小于6位");
    return;
  }

  var rePassword = $("input[name='repassword']").val();
  if (password != rePassword) {
    mui.toast("两次密码不一致");
    return;
  }

  var name = $("input[name='nickname']").val();
  if (name == null || name == "") {
    mui.toast("请输入姓名");
    return;
  }

  // var phoneRegx = /^1\d{10}$/;
  // var phone= $("input[name='Phones']").val();
  // if(!phoneRegx.test(phone)){
  //   mui.toast("请输入正确的手机号");
  //   return;
  // }

  //注册
  $.ajax({
    url: "/user/verifyAddUser",
    data: $("form").serialize(),
    type: 'get',
    dataType: 'json',
    beforeSend: function() {
      mui("#reg").button('loading');
    },
    complate:function(res){

    },
    success: function(response) {
      console.log(response);
      if(response.code==200){
        mui.alert('恭喜您注册成功，你需要激活账号才能登陆，请到邮箱激活你的账号！', '注册成功', function() {
          location.href = "/login";
        });
      }else{
        mui.toast(response.msg);
        mui("#reg").button('reset');
      }
    },
    error:function(res){
      mui.toast("注册失败，请重试");
      mui("#reg").button('reset');
    }
  });
});
