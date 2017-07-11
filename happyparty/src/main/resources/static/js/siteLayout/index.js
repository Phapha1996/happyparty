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
  mui.ajax('/decoration/getAllTheme?page='+page+'&size=2', {
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

        mui.ajax('/decoration/getThemeDecortion?thid='+res.result[i].thid+'&page=1&size=5', {
          type: 'get',
          async: false,
          success: function(response) {
              // console.log(response);
              var d = {
                theme:res.result[i].theme_name,
                img:res.result[i].img.imgUrl,
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
