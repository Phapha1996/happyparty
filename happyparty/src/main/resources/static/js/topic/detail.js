var data;
var list = document.getElementById('cardContainer');
var tid = getUrlParam("tid");
var page = 1;
var flag = false;
mui.ajax('/topic/get/'+tid, {
  type: 'get',
  async: false,
  success: function(res) {
    res.result.cTime = new Date(res.result.cTime).format('yyyy-MM-dd');
    document.getElementsByClassName("mui-title")[0].innerHTML=res.result.title;
    var topicTpl = template('topicTpl', res);
    document.getElementById('topic').innerHTML = topicTpl;
  },
});

document.getElementById("reply").onclick = function(){
  location.href = "/topic/reply?tid="+tid;
};

mui.init({
  pullRefresh: {
    container: '#pullrefresh',
    down: {
      callback: function() {
        list.innerHTML = list.innerHTML = "";
        page = 1;
        flag = false;
        getData();
        this.endPulldownToRefresh();
      }
    },
    up: {
      contentrefresh: '正在加载...',
       contentnomore:'没有更多回复了',
      auto:true,
      callback: function() {
        getData();
        page++;
        this.endPullupToRefresh(flag);
      }
    }
  }
});

function getData() {
  mui.ajax('/topic/reply/list/'+tid+"?pageNum="+page, {
    type: 'get',
    async: false,
    success: function(res) {
      for(var i=0;i<res.result.length;i++){
        var date = new Date(res.result[i].ctime);
        res.result[i].ctime=date.format('yyyy-MM-dd');
      }
      if(res.result.length>0){
        var cardTpl = template('cardTpl', res);
        list.innerHTML = list.innerHTML + cardTpl;
      }else{
        flag = true;
      }

    },
  });
}
