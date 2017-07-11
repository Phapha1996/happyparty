var data = {
  'themeList':new Array()
};
var list = document.getElementById('cardContainer');
var page = 1;
var flag = false;

mui.init({
  pullRefresh: {
    container: '#pullrefresh',
    down: {
      callback: function(){
        list.innerHTML = "";
        page = 1;
        getData();
        this.endPulldownToRefresh();
      }
    },
    up: {
      auto:true,
      contentrefresh: '正在加载...',
      callback:function(){
          getData();
          page++;
          $.getScript("../js/common/mui.min.js");
          this.endPullupToRefresh(flag);
      }
    }
  }
});

function getData(){
  mui.ajax('/meal/cat/list/'+page+'?pageSize=2', {
    type: 'get',
    async: false,
    error:function(){
      flag = true;
    },
    success: function(res) {
      // console.log(res);
      //data.list()
	  if(res.result.length<2){
		  flag = true;
	  }
      for(var i=0;i<res.result.length;i++){

        mui.ajax('/meal/list/1?categoryId='+res.result[i].mcid+'&pageSize=5', {
          type: 'get',
          async: false,
          success: function(response) {
              // console.log(response);
              var d = {
                theme:res.result[i].categoryName,
                img:res.result[i].imgs,
                thid:res.result[i].thid,
                list:response.result,
                len:parseInt(response.result.length/2)+1
              };
              data.themeList.push(d);
          },
        });
      }
    },
  });

  // console.log(data);
    list.innerHTML = list.innerHTML = "";
    var cardTpl = template('cardTpl',data);
    list.innerHTML = list.innerHTML+cardTpl;


}
