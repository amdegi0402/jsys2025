
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- HTMLの作成 -->
<!DOCTYPE HTML html>
<html>
<head>
<meta charset="utf-8">
<!-- 先に jQuery を読み込む -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- ▼ 次に DataTables を読み込む -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>

<title>得意先一覧画面</title>
</head>
<body>
	<!-- 見出し -->
	<div style="text-align: center">
		<h2>得意先一覧</h2>
	</div>
	<!-- フォーム -->
	<form action="./jsysF" method="post">
		<div align="right">
			<button type="submit" name="buttonId" value="V201_01_01"
				style="width: 200px">メニュー画面へ戻る</button>
		</div>
	</form>
	<!-- ダウンロードボタン -->
	<button onclick="downloadCsv()">ダウンロード</button>
	
	
	<!-- フォーム -->
	<form method="post" action="./jsysF">

		<table id="myTable" border="1" style="margin: 0 auto;">

			<!-- ここを thead として定義 -->
			<thead>
				<tr>
					<!-- ID列（ソート用アイコン ▲▼）-->
					<th width="100">得意先コード <a href="#"
						onclick="sortData('id','asc')">▲</a> <a href="#"
						onclick="sortData('id','desc')">▼</a>
					</th>
					<th width="200">得意先名 <a href="#"
						onclick="sortData('name','asc')">▲</a> <a href="#"
						onclick="sortData('name','desc')">▼</a>
					</th>
					<th width="100">電話番号 <a href="#"
						onclick="sortData('tel','asc')">▲</a> <a href="#"
						onclick="sortData('tel','desc')">▼</a>
					</th>
					<th width="100">郵便番号 <a href="#"
						onclick="sortData('postal','asc')">▲</a> <a href="#"
						onclick="sortData('postal','desc')">▼</a>
					</th>
					<th width="300">住所 <a href="#"
						onclick="sortData('address','asc')">▲</a> <a href="#"
						onclick="sortData('address','desc')">▼</a>
					</th>
					<th width="80">割引率 <a href="#"
						onclick="sortData('discount','asc')">▲</a> <a href="#"
						onclick="sortData('discount','desc')">▼</a>
					</th>
				</tr>
			</thead>

			<!-- データ行はこちらに -->
			<tbody>
				<c:forEach var="customer" items="${customerList}">
					<tr>
						<td><c:out value="${customer.custCode}" /></td>
						<td><c:out value="${customer.custName}" /></td>
						<td><c:out value="${customer.telNo}" /></td>
						<td><c:out value="${customer.postalCode}" /></td>
						<td><c:out value="${customer.address}" /></td>
						<td><c:out value="${customer.discountRate}" />%</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<script>
			// DataTables 初期化部分
			let dataTable;
			$(document).ready(function() {
				dataTable = $('#myTable').DataTable({
					paging : false,//テーブル内のデータを分けて表示しない
					searching : false,//検索ボックスは利用しない
					info : false,//データの情報は表示しない
					order : [], // デフォルトソートを無効化
					columnDefs : [ {//テーブル内のソートについてのルールを定義
						targets : '_all',
						orderable : true
					} ]
				});
			});
			// ソート用関数
			function sortData(columnName, direction) {
				//テーブル列の位置を管理
				let colIndex = {
					'id' : 0,
					'name' : 1,
					'tel' : 2,
					'postal' : 3,
					'address' : 4,
					'discount' : 5
				}[columnName];

				//「存在しない列名ではない」場合にのみ実行する
				if (colIndex !== undefined) {
					dataTable.order([ colIndex, direction ]).draw();
				}
			}

			//csvダウンロード関数
			function downloadCsv(){
				//テーブルデータを取得
				const table = document.getElementById('myTable');


				//テーブルの各行をループしてcsvに変換
				let csvData = '\ufeff得意先コード,得意先名,電話番号,郵便番号,住所,割引率\n';
				//let csvData = '\uFEFF得意先コード,得意先名,電話番号,郵便番号,住所,割引率\n';
				//ヘッダー行をスキップして開始
				for(let i = 1; i < table.rows.length; i++){
					let rowData = [];
					for(let cell of table.rows[i].cells){
						rowData.push(cell.textContent);
					}
					csvData += rowData.join(',') + '\n';
				}
						
				//csvダウンロードリンクを生成
				const link = document.createElement("a");//linkを作成
				link.href = 'data:text/csv;charset=utf-8,' + encodeURIComponent(csvData);
				link.download = 'table_data.csv'; //ダウンロードファイル名
				link.click();//ボタンクリック
			}
			
		</script>
</body>
</html>