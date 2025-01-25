<!-- All Rights Reserved ,Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	session="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- HTMLの作成 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<!-- 見出し -->
	<div style="text-align: center">
		<h2>販売管理システム</h2>
		<h2>ログイン画面</h2>
	</div>
	<!-- フォーム -->
	<form action="./jsysF" method="post">
		<table style="margin: 0 auto">
			<tr>
				<td>従業員番号</td>
				<td><input type="text" name="employeeNo" maxLength="6"
					size="14" value="${param.employeeNo}"></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><input type="password" name="password" maxLength="6"
					size="10"></td>
			</tr>
		</table>
		<div style="text-align: center">
			<button type="submit" name="buttonId" value="V101_01"
				style="width: 100px">ログイン</button>
		</div>
	</form>
	<div style="text-align: center; color: red; font-weight: bold;">
		<%-- エラーメッセージがある場合、出力 --%>
		<c:out value="${requestScope.errorMessage}" />
		<c:forEach var="message" items="${requestScope.errorMessageList}">
			<c:out value="${message}" />
			<br>
		</c:forEach>
	</div>
</body>
</html>