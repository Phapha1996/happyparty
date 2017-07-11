
var id = getUrlParam("id");

addConllect("site",id);

getData();
$(".btn-more").click(function() {
  var self = $(this);
  var element = self.parent().prev();
  if (self.text() == "更多") {
    element.css("height", "auto").css("max-height", "none");
    self.text("收起");
  } else {
    element.css("max-height", element.attr("data-height"));
    self.text("更多");
  }
});

mui.ajax('/site/getRandomSite?number=4',{
  type: 'get',
  async: false,
  success: function(res) {
    for(var i=0;i<res.result.length;i++){
      var date = new Date(res.result[i].ctime);
      res.result[i].ctime=date.format('yyyy-MM-dd');
    }
     var recommendTpl= template("recommendTpl",res);
     document.getElementById("recommend").innerHTML = recommendTpl;
  },
});

mui.ajax('/discuss/list/1?productType=场地&productId='+id+"&pageSize=3",{
  type: 'get',
  async: false,
  success: function(res) {
    var recommendTpl= template("discussTpl",res.result);
    document.getElementById("discuss").innerHTML = recommendTpl;
    document.getElementById("moreDiscuss").addEventListener("tap",function(){
      location.href = '/site/discuss?id='+id;
    });
  },
});



var data;
var list = document.getElementById('cardContainer');

var slider = mui("#slider");
slider.slider({
  interval: 500,
});
mui.init({
  pullRefresh: {
    container: '#pullrefresh',
    down: {
      callback: function() {
        this.endPulldownToRefresh();
      }
    },
    up: {
      contentrefresh: '正在加载...',
      callback: function() {

        this.endPullupToRefresh();
      }
    }
  }
});

function getData() {
  mui.ajax('/site/getSite?siid='+id, {
    type: 'get',
    async: false,
    success: function(res) {
      console.log(res);
      document.getElementsByClassName("mui-title")[0].innerHTML = res.result.title;
      addCar("场地",id, res.result.admin_id);
      var goodInfoTpl= template("goodInfoTpl",res.result);
      document.getElementById("goodInfo").innerHTML = goodInfoTpl;
      var date = new Date();
      var calendar = new PriceCalenar(date.getFullYear(), date.getMonth()+1,[res.result.price,res.result.weekPrice]);
      calendar.show();
      // var cardTpl = template('cardTpl', data);
      // list.innerHTML = list.innerHTML + cardTpl;
    },
  });
}
