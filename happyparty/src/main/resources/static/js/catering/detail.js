

var sid = getUrlParam("sid");
var flag = false;
var page = 1;

document.getElementById("discussLink").setAttribute("href","/discuss/publish?type=二级服务&id="+sid);

addConllect("二级服务",sid);
mui.ajax('/serve/get/'+sid, {
  type: 'get',
  async: false,
  success: function(res) {
    var infoTpl = template('infoTpl', res);
    var info = document.getElementById('info');
    info.innerHTML = info.innerHTML + infoTpl;
    document.getElementsByClassName("mui-title")[0].innerHTML = res.result.title;
    addCar("二级服务",sid,res.result.admin.aid);
  },
});

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
        list.innerHTML = list.innerHTML = "";
        getData();
        this.endPulldownToRefresh();
      }
    },
    up: {
      auto:true,
      contentrefresh: '正在加载...',
      callback: function() {
        getData();
        page++;
        this.endPullupToRefresh(flag);
      }
    }
  }
});


function getData() {
  mui.ajax('/discuss/list/'+page+'?pageSize=5&productType=二级服务&productId='+sid, {
    type: 'get',
    async: false,
    success: function(res) {
      for(var i=0;i<res.result.length;i++){
        var date = new Date(res.result[i].ctime);
        res.result[i].ctime=date.format('yyyy-MM-dd');
      }
      if(res.result.length<5){
        flag = true;
      }
      var cardTpl = template('cardTpl', res);
      list.innerHTML = list.innerHTML + cardTpl;
      page++;
    },
  });
}
