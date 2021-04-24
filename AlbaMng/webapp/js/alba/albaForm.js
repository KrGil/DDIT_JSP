/**
 * 
 */
let licCodes = $("[name='licCodes']");
let albaForm = $("#albaForm").on("reset", function(){
	$(".licImagesTB").remove();
	$.each(spanBuffer, function(idx, span){
		span.show();
		let licCode = span.prop("id");
		$(licCodes).find("[value='"+licCode+"']").addClass("matched");
	});
	spanBuffer.length=0;
	albaForm.find("[name='deleteLicCodes']").remove();
});
let spanBuffer = [];
//기존 자격증 삭제 처리시 테스트 코드
$(".delBtn").on("click", function(){
	let dellicCode = $(this).data("code");
	albaForm.append(
		$("<input>").attr({
			type:"text"
			, name:"deleteLicCodes"
			, value:dellicCode
		})
	);
	spanBuffer.push( $(this).closest("span") );
	$(this).closest("span").hide();
	$(licCodes).find("[value='"+dellicCode+"']").removeClass("matched");
});	
// 자격증 선택시, 이미지 태그 추가
licCodes.on("blur", function(){
	$(".licImagesTB").remove();
	let selectTag = $(this);
	let options = $(this).find("option:selected")
	let licTB = $("<table class='licImagesTB table-bordered'>");
	selectTag.closest("div#licenseArea").append(licTB);
	$(options).each(function(idx, option){
		licTB.append(
			$("<tr>").append(
				$("<td class='align-middle'>").html($("<span>").addClass("mr-3").text($(option).text())),		
				$("<td class='align-middle'>").append(
					$("<input>").attr({
						type:"file"
						, 'class':"form-control mr-3"
						, name:"licImages"
						, required:true
						, id:"file_"+idx
						, accept:"image/*"
					}),
					$("<img>").hide()
					.addClass("file_"+idx)
					.addClass("thumbnail")
				),
				$("<td class='align-middle'>").html(
					$("<input>").attr({
						type:"date"
						, 'class':"form-control mr-3"
						, name:"licDates"
						, required:true
						, id:"date_"+idx
					})
				)
			)
		);	
	});
});
// 자격증 사본 미리보기
$("#albaForm").on("change", "[type='file']", function(){
	let fileTag = this;
	let files = $(this).prop("files");
	let id = $(this).prop("id");
	if(!files) return;
	for(let idx=0; idx<files.length; idx++){
		let reader = new FileReader();
		reader.onloadend = function(event){
			let imgTag = $(fileTag).next("."+id);
			imgTag.attr("src", event.target.result);
			imgTag.show();
		}
		reader.readAsDataURL(files[idx]);
	}
});