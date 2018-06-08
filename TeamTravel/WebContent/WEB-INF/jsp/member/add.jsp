<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/member/add" method="post">
		email* : <input type="text" name="email" value="ee@email.com"><br/>
		비밀번호* : <input type="text" name="password" value="ee"><br/>
		닉네임* : <input type="text" name="nickname" value="aa"><br/>
		나이 : <input type="text" name="age" value="4"><br/>
		성별 : <input type="text" name="gender" value="0"><br/>
		자기소개 : <input type="text" name="selfintroduction" value="haha"><br/>
		
		<input type="submit" value="login">
	</form>
</body>
</html>