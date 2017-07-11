var searchString = getUrlParam("s");
document.getElementById("search").value = searchString;
var pages = new Array(1,1,1,1);
var flag = new Array(false,false,false,false);

document.getElementById("search").onkeypress = function(event){
  if(event.keyCode==13){
      location.href="/search?s="+this.value;
   }
};

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
            //ul.appendChild(createFragment(ul, index, 5));
            pages[index]++;
            self.endPullUpToRefresh(flag[index]);
          }
        }
      });
    });

  });

  var getData= function (elementId,index){
      var associate = {
          'siteList':{
            'tpl':'siteTpl',
            url:function(){
              return '/site/list/like/'+pages[0]+'?key='+searchString+'&pageSize=10';
            }
          },
          'decorationList':{
            'tpl':'decorationTpl',
              url:function(){
              return '/decoration/list/like/'+pages[1]+'?pageSize=10&key='+searchString;
            }
          },
          'serviceList':{
            'tpl':'serviceTpl',
            url:function(){
              return '/serve/list/like/'+pages[2]+'?pageSize=10&key='+searchString;
            }
          },
          'mealList':{
            'tpl':'mealTpl',
            url:function(){
              return '/meal/list/like/'+pages[3]+'?pageSize=10&key='+searchString;;
            }
          }
      };
    //  console.log(associate);
      var data;
      $.ajax(associate[elementId].url(), {
        type: 'get',
        async: false,
        success: function(res) {
          if(res.result.length<10){
            flag[index] = true;
          }
          if(elementId=="siteList" || elementId=="mealList" ){
            var cardData = {};
            cardData.list = new Array();

            for (var i = 0; i < res.result.length; i++) {
              var temp = {};
              temp.id = res.result[i].siid;
              temp.title = res.result[i].title;
              temp.price = res.result[i].price;
              temp.img = res.result[i].imgs.length>0?res.result[i].imgs[0].imgUrl:'';
              temp.label = res.result[i].tags.split("|");
              cardData.list.push(temp);
            }
            var siteTpl = template(associate[elementId]['tpl'], cardData);
            var element = document.getElementById(elementId);
            element.innerHTML = element.innerHTML+siteTpl;
          // }else if(elementId=="mealList"){

          }else{
            var allTpl = template(associate[elementId]['tpl'],res);
            //console.log(document.getElementById(elementId)+"----");
            var element = document.getElementById(elementId);
            element.innerHTML = element.innerHTML+allTpl;
          }

        },
      });

  }

})(mui);
