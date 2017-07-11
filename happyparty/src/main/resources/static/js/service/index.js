var type = getUrlParam("type");
//var type = "达人服务";
document.getElementsByClassName("mui-title")[0].innerHTML = type;
console.log(type);
mui.init();
(function($) {
  //阻尼系数
  var deceleration = mui.os.ios?0.003:0.0009;

  var page = new Array();
  var flag = null;

  mui.ajax("/serve/catlist?type="+type,{
    type: 'get',
    async:false,
    success: function(res) {

      var navTpl = template('navTpl',res);
      var categoryNav = document.getElementById("categoryNav");
      categoryNav.innerHTML = categoryNav.innerHTML + navTpl;

      var listTpl = template('listTpl',res);
      var listGroup = document.getElementsByClassName("mui-slider-group")[0];
      listGroup.innerHTML = listGroup.innerHTML + listTpl;

      page.push(1);
      for(var i=0;i<res.result.length;i++){
        page.push(1);
      }

      console.log(page);
    },
  });


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
            var self = this;
            var ul = self.element.querySelector('.mui-table-view');
            // console.log(self.getAtrribute("id")+"--");
            // ul.insertBefore(createFragment(ul, index, 10, true), ul.firstChild);
             self.endPullDownToRefresh();
          }
        },
        up: {
          auto: true,
          callback: function() {
            var self = this;

            var url = "";
            if(index==0){
              url = "/serve/list/"+page[0]+"?type="+type;
            }else{
              url = "/serve/list/category/"+page[index]+"?type=达人服务&categoryId="+self.element.getAttribute("data-id");
            }
            page[index]++;
            getData(url,index);
            console.log(flag);
            self.endPullUpToRefresh(flag);
          }
        }
      });
    });
  });



  var getData= function (url,index){
    //  console.log(associate);
      $.ajax(url, {
        type: 'get',
        async: false,
        success: function(res) {

          if(res.result.length>0){
            flag = false;
          }else{
            flag = true;
          }
          var tpl = template('item',res);
          var element = document.getElementsByClassName('mui-table-view');
          element[index].innerHTML = element[index].innerHTML+tpl;
        },
      });

  }

})(mui);
