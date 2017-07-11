function addConllect(type, id) {
  document.getElementById('addCollect').onclick = function() {
    mui.ajax('/collect/addCollect?product_type=' + type + '&product_id=' + id, {
      type: 'get',
      async: false,
      success: function(res) {
        if (res.code == 200) {
          mui.toast(res.result);
        } else if (res.code == 500) {
          mui.toast(res.msg);
        } else {
          mui.toast("请先登录");
        }
      },
    });
  }
}




function addCar(type, pid, aid) {
  document.getElementById('addCar').onclick = function() {
    mui.ajax('/shop/add?productType=' + type + '&productId=' + pid + '&admin.aid=' + aid,{
      type: 'get',
      async: false,
      success: function(res) {
        if (res.code == 200) {
          mui.toast("加入成功");
        } else if (res.code == 500) {
          mui.toast("加入失败");
        } else {
          mui.toast("请先登录");
        }
      },
    });
  }
}


function share(){

}
