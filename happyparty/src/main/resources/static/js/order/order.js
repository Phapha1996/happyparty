var data;
$.ajax({
  url: "/shop/list",
  async: false,
  success: function(res) {
    for (var type in res.result) {
      var html = template("item", res.result[type]);
      if (type == "场地") {
        $("#siteList").append(html);
      }
      if (type == "场地布置") {
        $("#layoutList").append(html);
      }
      if (type == "餐饮服务") {
        $("#goodsList").append(html);
      }
      if (type == "达人服务") {
        $("#personList").append(html);
      }
      if (type == "包车服务") {
        $("#carList").append(html);
      }
    }

    mui(".cj-item-opt").on("tap", ".deleteItem", function() {
      var btnArray = ['否', '是'];
      var id = this.getAttribute("data-id");
      mui.confirm('您确定要删除该订单？', '确认', btnArray, function(e) {
        if (e.index == 1) {
          $.ajax({
            url: '/shop/delete/'+id,
            dataType: 'json',
            success: function(response) {
              if (response.code == 200) {
                location.reload();
              }
            }
          });

        }
      });
    });

    mui(".cj-num").on("change",".mui-input-numbox",function(){
        var id = this.getAttribute("data-id");
        var value = this.value;
        $.ajax({
          url: '/shop/update?spid='+id+'&number='+value,
          dataType: 'json',
          success: function(response) {

          }
        });
    });

  }
});


mui(".mui-scroll-wrapper").scroll({
  indicators: true //是否显示滚动条
});

var item = $(".cj-car-item");
var totalPriceDom = $("#totalPrice");

$(".cj-car-list").each(function() {
  if ($(this).find(".cj-car-item").length > 0) {
    $(this).find(".cj-no-goods").hide();
  }
});

$(".cj-car-item input[type='checkbox']").change(function() {
  compute();
});

$(".mui-input-numbox").change(function() {
  compute();
});

$("input[name='all']").click(function() {
  var all = $(this);
  var checked = all.get(0).checked;
  console.log(checked);
  if (checked) {
    $(".cj-car-item-select input[type='checkbox']").prop('checked', true);
  } else {
    $(".cj-car-item-select input[type='checkbox']").prop('checked', false);
  }
  compute();
});

function compute() {
  var totalPrice = 0;
  item.each(function() {
    var self = $(this);
    if (self.find("input[type='checkbox']").get(0).checked) {
      var price = self.find(".price").text();
      var num = self.find("input[type='number']").val();
      totalPrice += parseInt(price) * parseInt(num);
      //console.log(num);
    }
  });

  totalPriceDom.text(totalPrice);
}
