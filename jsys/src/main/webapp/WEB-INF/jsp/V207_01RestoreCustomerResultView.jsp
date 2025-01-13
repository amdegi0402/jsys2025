
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>得意先復元結果画面</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>

	<div style="text-align: center">
		<h2>得意先情報を復元しました</h2>
	</div>
	<!-- フォーム -->
	<form action="./jsysF" method="post">
		<div align="right">
			<button type="submit" name="buttonId" value="V201_01_01"
				style="width: 200px">メニュー画面へ戻る</button>
		</div>
	</form>
	<!-- フォーム -->
	<form method="post" action="./jsysF">
		<table border="1" style="margin: 0 auto">
			<tr>
				<th width="100">得意先コード</th>
				<th width="200">得意先名</th>
				<th width="100">電話番号</th>
				<th width="100">郵便番号</th>
				<th width="300">住所</th>
				<th width="80">割引率</th>
			</tr>
			<c:forEach var="customer" items="${customerList}" varStatus="status">
				<tr>
					<td nowrap><c:out value="${customer.custCode}" /></td>
					<td nowrap><c:out value="${customer.custName}" /></td>
					<td nowrap><c:out value="${customer.telNo}" /></td>
					<td nowrap><c:out value="${customer.postalCode}" /></td>
					<td nowrap><c:out value="${customer.address}" /></td>
					<td nowrap><c:out value="${customer.discountRate}" /> %</td>
				</tr>
			</c:forEach>
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