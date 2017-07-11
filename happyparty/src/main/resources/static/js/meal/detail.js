
var id = getUrlParam("id");

// addConllect("聚会套餐",id);

var page =1;
var flag  = false;

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

mui.ajax('/meal/list/random/3',{
  type: 'get',
  async: false,
  success: function(res) {
    console.log(res);
     var recommendTpl= template("recommendTpl",res);
     document.getElementById("recommend").innerHTML = recommendTpl;

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
      auto:true,
      callback: function() {
        getDiscuss();
        page++;
        this.endPullupToRefresh(flag);
      }
    }
  }
});

function getData() {
  mui.ajax('/meal/get/'+id, {
    type: 'get',
    async: false,
    success: function(res) {
      console.log(res);
      document.getElementsByClassName("mui-title")[0].innerHTML = res.result.title;
      addCar("场地",id, res.result.admin_id);
      var goodInfoTpl= template("goodInfoTpl",res.result);
      document.getElementById("goodInfo").innerHTML = goodInfoTpl;
      // var cardTpl = template('cardTpl', data);
      // list.innerHTML = list.innerHTML + cardTpl;
    },
  });
}

function getDiscuss(){
  mui.ajax('/discuss/list/'+page+'?productType=聚会套餐&productId='+id+"&pageSize=3",{
    type: 'get',
    async: false,
    success: function(res) {
      if(res.result.length<1){
        flag = true;
      }
      var recommendTpl= template("discussTpl",res.result);
      document.getElementById("discuss").innerHTML =   document.getElementById("discuss").innerHTML + recommendTpl;
    },
  });
}
