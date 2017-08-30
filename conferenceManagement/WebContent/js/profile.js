function sure(){
	alert("确认更改？")
	var head=document.getElementById("pic").value
	var sex=document.getElementsByName("gender").value
	var userpassword=document.getElementById("password").value
	var email=document.getElementById("email").value
	var tel=document.getElementById("tel").value
	document.getElementById("tel")=tel;
}
function cancel(){
	alert("确认取消？")
}

