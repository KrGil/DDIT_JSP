/**
 * 
 */
$(function(){
	let commonForm = $("#commonForm");
	// 수정 및 삭제 버튼 이벤트 처리
	$("#updateBtn,#deleteBtn").on("click", function(){
		let flag = null;
		let action = null;
		if($(this).prop("id")=="deleteBtn"){
			action = commonForm.data("delete");
			flag = confirm("삭제하시겠습니까?");
		}else{
			flag = true;
			action = commonForm.data("update");
		}
		if(flag){
			commonForm.attr("action", action);
			commonForm.submit();
		}
	});
	
	// 자격증 사본 이미지 모달 hide 이벤트 처리
	var modalTag = $("#imageViewModal").on("hiden.bs.modal", function(){
		$(this).find(".modal-body").empty();
	});
	
	const licenseImageURL = "licenseImage.do?alId=%I&licCode=%C";
	
	// 자격증 사본 이미지 조회
	$(".viewImage").on("click", function(){
		var alId = $(this).data("alid");
		var licCode = $(this).data("code");
		var licName = $(this).data("name");
		var licDate = $(this).data("date");
		modalTag.find("#imageViewModalTitle").html(
			licName+"("+licDate+")"
		);
		
		modalTag.find(".modal-body").html(
			$("<img>").attr({
					src:licenseImageURL.replace("%I", alId).replace("%C",licCode)
					, style:"width:50%;height:50%;"		
				})	
		);
		modalTag.modal("show");
	});	
});