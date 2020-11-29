/**
 * 
 */
var check = false;

function studioFormSubmit() {
	var form = document.studioOpenForm;
	if (form.studio_name.value == "") {
		alert("스튜디오 이름을 입력하세요.");
		form.studio_name.focus();
		return false;
	} else {
		var reg = /^(?=.*?[#?!@$%^&*]).{5,}$/;
		if (reg.test(form.studio_name.value)) {
			alert("공방이름에  특수기호는 (_),(-)만 사용 가능합니다..");
			return false;
		}
	}
	
	if (form.studio_president.value == "") {
		alert("대표자 이름을 입력해주세요");
		form.studio_president.focus();
		return false;
	}
	
	if (form.studio_phone.value == "") {
		alert("공방의 전화번호를 입력해주세요");
		form.studio_phone.focus();
		return false;
		
	}
	
	if (form.studio_zipcode.value == "") {
		alert("우편번호를 입력해주세요.");
		form.studio_zipcode.focus();
		return false;
	}
	
	if (form.studio_address2.value == "") {
		alert("상세정보를 입력해주세요.");
		form.studio_address_detail.focus();
		return false;
	}
	
	form.submit();
}

function checkStudioName(studio_name){
	check = false;
	var reg = /^(?=.*?[#?!@$%^&*]).{5,}$/;

	if(studio_name == ""){
		$("#studioCheck_text").text("* 공방의 이름을 입력하세요.");
	} else if (reg.test(studio_name)) {
		$("#studioCheck_text").text("* 공방이름에  특수기호는 (_),(-)만 사용 가능합니다..");
	} else {
		$.ajax({
			url: contextPath + "/user/StudioNamCheckOk.use?studio_name="+studio_name,
			type: 'get',
			dataType: 'text',
			success: function(data){
				if(data.trim() == "ok"){
					check = true;
					$("#studioCheck_text").text("사용할 수 있는 공방 이름입니다.");
				}else{
					$("#studioCheck_text").text("* 중복된 공방 이름이 있습니다.");
				}
			},
			error: function(){
				console.log("오류");
			}
		});
	}
}
$("input[name='studio_name']").keyup(function(event){
	var studio_name = $("input[name='studio_name']").val();
	checkStudioName(studio_name);
});


function updateStudioFormSubmit() {
	var form = document.updateStudioForm;
	
	if (form.studio_president.value == "") {
		alert("대표자 이름을 입력해주세요");
		form.studio_president.focus();
		return false;
	}
	
	if (form.studio_phone.value == "") {
		alert("공방의 전화번호를 입력해주세요");
		form.studio_phone.focus();
		return false;
		
	}
	
	if (form.studio_address.value == "") {
		alert("우편번호를 입력해주세요.");
		form.studio_zipcode.focus();
		return false;
	}
	
	if (form.studio_address2.value == "") {
		alert("상세정보를 입력해주세요.");
		form.studio_address_detail.focus();
		return false;
	}
	
	form.submit();
	
}