//轮播数据获取与模板渲染
var page = 1;
var sliderData;
if(!location.href.indexOf("/meal")){
  document.getElementById("search").onkeypress = function(event){
    if(event.keyCode==13){
        location.href="/search?s="+this.value;
     }
  };
}


mui.ajax('/home/findPlay?type=home', {
  type: 'get',
  async: false,
  success: function(res) {
    sliderData = res.result;
  },
});

var sliderTpl = template('sliderTpl', sliderData);
document.getElementById('sliderContainer').innerHTML = sliderTpl;
var slider = mui("#slider");

slider.slider({
  interval: 500,
});

var sliderTextData;
mui.ajax('/home/findRe', {
  type: 'get',
  async: false,
  success: function(res) {
    sliderTextData = res;
  //  console.log(sliderTextData);
  },
});
var sliderTextTpl = template('sliderTextTpl', sliderTextData.result);
document.getElementById('sliderTextContainer').innerHTML = sliderTextTpl;
var slider = mui("#slider");
$(".cj-text-carousel").slideUp();

var cardData = {};
mui.ajax('/meal/list/'+page+'?mealType=生日趴&pageSize=3', {
  type: 'get',
  async: false,
  success: function(res) {
    if (res.code == 200) {
      cardData.list = new Array();
      for (var i = 0; i < res.result.length; i++) {
        var temp = {};
        temp.id = res.result[i].smid;
        temp.title = res.result[i].title;
        temp.price = res.result[i].price;

        temp.img = res.result[i].imgs.length==0?'':res.result[i].imgs[0].imgUrl;
        temp.label = res.result[i].tags.split("|");
        cardData.list.push(temp);
      }
    }
    //console.log(cardData);
  }
});
var cardTpl = template('cardTpl', cardData);
document.getElementById('cardContainer').innerHTML = cardTpl;

var cityPicker = new mui.PopPicker({
  layer: 2
});
cityPicker.setData(cityData);
var showCityPickerButton = document.getElementById('showCityPicker');
var city = document.getElementById("city")
showCityPickerButton.addEventListener('tap', function(event) {
  cityPicker.show(function(items) {
    city.innerText = items[1].text;
    //返回 false 可以阻止选择框的关闭
    //return false;
  });
}, false);
