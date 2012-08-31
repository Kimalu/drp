function validate_userId(userId) {
		if(userId==null){
			alert("userId error");
			return false;
		}
		userIdValue = userId.value;
		ulength = userIdValue.trim().length;
		if (ulength == 0) {
			alert("用户代码不能为空");
			userId.focus();
			return false;
		}
		/*
		if(ulength<4||ulength>6){
			alert("用户代码长度只能在4-6字符之间");
			userId.focus();
			return false;
		}
		 */
		var re = new RegExp(/^[a-zA-Z]/);
		if (!re.test(userIdValue)) {
			alert("用户代码必须以字母开头");
			userId.focus();
			return false;
		}
		re.compile(/^[a-zA-Z0-9]{4,6}$/);
		if (!re.test(userIdValue)){
			alert("用户代码长度只能在4-6字符之间");
			userId.focus();
			return false;
		}
		return true;
	}
	
	function validate_userName(userName){
		if(userName==null){
			alert("userName error");
			return false;
		}
		userNameValue=userName.value;
		if(userNameValue.trim().length==0){
			alert("用户名不能为空");
			userName.focus();
			return false;
		}
		/*
		re=new RegExp(/^[a-zA-Z0-9]{6,8}$/);
		if(!re.test(userNameValue)){
			alert("用户名必须由字母和数字组成，并且长度在6-8之间");
			userName.focus();
			return false;
		}
		*/
		if(userNameValue.length<6||userNameValue>8){
			alert("用户名长度必须在6-8之间");
			userName.focus();
			return false;
		}
		return true;
	}
	function validate_password(password){
		if(password==null){
			alert("password error");
			return false;
		}
		passwordValue=password.value;
		if(passwordValue.length==0){
			alert("密码不能为空");
			password.focus();
			return false;
		}
		
		if(passwordValue.length<6||passwordValue.length>8){
			alert("密码长度只能在6-8之间");
			password.focus();
			return false;
		}
		return true;
	}
	function validate_password(password,obj){
		if(password==null){
			obj.innerHTML="<font color='red'>password error</font>";
			return false;
		}
		passwordValue=password.value;
		if(passwordValue.length==0){
			obj.innerHTML="<font color='red'>密码不能为空</font>";
			
			return false;
		}
		
		if(passwordValue.length<6||passwordValue.length>8){
			obj.innerHTML="<font color='red'>密码长度只能在6-8之间</font>";
			
			return false;
		}
		return true;
	}
	function validate_contactTel(contactTel){
		if(contactTel==null){
			alert("contactTel error");
			return false;
		}
		contactTelValue=contactTel.value;
		if(contactTelValue.length!=0){
			var re=new RegExp(/^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$/);
			if(!re.test(contactTelValue)){
				alert("请输入正确的手机号码");
				contactTel.focus();
				return false;
			}
		}
		return true;
	}
	function validate_email(email){
		if(email==null){
			alert("email error");
			return false;
		}
		emailValue=email.value;
		if(emailValue.length!=0){
			var re=new RegExp(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/)
			if(!re.test(emailValue)){
				alert("输入的email地址有误");
				email.focus();
				return false;
			}
		}
		return true;
	}