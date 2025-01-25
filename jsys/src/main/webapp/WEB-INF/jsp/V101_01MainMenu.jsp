<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ画面</title>
</head>
<body>
<h2>メインメニュー</h2>
<form action="/jsys/jsysF" method="post">
<div>
<button type="submit" name="buttonId" value="101_01">一覧</button>
<button type="submit" name="buttonId" value="101_01">検索</button>
<button type="submit" name="buttonId" value="101_01">更新</button>
<button type="submit" name="buttonId" value="101_01">削除</button>
<button type="submit" name="buttonId" value="101_01">登録</button>
<button type="submit" name="buttonId" value="101_01">削除済み一覧</button>
</div>

</form>

</body>
</html>