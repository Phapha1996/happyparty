
document.getElementById("add").onclick = function(){
  var title = document.getElementById("title").value;
  var content = document.getElementById("content").value;
  //console.log(content);
  if(title==null || title==""){
    mui.toast("标题不能为空");
    return;
  }

  if(content==null || content==""){
    mui.toast("内容不能为空");
    return;
  }
  mui.ajax("/topic/add?content="+content+"&title="+title,{
    type: 'get',
    success: function(res) {
      console.log(res);
      if(res.code==200){
        mui.toast("发表成功");
        setTimeout(function(){
          location.href = "/topic";
        },1000);
      }
    },
  });
}
