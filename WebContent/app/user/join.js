/**
 * 
 */
var check = false;

function formSubmit() {
	var form = document.joinForm;
	if (form.users_id.value == "") {
		alert("아이디를 확인해주세요.");
		form.users_id.focus();
		return false;
	}
	
	if (form.users_id.value.length < 4 || form.users_id.value.length > 16) {
		alert("아이디는 4자 이상, 16자 이하로 입력해주세요");
		form.users_id.focus();
		return false;
		
	} else {
		var reg = /^(?=.*?[#?!@$%^&*]).{5,}$/;
		var hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		
		if (reg.test(form.users_id.value)) {
			alert("아이디에  특수기호는 (_),(-)만 사용 가능합니다..");
			return false;
		}
		else if (hangleCheck.test(form.users_id.value)) {
			alert("아이디에 한글을 사용할 수 없습니다.");
			return false;
		}
		
	}
	
	if (form.users_name.value == "") {
		alert("이름을 입력해주세요");
		form.users_name.focus();
		return false;
	}
	
	if (form.users_pw.value == "") {
		alert("패스워드를 입력해주세요");
		form.users_pw.focus();
		return false;
		
	} else{
		// 8자리 이상, 대문자/소문자/숫자/특수문자 모두 포함되어 있는지 검사
		var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		
		if (!reg.test(form.users_pw.value)) {
			alert("비밀번호는 8자 이상이어야 하며, 대문자/소문자/숫자/특수문자를 모두 포함해야 합니다.");
			return false;
		}
		// 같은 문자를 4번 이상 사용할 수 없다.
		else if (/(\w)\1\1\1/.test(form.users_pw.value)) {
			alert("같은 문자를 4번 이상 사용하실 수 없습니다.");
			return false;
		}
		// 비밀번호에 아이디가 포함되어 있을 수 없다.
		else if (form.users_pw.value.search(form.users_id.value) > -1) {
			alert("비밀번호에 아이디를 포함할 수 없습니다.");
			return false;
		}
		// 비밀번호에 공백을 포함할 수 없다.
		// 포함되면 -1이다.
		else if (form.users_pw.value.search(/\s/) != -1) {
			alert("비밀번호는 공백 없이 입력해주세요.");
			return false;
		}
		
		else if (hangleCheck.test(form.users_pw.value)) {
			alert("비밀번호에 한글을 사용할 수 없습니다.");
			return false;
		}
	}
	
	if (form.users_pw.value != form.users_pw_re.value) {
		alert("패스워드를 확인해주세요.");
		form.users_pw_re.focus();
		return false;
	}
	
	if (form.users_phone.value == "") {
		alert("전화번호를 입력해주세요.");
		form.users_phone.focus();
		return false;
	}
	
	if (form.users_email.value == "") {
		alert("이메일을 입력해주세요.");
		form.users_email.focus();
		return false;
	}
	
	if (form.users_zipcode.value == "") {
		alert("우편번호를 입력해주세요.");
		form.users_zipcode.focus();
		return false;
	}
	
	if (form.users_address_detail.value == "") {
		alert("상세정보를 입력해주세요.");
		form.users_address_detail.focus();
		return false;
	}
	
	
	if (form.genre_no.value == "") {
		alert("장르를 1개 선택해주세요.");
		form.genre_no.focus();
		return false;
	}
	
	form.submit();
}

function checkId(id){
	check = false;
	var reg = /^(?=.*?[#?!@$%^&*]).{5,}$/;
	var hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

	if(id == ""){
		$("#idCheck_text").text("* 아이디를 작성해주세요.");
	} else if (reg.test(id)) {
		$("#idCheck_text").text("아이디에  특수기호는 (_),(-)만 사용 가능합니다..");
	} else if (hangleCheck.test(id)) {
		$("#idCheck_text").text("아이디에 한글을 사용할 수 없습니다.");
	} else if (id.length < 4 || id.length > 16) {
		$("#idCheck_text").text("아이디는 4자 이상, 16자 이하로 입력해주세요");
	} else {
		$.ajax({
			url: contextPath + "/user/UserCheckIdOk.use?users_id="+id,
			type: 'get',
			dataType: 'text',
			success: function(data){
				if(data.trim() == "ok"){
					check = true;
					$("#idCheck_text").text("사용할 수 있는 아이디입니다.");
				}else{
					$("#idCheck_text").text("* 중복된 아이디입니다.");
				}
			},
			error: function(){
				console.log("오류");
			}
		});
	}
}
$("input[name='users_id']").keyup(function(event){
	var id = $("input[name='users_id']").val();
	checkId(id);
});

function checkPw(pw, pw_re){
	check = false;
	if(pw != pw_re){
		$("#checkPw_re").text("* 입력한 비밀번호가 다릅니다.");
	}else{
		$("#checkPw_re").text("* 일치");
	}
}
$("input[name='users_pw_re']").keyup(function(event){
	var pw = $("input[name='users_pw']").val();
	var pw_re = $("input[name='users_pw_re']").val();
	checkPw(pw, pw_re);
});


function check_tos() {
	var form = document.checkTosForm;
	if ($("input:checkbox[name=check_tos_service]").is(":checked") == false) {
		alert("서비스 이용약관에 동의해주세요.");
		form.check_tos_service.focus();
		return false;
	}
	if ($("input:checkbox[name=check_tos_info]").is(":checked") == false) {
		alert("개인정보 수집 및 이용 내역에 동의해주세요.");
		form.check_tos_info.focus();
		return false;
	}
	
	form.submit();
}

$(function(){
    $("#check_all").click(function(){
        var chk = $(this).is(":checked");
        if(chk) $(".select_subject input").prop('checked', true);
        else  $(".select_subject input").prop('checked', false);
    });
});

function updateFormSubmit() {
	var form = document.updateUserForm;
	if (form.users_name.value == "") {
		alert("이름을 입력해주세요");
		form.users_name.focus();
		return false;
	}
	
	if (form.users_pw.value == "") {
		alert("기존의 패스워드를 입력해주세요");
		form.users_pw.focus();
		return false;
		
	} else {
		var id = form.users_id.value;
		var pw = form.users_pw.value;
		$.ajax({
			url: contextPath + "/user/UserCheckPwOk.use?users_id="+id,
			type: 'get',
			dataType: 'text',
			success: function(data){
				if(data.trim() == pw){
					check = true;
				} else{
					alert("기존의 비밀번호를 다시 확인해주세요.");
					form.updateUsers_pw.focus();
					return false;
				}
			},
			error: function(){
				console.log("오류");
			}
		});
	}
	
	if (form.updateUsers_pw.value != "") {
		form.users_pw.value = form.updateUsers_pw.value;

		// 8자리 이상, 대문자/소문자/숫자/특수문자 모두 포함되어 있는지 검사
		var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var hangleCheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		
		if (!reg.test(form.updateUsers_pw.value)) {
			alert("비밀번호는 8자 이상이어야 하며, 대문자/소문자/숫자/특수문자를 모두 포함해야 합니다.");
			return false;
		}
		// 같은 문자를 4번 이상 사용할 수 없다.
		else if (/(\w)\1\1\1/.test(form.updateUsers_pw.value)) {
			alert("같은 문자를 4번 이상 사용하실 수 없습니다.");
			return false;
		}
		// 비밀번호에 아이디가 포함되어 있을 수 없다.
		else if (form.users_pw.value.search(form.updateUsers_pw.value) > -1) {
			alert("비밀번호에 아이디를 포함할 수 없습니다.");
			return false;
		}
		// 비밀번호에 공백을 포함할 수 없다.
		// 포함되면 -1이다.
		else if (form.updateUsers_pw.value.search(/\s/) != -1) {
			alert("비밀번호는 공백 없이 입력해주세요.");
			return false;
		}
		
		else if (hangleCheck.test(form.updateUsers_pw.value)) {
			alert("비밀번호에 한글을 사용할 수 없습니다.");
			return false;
		}
	}
	
	if (form.updateUsers_pw.value != form.updateUsers_pw_re.value) {
		alert("패스워드를 확인해주세요.");
		form.users_pw_re.focus();
		return false;
	}
	
	if (form.users_phone.value == "") {
		alert("전화번호를 입력해주세요.");
		form.users_phone.focus();
		return false;
	}
	
	if (form.users_email.value == "") {
		alert("이메일을 입력해주세요.");
		form.users_email.focus();
		return false;
	}
	
	if (form.users_zipcode.value == "") {
		alert("우편번호를 입력해주세요.");
		form.users_zipcode.focus();
		return false;
	}
	
	if (form.users_address_detail.value == "") {
		alert("상세정보를 입력해주세요.");
		form.users_address_detail.focus();
		return false;
	}
	
	
	if (form.genre_no.value == "") {
		alert("장르를 1개 선택해주세요.");
		form.genre_no.focus();
		return false;
	}
	
	form.submit();
}

