mui('body').on('tap','a',function(){
    var href = this.getAttribute("href");
   if(href!==undefined && href!="" && href.indexOf("#")!=0){
      window.top.location.href=this.href;
      console.log(this.href);
   }

});
