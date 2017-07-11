"use strict";
class PriceCalenar{



    constructor(year,month,price){
		this.year = year;
		this.month = month;
		this.day = 1;
		this.monthNum = new Array(31, 28 + this.isLeap(year), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
		this.week = this.getDayIsWeek()+1;
		this.div  = document.getElementById("priceCalenar");
		this.weekString = new Array ('日','一','二','三','四','五','六');
		// this.price = new Array('1888','1888','1888','1888','1888','1888','1888','1888','1888','1888',
		// 					   '1888','1888','1888','1888','无','1888','1888','1888','1888','1888',
		// 					   '1888','1888','1888','无','1888','1888','1888','1888','1888','1888','1888','1888');

		this.price = price;

		this.nowDate = new Date();
	}


	getDayIsWeek(){
		var temp = new Date(this.year+"/"+this.month+"/"+this.day);
		return new Date(temp-1).getDay();
	}

	show(){

		this.div.innerHTML ="";

		var day = 1;
		var element = null;

		element = document.createElement("div");

		var lastMonth = "";
		var nextMonth = "";
		var lastYear,nextYear;
		//if(this.month>this.nowDate.getMonth()+1){
			var lastNum,year;
			if(this.month==1){
				lastYear = this.year-1;
				lastNum = 12;
			}else{
				lastYear = this.year;
				lastNum = this.month-1;
			}


			if(!(this.year==this.nowDate.getFullYear()&&this.nowDate.getMonth()+1==this.month)){
				lastMonth = "<span class='cj-price-calenar-select-month' id='priceCalenarLast' >上一月</span> ";
			}

		//}

		//if(this.month==this.nowDate.getMonth()+1){
			var nextNum;
			if(this.month==12){
				nextYear = this.year+1;
				nextNum = 1;

			}else{
				nextYear = this.year;
				nextNum = this.month+1;

			}
			if(!(this.year==this.nowDate.getFullYear()&&this.nowDate.getMonth()+2==this.month) ){
				nextMonth = " <span class='cj-price-calenar-select-month' id='priceCalenarNext'>下一月</span> ";
			}

			if((this.nowDate.getMonth()+1==12&&this.month==1)){
				nextMonth = "";
			}

		//}

		element.innerHTML=lastMonth+this.year+"."+this.month+nextMonth;



		$(element).addClass("cj-price-calenar-title");
		this.div.appendChild(element);

    var prices  = this.price;

		if(nextMonth!=""){
			var priceCalenarNext =  document.getElementById("priceCalenarNext");
			priceCalenarNext.addEventListener("tap",function(){
				new PriceCalenar(nextYear,nextNum,prices).show();
			});
		}

		if(lastMonth!=""){
			var priceCalenarLast =  document.getElementById("priceCalenarLast");
			priceCalenarLast.addEventListener("tap",function(){
				new PriceCalenar(lastYear,lastNum,prices).show();
			})
		}
;

		element = document.createElement("div");
		this.div.appendChild(element);
		for(var i=0;i<this.weekString.length;i++){
			var span = this.createSpan("cj-price-calenar-week");
			element.appendChild(span);
			span.innerHTML = this.weekString[i];
		}

		element = null;
		for(var i=0;i<49;i++){

			if(i%7==0){
				if((this.week==7 && i>7)||this.week!=7){
					 element = document.createElement("div");
					this.div.appendChild(element);
				}
			}

			if(element){

				if(i<this.week){
					var span = this.createSpan("cj-price-calenar-box");
					element.appendChild(span);
					span.innerHTML = " &nbsp;<br/>&nbsp; ";
				}else{
					if(day<=this.monthNum[this.month-1]){
						var span = this.createSpan("cj-price-calenar-box");
						if(this.year==this.nowDate.getFullYear()&&this.nowDate.getMonth()+1==this.month && this.nowDate.getDate()==day){
							span.style.backgroundColor = "#03a9f4";
							span.style.color = "#fff";
							if(i%7==0||i%7==6){
								span.innerHTML =  " "+ day + "<br/><span class='price'>￥"+this.price[1]+"</span>";
							}else{
								span.innerHTML =  " "+ day + "<br/><span class='price'>￥"+this.price[0]+"</span>";
							}

						// }else if(this.price[]=='无'){
						// 	span.style.color = "#ccc";
						// 	span.innerHTML = " "+ day + "<br/><span class='price'>无房</span>";
						}else{
							if(i%7==0||i%7==6){
								span.style.color = "#03a9f4";
								span.innerHTML =  " "+ day + "<br/><span class='price'>￥"+this.price[1]+"</span>";
							}else{
								span.innerHTML =  " "+ day + "<br/><span class='price'>￥"+this.price[0]+"</span>";
							}

						}
						element.appendChild(span);

						day++;
					}else{
						break;
					}
				}

			}

		}
	}

	createSpan(className){
		var span = document.createElement("span");
		$(span).addClass(className);
		return span;
	}

	/*
	*是否是闰年
	*/
	isLeap(year) {
  		return year % 4 == 0 ? (year % 100 != 0 ? 1 : (year % 400 == 0 ? 1 : 0)) : 0;
    }


}
