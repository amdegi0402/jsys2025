
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- HTMLの作成 -->
<!DOCTYPE HTML html>
<html>
<head>
<meta charset="utf-8">
<title>得意先検索画面</title>
</head>
<body>
	<!-- 見出し -->
	<div style="text-align: center">
		<h2>得意先検索</h2>
	</div>
	<!-- フォーム -->
	<form action="./jsysF" method="post">
		<div align="right">
			<button type="submit" name="buttonId" value="V201_01_01"
				style="width: 200px">メニュー画面へ戻る</button>
		</div>
	</form>
	<form action="./jsysF" method="post">
		<label>
			<input type="radio" name="category" value="custCode" checked>
			得意先コード
		</label>
		<label>
			<input type="radio" name="category" value="custName">
			得意先名
		</label>
		<br>
		<table>
			<tr>
				<td><input type="text" name="custValue" value="${param.custValue}" maxlength=6 size="10"></td>
			</tr>
			<tr>
				<td><button type="submit" name="buttonId" value="V201_02">検索</button></td>
			</tr>
		</table>
	</form>
	
	<!-- エラーメッセージ -->
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