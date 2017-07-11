$("#datePicker").click(function() {
      var dtpicker = new mui.DtPicker({
        type: "date", //设置日历初始视图模式
        beginDate: new Date(2017, 06, 08), //设置开始日期
        endDate: new Date(2018, 06, 08), //设置结束日期
        labels: ['年', '月', '日'], //设置默认标签区域提示语
      })
      dtpicker.show(function(e) {
        $("#datePicker").text(e.text);
      });
    });

     $("#publish").click(function(){

       var theme = $("select[name='theme']").val();

      var address = $("select[name='address']").val();

      var time = $("#datePicker").text();

      var personNum = $("input[name='personNum']").val();

      var name = $("input[name='name']").val();
      var team = $("input[name='team']").val();
      var phoneRegx = /^1\d{10}$/;
      var phone = $("input[name='phone']").val();
      if(!phoneRegx.test(phone)){
        mui.toast("手机号码格式不正确");
        return;
      }

      $.ajax({
        url:'/custom/addcustom',
        type:'get',
        data:{
          cu_type:theme=='活动主题'?'':theme,
          cu_area:address,
          cu_time:time=='选择时间'?'':time,
          cu_people_number: personNum,
          cu_user:name,
          cu_phone:phone,
          cu_remark:$("#textarea").val(),
          cu_budget:$("input[name='budget']").val(),
          cu_team:$("input[name='team']").val()
        },
        dataType:'json',
        success:function(response){
          if(response.code==200){
            mui.toast("发布成功，我们会尽快联系您");
            $.ajax({
              'url':'/custom/informBoos?people='+name+'&phone=18978323457&number='+personNum,
              success:function(res){

              }
            });
            //location.reload();
          }else{
            mui.toast("发布失败，请重试");
          }
        }
      });

    });
