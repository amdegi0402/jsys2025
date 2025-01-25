<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- HTMLの作成 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>メインメニュー画面</title>
</head>

<body>
	<!-- 見出し -->
	<div style="text-align:center">
		<h2>販売支援システム</h2>
		<h2>メインメニュー</h2>
	</div>

	<!-- フォーム -->
	<form action="./jsysFC" method="post">
		<table style="margin: 0 auto">
			<!-- ボタン -->
			<tr>
				<td>
					<button type="submit" name="buttonId" value="V101_02_01"
						 style="width: 200px">得 意 先 管 理</button>
				</td>
			</tr>
			<tr>
				<td>
					<button type="submit" name="buttonId" value="V101_02_02"
						 style="width: 200px">得 意 先 別 集 計</button>
				</td>
			</tr>
		</table>
		<br>
		<div style="text-align:center">
			<button type="submit" name="buttonId" value="V101_02_03"
				 style="width: 100px">ログアウト</button>
		</div>
	</form>
</body>
</html>