var data;
var page = 1;
var flag = false;
var list = document.getElementById('cardContainer');
mui.init({
  pullRefresh: {
    container: '#pullrefresh',
    down: {
      callback: function(){
        list.innerHTML = list.innerHTML = "";
        page = 1;
        flag = false;
        getData();
        this.endPulldownToRefresh();
      }
    },
    up: {
      contentrefresh: '正在加载...',
      auto:true,
      callback:function(){
          getData();
          page++;

          this.endPullupToRefresh(flag);
      }
    }
  }
});
function getData(){
  mui.ajax('/topic/list/'+page, {
    type: 'get',
    async: false,
    success: function(res) {
      data = res;
      if(data.result.length>0){
          for(var i=0;i<data.result.length;i++){
            var date = new Date(data.result[i].cTime);
            data.result[i].cTime=date.format('yyyy-MM-dd');
          }
          var cardTpl = template('cardTpl',data);
          list.innerHTML = list.innerHTML+cardTpl;
      }else{
        flag = true;
      }

    },
  });
}
