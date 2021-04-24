/**
 * 
 */
	$("#inputUI :input").on("change", function(){
		searchBtn.trigger("click");
	});
	let listBody = $("#listBody");
	let pagingArea = $("#pagingArea");
	let pagingA = pagingArea.on('click', "a" ,function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		searchForm.find("[name='page']").val(1);
		return false;
	});
	
	let searchForm = $("#searchForm").ajaxForm({
		dataType : "json",
		success : function(resp) {
			listBody.empty();
			pagingArea.empty();
			let list = resp.dataList;
			let trTags = [];
			if( list.length>0 ){
				$(list).each(function(idx, alba){
					trTags.push(
						$("<tr>").append(
							$("<td>").html(
									$("<a>").text(alba.alName+"("+ alba.alGen +")")
											.attr("href", CONTEXTPATH+"/alba/albaView.do?who="+alba.alId)
							),
							$("<td>").text(alba.alAge),
							$("<td>").text(alba.grName),
							$("<td>").text(alba.alAdd1),
							$("<td>").text(alba.alHp),
							$("<td>").text(alba.alMail)
						)
					);
					
				});
			}else{
				trTags.push($("<tr>").html($("<td colspan='6'>").text("조건에 맞는 상품이 없음.")));
			}
			listBody.html(trTags);
			pagingArea.html( resp.pagingHTML );
		},
		error : function(errResp) {
			console.log(errResp);
		}
	});

	let searchBtn = $("#searchBtn").on("click", function(){
		let inputs = $(this).parents("div#inputUI").find(":input[name]");
		$(inputs).each(function(index, input){
			let name = $(this).attr("name");
			let value = null;
			if($(this).attr("type")=="radio"){
				value = $("[name='"+name+"']:checked").val();
			}else{
				value = $(this).val();
			}
			let hidden = searchForm.find("[name='"+name+"']");
			hidden.val(value);
		});
		searchForm.submit();
	});