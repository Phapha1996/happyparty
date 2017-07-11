var data;
var list = document.getElementById('cardContainer');
var page = 1;
var flag = false;
var type =  getUrlParam("type");
document.querySelector(".mui-title").innerHTML = type;
var cardData = {};
mui.init({
  pullRefresh: {
    container: '#pullrefresh',
    down: {
      callback: function() {
        list.innerHTML = list.innerHTML = "";
        page = 1;
        getData();
        this.endPulldownToRefresh();
      }
    },
    up: {
      contentrefresh: '正在加载...',
      callback: function() {
        getData();
        this.endPullupToRefresh(flag);
      }
    }
  }
});
getData();
function getData(){
  mui.ajax('/meal/list/'+page+'?mealType='+type+'&pageSize=3', {
    type: 'get',
    async: false,
    error:function(){
        list.innerHTML = "";
        mui.toast("没有记录");
    },
    success: function(res) {
      if(res.result.length<3){
        flag = true;
      }
      cardData.list = new Array();
      for (var i = 0; i < res.result.length; i++) {
        var temp = {};
        temp.id = res.result[i].smid;
        temp.title = res.result[i].title;
        temp.price = res.result[i].price;

        temp.img = res.result[i].imgs.length==0?'img/default.png':res.result[i].imgs[0].imgUrl;
        temp.label = res.result[i].tags.split("|");
        cardData.list.push(temp);
      }
      var cardTpl = template('cardTpl', cardData);
      list.innerHTML = list.innerHTML + cardTpl;
      page++;
    },
  });


}
