
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
		<h2>得意先削除画面</h2>
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
				<td><button type="submit" name="buttonId" value="V203_02">検索</button></td>
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
					<td><c:out value="${requestScope.customer.custName}" /></td>
				</tr>
				<tr>
					<td>電話番号</td>
					<td><c:out value="${requestScope.customer.telNo}" /></td>
				</tr>
				<tr>
					<td>郵便番号</td>
					<td><c:out value="${requestScope.customer.postalCode}" /></td>
				</tr>
				<tr>
					<td>住所</td>
					<td><c:out value="${requestScope.customer.address}" /></td>
				</tr>
				<tr>
					<td>割引率</td>
					<td><c:out value="${requestScope.customer.discountRate}" /> %</td>
				</tr>
			</table>
		</c:if>
		<br>
		<div>
			<button type="submit" name="buttonId" value="V201_01">前画面へ戻る</button>
		</div>
	
		<div align="right">
			<button type="submit" name="buttonId" value="V203_03"
				style="width: 200px">削除</button>
		</div>
		<div align="right">
			<button type="submit" name="buttonId" value="V203_01"
				style="width: 200px">クリア</button>
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