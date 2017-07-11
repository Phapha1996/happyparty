
var type = getUrlParam("type");
var id = getUrlParam("id");

document.getElementById("add").onclick = function(){
  var content = document.getElementById("content").value;

  if(content==null || content==""){
    mui.toast("内容不能为空");
    return;
  }
  mui.ajax("/discuss/add?productId="+id+"&productType="+type+"&content="+content,{
    type: 'get',
    success: function(res) {
      console.log(res);
      if(res.code==200){
        mui.toast("发表成功");
        setTimeout(function(){
          history.go(-1);
        },1000);
      }
    },
  });
}
