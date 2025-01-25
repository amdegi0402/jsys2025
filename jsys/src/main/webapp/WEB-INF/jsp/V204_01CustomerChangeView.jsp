
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
		<h2>得意先更新画面</h2>
	</div>
	<!-- フォーム -->
	<form action="./jsysF" method="post">
		<div align="right">
			<button type="submit" name="buttonId" value="V201_01_01"
				style="width: 200px">メニュー画面へ戻る</button>
		</div>
	</form>
	<form action="./jsysF" method="post">
		<table>
			<tr>
				<td>得意先コード</td>
				<td><input type="text" name="custCode"
					value="${param.custCode}" maxLength="6" size="10"></td>
			</tr>
			<tr>
				<td><button type="submit" name="buttonId" value="V204_02">検索</button></td>
			</tr>
		</table>

		<c:if test="${not empty customer}">
			<table border="1">
				<tr>
					<td>得意先コード</td>
					<td><c:out value="${requestScope.customer.custCode}" /></td>
				</tr>
				<tr>
					<td>得意先名</td>
					<td><input type="text" name="custName"
						value="${requestScope.customer.custName}" maxLength="6" size="10" /></td>
				</tr>
				<tr>
					<td>電話番号</td>
					<td><input type="text" name="telNo"
						value="${requestScope.customer.telNo}" maxLength="10" size="10" /></td>
				</tr>
				<tr>
					<td>郵便番号</td>
					<td><input type="text" name="postalCode"
						value="${requestScope.customer.postalCode}" maxLength="6"
						size="10" /></td>
				</tr>
				<tr>
					<td>住所</td>
					<td><input type="text" name="address"
						value="${requestScope.customer.address}" maxLength="30" size="10" /></td>
				</tr>
				<tr>
					<td>割引率</td>
					<td><input type="text" name="discountRate"
						value="${requestScope.customer.discountRate}" maxLength="2"
						size="10" /> %</td>
				</tr>
			</table>

			<br>
			<div>
				<button type="button" onclick="history.back()">前画面へ戻る</button>
			</div>

			<div align="right">
				<button type="submit" name="buttonId" value="V204_03"
					style="width: 200px">更新</button>
			</div>
			<div align="right">
				<button type="submit" name="buttonId" value="V204_01"
					style="width: 200px">クリア</button>
			</div>
		</c:if>
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