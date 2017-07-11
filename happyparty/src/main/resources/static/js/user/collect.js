mui.init();
(function($) {
  //阻尼系数
  var deceleration = mui.os.ios?0.003:0.0009;
  $('.mui-scroll-wrapper').scroll({
    bounce: false,
    indicators: true, //是否显示滚动条
    deceleration:deceleration
  });
  $.ready(function() {
    //循环初始化所有下拉刷新，上拉加载。
    $.each(document.querySelectorAll('.mui-slider-group .mui-scroll'), function(index, pullRefreshEl) {
      $(pullRefreshEl).pullToRefresh({
        down: {
          height:200,
          callback: function() {

          }
        },
        up: {
          auto: true,
          callback: function() {
            var self = this;
            var ul = self.element.querySelector('.mui-table-view');
            var ulId = ul.getAttribute("id");
            getData(ulId,index);
            page[index]++;
            //ul.appendChild(createFragment(ul, index, 5));
            self.endPullUpToRefresh(flag[index]);
          }
        }
      });
    });

  });
  var page = [1,1,1,1,1];
  var flag = [false,false,false,false,false];
  var getData= function (elementId,index){
      var associate = {
          'siteList':{
            'tpl':'siteTpl',
            'url':'/collect/getCollectSite?page='+page[0]+'&size=10'
          },
          'personList':{
            'tpl':'personTpl',
            'url':'/collect/getCollectServe?type=达人服务&page='+page[1]+'&size=10'
          },
          'carList':{
            'tpl':'carTpl',
            'url':'/collect/getCollectServe?type=包车服务&page='+page[2]+'&size=10'
          },
          'cateringList':{
            'tpl':'cateringTpl',
            'url':'/collect/getCollectServe?type=餐饮服务&page='+page[3]+'&size=10'
          },
          'mealList':{
            'tpl':'mealTpl',
            'url':'/collect/getCollectMeal?type=聚会套餐&page='+page[4]+'&size=10'
          }
      };
    //  console.log(associate);
      var data;
      $.ajax(associate[elementId]['url'], {
        type: 'get',
        async: false,
        success: function(res) {
          if(res.result.length<=0){
            flag[index] = true;
          }
          var allTpl = template(associate[elementId]['tpl'],res);
          //console.log(document.getElementById(elementId)+"----");
          var element = document.getElementById(elementId);
          element.innerHTML = element.innerHTML+allTpl;
         deleteCollect();
        },
      });

  }

var deleteCollect = function(){
    mui(".mui-table-view-cell").on("tap",".collect-delete",function(event){
      event.stopPropagation();
      var self = this;

      var id = self.getAttribute("data-id");
      console.log(self.getAttribute("data-id"));
      $.ajax('/collect/deleteCollect?coid='+id, {
        type: 'get',
        async: false,
        success: function(res) {
          if(res.code=200){
            mui.toast("删除成功");
              self.parentNode.parentNode.parentNode.parentNode.parentNode.style.display="none";
          }
        }
      });
    });
 }


})(mui);
