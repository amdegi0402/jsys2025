
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- HTMLの作成 -->
<!DOCTYPE HTML html>
<html>
<head>
<meta charset="utf-8">
<!-- レスポンシブデザイン -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>得意先登録画面</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container">
		<div class="row justify-content-center">
			<!-- 見出し -->
			<div class="display-5">
				<h2>得意先登録結果</h2>
			</div>
			<!-- フォーム -->
			<form action="./jsysF" method="post">
				<div class="d-flex justify-content-end">
					<button type="submit" name="buttonId" value="V201_01_01"
						class="btn btn-secondary">メニュー画面へ戻る</button>
				</div>
			</form>
			<div class="alert alert-success">登録が完了しました。</div>
			<!-- フォーム -->
			<form action="./jsysF" method="post">
				<!-- レスポンシブ -->
				<div class="content">
					<div class="row justify-content-center">
						<div class="col-md-8">
							<div class="table-responsive">
								<!-- セルに枠線を追加 -->
								<table class="table table-bordered">
			<tr class="table-light">
				<td class="col-3">得意先コード</td>
				<td class="col-9"><c:out
						value="${requestScope.customer.custCode}" /></td>
			</tr>
			<tr class="table-light">
				<td>得意先名</td>
				<td><c:out
						value="${requestScope.customer.custName}" /></td>
			</tr>
			<tr class="table-light">
				<td>電話番号</td>
				<td><c:out
						value="${requestScope.customer.telNo}" /></td>
			</tr>
			<tr class="table-light">
				<td>郵便番号</td>
				<td><c:out
						value="${requestScope.customer.postalCode}" /></td>
			</tr>
			<tr class="table-light">
				<td>住所</td>
				<td><c:out
						value="${requestScope.customer.address}" /></td>
			</tr>
			<tr class="table-light">
				<td>割引率</td>
				<td><c:out
						value="${requestScope.customer.discountRate}" /> %</td>
			</tr>
		</table>
		</div>
		</div>
		</div>
		<br>
		<div>
			<button type="submit" name="buttonId" value="V202_01" class="btn btn-secondary">前画面へ戻る</button>
		</div>
		</div>
		</div>
	</form>
	
	<!-- エラーメッセージ -->
	<div class="mt-3">
	<c:if test="${not empty requestScope.errorMessage || not empty requestScope.errorMessageList}">
	
		<%-- エラーメッセージがある場合、出力 --%>
		<div class="alert alert-danger">
		<c:out value="${requestScope.errorMessage}" />
		<c:forEach var="message" items="${requestScope.errorMessageList}">
			<c:out value="${message}" />
			<br>
		</c:forEach>
		</div>
		</c:if>
	</div>
	</div>
	</div>
</body>
</html>