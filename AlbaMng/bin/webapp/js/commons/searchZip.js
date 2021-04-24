/**
 * 
 */
$.fn.searchZip=function(param){
	const searchBtn = this;
	const url = param.url;
	const zipCodeTag = $(param.zipCodeTag).get(0);
	const add1Tag = $(param.add1Tag).get(0);
	const add2Tag = $(param.add2Tag).get(0);
	const zipModal = $(ZIPMODALSRC.trim().replaceAll(/\s{2,}/igm, " ")).on("hidden.bs.modal", function(event){
		zipTable.search("");	
		zipTable.draw();	
	});
	$("body").append(zipModal);
	
	searchBtn.on("click", function(){
		zipModal.modal("show");
	});
	
	const inModalForm = zipModal.find("form").on("submit", function(event){
		event.preventDefault();
		zipCodeTag.value = inModalForm[0].modalZipCode.value;
		add1Tag.value = inModalForm[0].address1.value;
		add2Tag.value= inModalForm[0].address2.value;
		zipModal.modal("hide");
		return false;
	});
	
	let zipTable = zipModal.find('#zipTable').DataTable( {
		processing: true,
		serverSide: true,
	    ajax: {
	        url: url
	    },
	    columns: [ 
	    	{ data: 'zipcode' }
	        , { data: 'address' }
		], 
		select: {
			style: "single"
		},
		pageLength: 7,
		lengthChange: false,
		info: false
	    
	} ).on( 'select', function ( e, dt, type, indexes ) {
		let zipVO = dt.data();
		inModalForm[0].modalZipCode.value = zipVO.zipcode;
		inModalForm[0].address1.value = zipVO.address;
	} );
}
const ZIPMODALSRC = `<div class="modal fade" id="zipModal" data-bs-backdrop="static" data-bs-keyboard="false" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">우편번호 검색</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<form id="inModalForm">
			<div class="modal-body">
				<table id="zipTable" class="w-100">
					<thead>
						<tr>
							<th class="text-center">우편번호</th>
							<th class="text-center">주소</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="input-group input-group-sm mt-3 mb-3">
				  <span class="input-group-text" id="inputGroup-sizing-sm">우편번호</span>
				  <input readonly name="modalZipCode" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" tabindex="-1" required/>
				</div>
				<div class="input-group input-group-sm mb-3">
				  <span class="input-group-text" id="inputGroup-sizing-sm">주소1</span>
				  <input readonly name="address1" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" tabindex="-1" required/>
				</div>
				<div class="input-group input-group-sm mb-3">
				  <span class="input-group-text" id="inputGroup-sizing-sm">주소2</span>
				  <input name="address2" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" required />
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-secondary">주소입력</button>
			</div>
			</form>
		</div>
	</div>	
</div>`;