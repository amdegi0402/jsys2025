<!-- All Rights Reserved ,Copyright(c) Fujitsu Learning Media Limited -->
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
		<h2>削除済み得意先一覧</h2>
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
		<!-- テーブルに表示するjじょうほうがあるか確認 -->
		<c:if test="${not empty customerList}">
			<table border="1" style="margin: 0 auto">
				<tr>
					<!-- 全選択全解除用のチェックボックス -->
					<th><input type="checkbox"
						onclick="
                    let checkboxes = document.getElementsByName('custCodes');
                    for (let i = 0; i < checkboxes.length; i++) {
                        checkboxes[i].checked = this.checked;
                    }" /></th>
					<th width="100">得意先コード</th>
					<th width="200">得意先名</th>
					<th width="100">電話番号</th>
					<th width="100">郵便番号</th>
					<th width="300">住所</th>
					<th width="80">割引率</th>
				</tr>
				<c:forEach var="customer" items="${customerList}" varStatus="status">
					<tr>
						<!-- チェックボックスで復元対象を選択 -->
						<td aligign="center"><input type="checkbox" name="custCodes"
							value="${customer.custCode}" /></td>
						<td nowrap><c:out value="${customer.custCode}" /></td>
						<td nowrap><c:out value="${customer.custName}" /></td>
						<td nowrap><c:out value="${customer.telNo}" /></td>
						<td nowrap><c:out value="${customer.postalCode}" /></td>
						<td nowrap><c:out value="${customer.address}" /></td>
						<td nowrap><c:out value="${customer.discountRate}" /> %</td>

					</tr>
				</c:forEach>
			</table>
			<!-- 選択された顧客をまとめて復元 -->
			<div style="text-align: center">
				<button type="submit" name="buttonId" value="V207_01"
					style="width: 200px">復元</button>
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