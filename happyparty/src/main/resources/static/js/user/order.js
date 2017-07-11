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
            var self = this;
            var ul = self.element.querySelector('.mui-table-view');
            console.log(self.getAtrribute("id")+"--");
            ul.insertBefore(createFragment(ul, index, 10, true), ul.firstChild);
            self.endPullDownToRefresh();
          }
        },
        up: {
          auto: true,
          callback: function() {
            var self = this;
            var ul = self.element.querySelector('.mui-table-view');
            var ulId = ul.getAttribute("id");
            console.log(ulId+"--");
            getData(ulId);
            //ul.appendChild(createFragment(ul, index, 5));
            self.endPullUpToRefresh();
          }
        }
      });
    });
    var createFragment = function(ul, index, count, reverse) {
      var length = ul.querySelectorAll('li').length;
      var fragment = document.createDocumentFragment();
      var li;
      for (var i = 0; i < count; i++) {
        li = document.createElement('li');
        li.className = 'mui-table-view-cell';
        li.innerHTML = '第' + (index + 1) + '个选项卡子项-' + (length + (reverse ? (count - i) : (i + 1)));
        fragment.appendChild(li);
      }
      return fragment;
    };
  });

  var getData= function (elementId){
      var associate = {
          'noPayList':{
            'tpl':'noPayTpl',
            'url':'/orders/list/1?ordersState=1&pageSize=10'
          },
          'noHandleList':{
            'tpl':'noHandleTpl',
            'url':'/orders/list/1?ordersState=2&pageSize=10'
          },
          'noUseList':{
            'tpl':'noUseTpl',
            'url':'/orders/list/1?ordersState=3&pageSize=10'
          },
          'usedList':{
            'tpl':'usedTpl',
            'url':'/orders/list/1?ordersState=4&pageSize=10'
          },
          'refundList':{
            'tpl':'refundTpl',
            'url':'/orders/list/1?ordersState=5&pageSize=10'
          },
          'cancelList':{
            'tpl':'cancelTpl',
            'url':'/orders/list/1?ordersState=6&pageSize=10'
          },
          'refundedList':{
            'tpl':'refundedTpl',
            'url':'/orders/list/1?ordersState=7&pageSize=10'
          },
      };
    //  console.log(associate);
      var data;
      $.ajax(associate[elementId]['url'], {
        type: 'get',
        async: false,
        success: function(res) {
          for(var key in res.result){
            for(var i=0;i<res.result[key].length;i++){
              res.result[key][i].ctime = new Date(res.result[key][i].ctime).format('yyyy-MM-dd');
            }
          }
          var allTpl = template(associate[elementId]['tpl'],res);
          //console.log(document.getElementById(elementId)+"----");
          var element = document.getElementById(elementId);
          element.innerHTML = element.innerHTML+allTpl;
        },
      });

  }

})(mui);
