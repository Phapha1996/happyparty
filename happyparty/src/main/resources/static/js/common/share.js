    var _system={
        $share:function(id){return document.getElementById(id);},
   _client:function(){
      return {w:document.documentElement.scrollWidth,h:document.documentElement.scrollHeight,bw:document.documentElement.clientWidth,bh:document.documentElement.clientHeight};
   },
   _scroll:function(){
      return {x:document.documentElement.scrollLeft?document.documentElement.scrollLeft:document.body.scrollLeft,y:document.documentElement.scrollTop?document.documentElement.scrollTop:document.body.scrollTop};
   },
   _cover:function(show){
      if(show){
     this.$share("cover").style.display="block";
     this.$share("cover").style.width=(this._client().bw>this._client().w?this._client().bw:this._client().w)+"px";
     this.$share("cover").style.height=(this._client().bh>this._client().h?this._client().bh:this._client().h)+"px";
  }else{
     this.$share("cover").style.display="none";
  }
   },
   _guide:function(click){
      this._cover(true);
      this.$share("guide").style.display="block";
      this.$share("guide").style.top=(_system._scroll().y+5)+"px";
      window.onresize=function(){_system._cover(true);_system.$share("guide").style.top=(_system._scroll().y+5)+"px";};
  if(click){_system.$share("cover").onclick=function(){
         _system._cover();
         _system.$share("guide").style.display="none";
 _system.$share("cover").onclick=null;
 window.onresize=null;
  };}
   },
   _zero:function(n){
      return n<0?0:n;
   }
}