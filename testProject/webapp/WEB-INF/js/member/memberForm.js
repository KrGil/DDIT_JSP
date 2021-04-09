/**
 * 
 */
	$.generateMessage = function (message){
		let messageTag = $("<span>")
			.text(message?message:"")
			.addClass("message")
			.addClass("error");
		return messageTag;
	}
	let idTag = $("[name='mem_id']").on("change", function(){
		idCheckBtn.trigger("click");
	});
	let idCheckBtn = $("#idCheck").on("click", function(){
		memberForm.data("idcheck", "FAIL");
		idTag.next(".message:first").remove();
		let mem_id = idTag.val();
		$.ajax({
			url : "idCheck.do",
			method : "post",
			data : {
				id:mem_id
			},
			dataType : "json",
			success : function(resp) {
				memberForm.data("idcheck", resp.result);
				if(resp.result!="OK"){
					let messageTag = $.generateMessage("아이디 중복");
					idTag.after(messageTag);
					idTag.focus();
				}
			},
			error : function(xhr, error, msg) {
				console.log(xhr);
				console.log(error);
				console.log(msg);
			}
		});
	});
	let memberForm = $("#memberForm").on("submit", function(){
		let checked = $(this).data("idcheck")=="OK";
		if(!checked){
			let messageTag = idTag.next(".message:first");
			if(!messageTag || messageTag.length==0){
				messageTag = $.generateMessage();
			}
			messageTag.text("아이디 중복 체크 하세요.");	
			idTag.after(messageTag);
		}
		return checked;
	});