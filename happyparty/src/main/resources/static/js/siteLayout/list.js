var data;
var list = document.getElementById('cardContainer');
var id = getUrlParam("id");
var title = getUrlParam("title");
var img = getUrlParam("img");

var page  = 1;
var flag = false;

document.getElementsByClassName("mui-title")[0].innerHTML = title;
document.getElementById("themeImg").setAttribute("src",img);

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
  mui.ajax('/decoration/getThemeDecortion?thid='+id+'&page='+page+'&size=10', {
    type: 'get',
    async: false,
    success: function(res) {
      console.log(res);
      if(res.result.length>0){
        var cardTpl = template('cardTpl', res);
        list.innerHTML = list.innerHTML + cardTpl;
      }else{
        flag = true;
      }

    },
  });
}
