

var data;
var list = document.getElementById('cardContainer');
var page = 1;
var flag = false;
mui.init({
  pullRefresh: {
    container: '#pullrefresh',
    down: {
      callback: function() {
        list.innerHTML = list.innerHTML = "";
        page = 1;
        getData();
        this.endPulldownToRefresh();
      }
    },
    up: {
      contentrefresh: '正在加载...',
      auto:true,
      callback: function() {
        getData();

        this.endPullupToRefresh(flag);
      }
    }
  }
});
var cardData = {};

var searchString = "全部全部全部";
var searchFlag = false;

function getData() {

	var input = $(".selectlist input");
 var section = input.eq(0).val();
 var num = input.eq(1).val();
 var price  = input.eq(2).val();


 var newSearchString = section+num+price;
 if(newSearchString!=searchString){
	 searchFlag = true;
	 searchString = newSearchString;
	 page = 1;
 }else{
	 searchFlag = false;
 }

 var url = "";
 if(searchString=="全部全部全部"){
	 url = '/site/getAllSite?page='+page+'&size=10';
 }else{

	 var sectionUrl = '';
	 if(section!='全部'){
		 sectionUrl = 'city='+section;
	 }
	 var personNumUrl='';
	 if(num!='全部'){
		 var personNum = num.split("-");
			personNumUrl = '&startApply='+personNum[0]+'&endApply='+personNum[1];
	 }
	 var priceUrl = '';
	 if(price!='全部'){
		 var prices =  price.split("-");
		 priceUrl = '&startValue='+prices[0]+'&endValue='+prices[1];
	 }
	 url = '/site/getLikeSite?'+sectionUrl+personNumUrl+priceUrl+'&page='+page+'&size=3';
 }

 console.log(url);

 console.log(section+","+num+","+price);
  mui.ajax(url, {
    type: 'get',
    async: false,
    error:function(){
        mui.toast("没有记录");
				if(searchFlag){
					list.innerHTML = "";
				}
    },
    success: function(res) {
      if(res.result.length<0){
        flag = true;
      }
      cardData.list = new Array();
      for (var i = 0; i < res.result.length; i++) {
        var temp = {};
        temp.id = res.result[i].siid;
        temp.title = res.result[i].title;
        temp.price = res.result[i].price;
        temp.img = res.result[i].imgs[0].imgUrl;
        temp.label = res.result[i].tags.split("|");
        cardData.list.push(temp);
      }

      var cardTpl = template('cardTpl', cardData);
			console.log(searchFlag);
      if(searchFlag){
        list.innerHTML = "";
				console.log(1234566);
        mui('.mui-scroll-wrapper').scroll().scrollTo(0,0,100);
      }
      list.innerHTML = list.innerHTML + cardTpl;
    },
  });
	page++;
}
