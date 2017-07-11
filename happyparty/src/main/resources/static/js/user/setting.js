var name;
var phone;
document.getElementById("updateUserName").addEventListener("tap",function(){
  var btnArray = ['取消', '确定'];
  mui.prompt('请输入你要修改的名字：', '', '修改姓名 ', btnArray, function(e) {
      if (e.index == 1) {
        updateUserInfo("nickName="+e.value);
      }
  });
});

document.getElementById("updateUserPassword").addEventListener("tap",function(){
  var btnArray = ['取消', '确定'];
  mui.prompt('请输入你要修改的密码：', '', '修改密码 ', btnArray, function(e) {
      if (e.index == 1) {
        $.ajax({
          url:"/user/changePasswordEmail?Password="+e.value,
          type:"get",
          dataType:"json",
          success:function(response){
            if(response.code==200){
              mui.toast("修改密码链接已发送，点击链接修改才生效");
            }
          }
       });
      }
  });
});

document.getElementById("changeHead").addEventListener("change",function(event){
      $.ajaxFileUpload({
        url:'/user/changeIcon',
        secureuri: false,
        fileElementId:'changeHead',
        dataType:'json',
        success:function(data, status){
          mui.toast("修改成功");
        },
        error:function(data, status,e){
          mui.toast("修改失败");
          console.log(data);
        }
      });
});

// 	$("#updateUserPhone").click(function(e){
// 		var btnArray = ['取消', '确定'];
// 		mui.prompt('请输入你要修改的手机号：', '', '修改手机号 ', btnArray, function(e) {
// 				if (e.index == 1) {
// 					updateUserInfo("Phones="+e.value+"&name="+name);
// 				}
// 		});
// });
  getUserInfo();

   function getUserInfo(){
       $.ajax({
        url:"/user/userMessage",
        type:"post",
        dataType:"json",
        success:function(response){
          name = response.result.nickname;
          phone = response.result.phone;
          $("#userName").text(name);
          $("#phone").text(phone);
        }
     });
   }

   function updateUserInfo(data){
    $.ajax({
        url:"/user/changeNickName",
        type:"get",
        data:data,
        dataType:"json",
        success:function(response){
            getUserInfo();
            mui.toast("修改成功");
        }
     });
   }
