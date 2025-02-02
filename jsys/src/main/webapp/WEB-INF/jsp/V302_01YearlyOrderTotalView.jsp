
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- HTMLの作成 -->
<!DOCTYPE HTML html>
<html>
<head>
<meta charset="utf-8">
<!-- Chart.js をCDNから読み込み -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<title>年別受注集計画面</title>

</head>
<body>
	<!-- 見出し -->
	<div style="text-align: center">
		<h2>年別受注集計</h2>
	</div>
	<!-- フォーム -->
	<form action="./jsysF" method="post">
		<div align="right">
			<button type="submit" name="buttonId" value="V201_01_01"
				style="width: 200px">メニュー画面へ戻る</button>
		</div>
	</form>
	<form action="./jsysF" method="post">
		<table border="1">
			<tr>

				<td>集計する年</td>
				<td><select name="year">
						<option value="2020">2020</option>
						<option value="2021">2021</option>
						<option value="2022">2022</option>
				</select> 年</td>
				<td>
					<form action="./jsysF" method="post">
						<button name="buttonId" value="V302_01">集計</button>
						<button name="buttonId" value="V302_00">クリア</button>
					</form>
				</td>
			</tr>
		</table>
	</form>

	<c:if test="${not empty orderTotal}">
		<table border="1" style="margin: 0 auto" id="data-table">
			<tr>
				<td>得意先コード</td>
				<td>得意先名</td>
				<td>得意先別合計金額</td>
			</tr>
			<c:forEach var="order" items="${requestScope.orderTotal}"
				varStatus="status">
				<tr>
					<td nowrap><c:out value="${order.custCode}" /></td>
					<td nowrap><c:out value="${order.custName}" /></td>
					<td nowrap><c:out value="${order.totalPrice}" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2">総計</td>
				<td nowrap><c:out value="${sumTotal}" /></td>
			</tr>
		</table>


	</c:if>

	<!-- グラフを描画するためのキャンパス -->
	<div style="width: 500px; height: 300px; margin: 20px auto;">
		<canvas id="myChart"></canvas>
	</div>
	<script>
		//テーブルからラベルとデータを抽出する処理
		var table = document.getElementById("data-table");
		var labels = [];
		var data = [];

		//最初の行はヘッダー南夫で２行目以降のデータを取得
		//１列目の行が総計であればスキップ
		for (var i = 1; i < table.rows.length; i++) {
			//得意先名を1つ目のlabelsに入れる
			var firstCellText = table.rows[i].cells[0].innerText.trim();
			//総計業はスキップ
			if (firstCellText === "総計") {
				continue; 
			}
			//得意先名をラベル、得意先別合計金額をデータとして抽出
			labels.push(table.rows[i].cells[1].innerText.trim());
			data.push(parseFloat(table.rows[i].cells[2].innerText.trim()));
		}

		//Charts,jsでグラフを描画
		var ctx = document.getElementById("myChart").getContext("2d");
		var myChart = new Chart(ctx, {
			type : 'bar',
			data : {
				labels : labels,
				datasets : [ {
					//横軸に得意先名
					label : '得意先別合計金額',
					//縦軸に金額
					data : data,
					backgroundColor : 'rgba(75, 192, 192, 0.2)',
					borderColor : 'rgba(75, 192, 192, 1)',
					borderWidth : 1,
					 // 棒の幅を直接ピクセルで指定
					barThickness: 30 
		         
				} ]

			}
		});
	</script>

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