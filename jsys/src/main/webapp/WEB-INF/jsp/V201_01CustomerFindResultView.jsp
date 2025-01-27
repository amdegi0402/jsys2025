
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- HTMLの作成 -->
<!DOCTYPE HTML html>
<html>
<head>
<meta charset="utf-8">
<title>得意先一覧画面</title>
</head>
<body>
	<!-- 見出し -->
	<div style="text-align: center">
		<h2>得意先検索結果</h2>
	</div>
	<!-- フォーム -->
	<form action="./jsysF" method="post">
		<div align="right">
			<button type="submit" name="buttonId" value="V201_01_01"
				style="width: 200px">メニュー画面へ戻る</button>
		</div>
	</form>
	<!-- フォーム -->
	<form action="./jsysF" method="post">
	<c:if test="${not empty customer}">
		<table border="1">
			<tr>
				<td>得意先コード</td>
				<td><c:out
						value="${requestScope.customer.custCode}" /></td>
			</tr>
			<tr>
				<td>得意先名</td>
				<td><c:out
						value="${requestScope.customer.custName}" /></td>
			</tr>
			<tr>
				<td>電話番号</td>
				<td><c:out
						value="${requestScope.customer.telNo}" /></td>
			</tr>
			<tr>
				<td>郵便番号</td>
				<td><c:out
						value="${requestScope.customer.postalCode}" /></td>
			</tr>
			<tr>
				<td>住所</td>
				<td><c:out
						value="${requestScope.customer.address}" /></td>
			</tr>
			<tr>
				<td>割引率</td>
				<td><c:out
						value="${requestScope.customer.discountRate}" /> %</td>
			</tr>
		</table>
		</c:if>
		<br>
		<div>
			<button type="submit" name="buttonId" value="V201_01">前画面へ戻る</button>
		</div>
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