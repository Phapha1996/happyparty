
var tid = getUrlParam("tid");

document.getElementById("add").onclick = function(){
  var content = document.getElementById("content").value;
  if(content==null || content==""){
    mui.toast("回复内容不能为空");
    return;
  }
  mui.ajax("/topic/reply/add?content="+content+"&topic.tid="+tid,{
    type: 'get',
    success: function(res) {
      console.log(res);
      if(res.code==200){
        mui.toast("回复成功");
        setTimeout(function(){
          location.href = "/topic/detail?tid="+tid;
        },1000);
      }
    },
  })
}
