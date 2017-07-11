var data;
var flag = false;
var page = 1;
var list = document.getElementById('cardContainer');
mui.init({
  pullRefresh: {
    container: '#pullrefresh',
    down: {
      callback: function(){
        list.innerHTML = list.innerHTML = "";
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
  mui.ajax("/serve/list/"+page+"?type=餐饮服务", {
    type: 'get',
    async: false,
    success: function(res) {
      if(res.result.length<0){
        flag = true;
      }
      data = res;
      console.log(res);
      var cardTpl = template('cardTpl',data);
      list.innerHTML = list.innerHTML+cardTpl;
    },
  });
}
