var data = {
  "list":new Array()
};

var aid = getUrlParam("aid");
var did = getUrlParam("id");
var list = document.getElementById('goodsList');
var flag = true;
var carDetailBox = $("#carDetailBox");
var backdrop = $(".mui-backdrop-car");
var goodsList = $("#goodsList");
var carDetailBoxList = $("#carDetailBoxList");
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
      auto: true,
      callback: function() {
        if(flag) {
          getData();
        }
        flag = false;
        console.log(flag);
        this.endPullupToRefresh();
      }
    }
  }
});

function getData() {
  mui.ajax('/decoration/getIdDepProduct?did='+did+'&page=1&size=100', {
    type: 'get',
    async: false,
    success: function(res) {
    //  data[res.result[0].productCatrgory.cat_name] = 1;
    for(var i=0;i<res.result.length;i++){
      if(typeof data.list[res.result[i].productCatrgory.cat_name] == "undefined"){
          data.list[res.result[i].productCatrgory.cat_name] = new Array();
          data.list[res.result[i].productCatrgory.cat_name].push(res.result[i]);
      }else{
          data.list[res.result[i].productCatrgory.cat_name].push(res.result[i]);
      }
    }
      console.log(data);
      var cardTpl = template('itemTpl',data);
      list.innerHTML = list.innerHTML+cardTpl;
      goodsListsub();goodsListadd();
    //  $.getScript("../js/common/mui.min.js");
    },
  });
}

$("#addOrderList").click(function(){
  var idArray = new Array();
  var numArray = new Array();
  carDetailBoxList.find("input[type='number']").each(function(){
    var self = $(this);
    idArray.push(self.attr("data-id"));
    numArray.push(self.val());
  });
  console.log(idArray);
  console.log(numArray);
  if(idArray.length>0){
    $.ajax({
      url:'/shop/add/decpro',
      data:{
        dpids:idArray,
        numbers:numArray,
        adminId:aid
      },
      traditional: true,
      dataType:'json',
      success:function(res){
        if(res.code==200){
          mui.toast("添加成功");
        }else{
          mui.toast("添加失败");
        }
      }
    });
  }else{
    mui.toast("您没有选择");
  }
});

$("#carDetailbtn").click(function(){
  backdrop.css("display","block");
  carDetailBox.addClass("mui-active");
});


backdrop.click(function(){
  backdrop.css("display","none");
  carDetailBox.removeClass("mui-active");
});


$("#clear").click(function(){
  carDetailBoxList.html("");
  goodsList.find("input[type='number']").val(0);
  $("#totalPrice").text(0);
});

function sub(){
  carDetailBoxList.find(".mui-btn-numbox-minus").click(function(){
    var numberImput = $(this).next();
    var num = numberImput.val();
    numberImput.val(parseInt(num)-1);
    //console.log(goodsList.find("input[data-id='"+numberImput.attr("data-id")+"']").val());
    goodsList.find("input[data-id='"+numberImput.attr("data-id")+"']").val(numberImput.val());
    change();
  });
}
function add(){
  carDetailBoxList.find(".mui-btn-numbox-plus").click(function(){
    var numberImput = $(this).prev();
    var num = numberImput.val();
    numberImput.val(parseInt(num)+1);
    goodsList.find("input[data-id='"+numberImput.attr("data-id")+"']").val(numberImput.val());
     change();
  });
}

function goodsListsub(){
  goodsList.find(".mui-btn-numbox-minus").click(function(){
    var numberImput = $(this).next();
    var num = numberImput.val();
    numberImput.val(parseInt(num)-1);
    change();
    });
}
function goodsListadd(){
  goodsList.find(".mui-btn-numbox-plus").click(function(){
    var numberImput = $(this).prev();
    var num = numberImput.val();
    numberImput.val(parseInt(num)+1);
    change();
    });
}

function change(){
  carDetailBoxList.html("");
  var totalPrice = 0;
  goodsList.find("input[type='number']").each(function(){
    var self = $(this);

    var datas = {
      id:self.attr("data-id"),
      title:self.attr("data-title"),
      price:self.attr("data-price"),
      num:self.val()
    }
    totalPrice = parseInt(totalPrice)+parseInt(datas.num)*parseInt(datas.price);
    var item = carDetailBoxList.find("input[data-id='"+datas.id+"']");
    if(datas.num>0){
      detailCarItemTpl = template("detailCarItemTpl",datas);
      carDetailBoxList.append(detailCarItemTpl);
    }
  });
  sub();add();
  $("#totalPrice").text(totalPrice);
}
