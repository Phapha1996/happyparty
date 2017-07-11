var data;
var list = document.getElementById('cardContainer');
var flag = true;
var id = getUrlParam("id");

mui.init({
  pullRefresh: {
    container: '#pullrefresh',
    down: {
      callback: function() {
        list.innerHTML = list.innerHTML = "";
        getData();
        this.endPulldownToRefresh();
      }
    },
    up: {
      contentrefresh: '正在加载...',
      auto: true,
      callback: function() {
        if(flag) {
          getData();
        }
        flag = false;
        console.log(flag);
        this.endPullupToRefresh();
      }
    }
  }
});

function getData() {
  mui.ajax('/decoration/getIdDecoration?did='+id, {
    type: 'get',
    async: false,
    success: function(res) {
      document.getElementsByClassName("mui-title")[0].innerHTML = res.result.title;
	  document.getElementById("headImg").setAttribute("src",res.result.imgs[0].imgUrl);
      var cardTpl = template('cardTpl', res.result);
      list.innerHTML = list.innerHTML + cardTpl;
      document.getElementById("goodDetail").setAttribute("href",'/siteLayout/good_detail?id='+id+'&aid='+res.result.admin_id);
    },
  });
}
