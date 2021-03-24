/**
 * 
 */

$.test=function(){
		alert("TEST");
}

$.fn.test2=function(){
	alert("TEST2"+this.attr("method"));
	return this;
}

$.fn.formToAjax=function(param){
 	this.on("submit", function(e){
		e.preventDefault();
		let url = this.action;
		let method = this.method;
		let inputs = $(this).find(":input");
	
		let data = {}
		$(inputs).each(function(index, input){
			let name = $(this).attr("name");
			let value= $(this).val();
			data[name] = value;
			console.log(data);
		});
	
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType: param.dataType,
			success: param.success, 
			error:function(xhr, error, msg){
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		});	
		return false;
	})
	return this;
}

$.fn.formToAjax=function(param){
	const SRCPTRN = "%A?%N=%V";
	const action = $("form")[0].action; //id가 아니라 태그 명을 가지고 왔다. 그래서 몇개인지 모르기에 배열로 return
	$("form").on("submit", function(event){
		param.resultArea.empty();
		var name = this.name; // 넘어갈 param값
		var values = $(this).val(); // 다중 선택시 배열로 돌아온다.
		var imgs = []
		$(values).each(function(idx, value){
			var src =  SRCPTRN.replace("%A", action)
								.replace("%N", name)
								.replace("%V", value)
			img = $("<img>").attr("src", src);
			imgs.push(img);
		})
		param.resultArea.html(imgs);
	})
}