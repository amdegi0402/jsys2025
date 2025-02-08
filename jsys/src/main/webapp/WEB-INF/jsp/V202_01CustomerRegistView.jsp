<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- レスポンシブデザイン -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>得意先登録画面</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<!-- 固定幅コンテナp padding y 上下方向 -->
    <div class="container py-2">
        <!-- text-center:テキストを中央揃え mb:margin-bottom-->
        <div class="text-center mb-4">
        <!-- display-6:見出し -->
            <h2 class="display-5">得意先登録</h2>
        </div>

        <!-- メニューに戻るボタン -->
        <form action="./jsysF" method="post" class="mb-3">
        <!-- d-flex:中身を横並び justify-content-end: 中身を右寄せ-->
            <div class="d-flex justify-content-end">
            <!-- btn: ボタンの基本的なデザイン btn-secondary: 灰色のボタンにする -->
                <button type="submit" name="buttonId" value="V201_01_01" 
                        class="btn btn-secondary">
                    メニュー画面へ戻る
                </button>
            </div>
        </form>

        <!-- メインフォーム -->
        <form action="./jsysF" method="post">
        <!-- card: カードを作る shadow-sm: 薄い影をつける -->
            <div class="card shadow-sm">
            <!-- card-body: カードの中身を入れる場所、余白を自動的につける -->
                <div class="card-body">
                <!-- row: 行を作る g-3: 列と列の間に3サイズの隙間を作る -->
                    <div class="row g-3">
                    <!-- col-md-6: 画面を12等分したときの6つ分（半分）使う -->
                        <div class="col-md-6">
                        <!-- form-group: 入力項目をグループ化して間隔を整える -->
                            <div class="form-group mb-3">
                            <!-- ラベルをきれいに表示する -->
                                <label for="custName" class="form-label">得意先名</label>
                                <!-- 入力欄をきれいに表示する -->
                                <input type="text" class="form-control" id="custName" 
                                       name="custName" value="${param.custName}" 
                                       maxLength="6">
                            </div>
                        </div>
                        
                        <div class="col-md-6">
                            <div class="form-group mb-3">
                                <label for="telNo" class="form-label">電話番号</label>
                                <input type="tel" class="form-control" id="telNo" 
                                       name="telNo" value="${param.telNo}" 
                                       maxLength="10">
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group mb-3">
                                <label for="postalCode" class="form-label">郵便番号</label>
                                <input type="text" class="form-control" id="postalCode" 
                                       name="postalCode" value="${param.postalCode}" 
                                       maxLength="6">
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group mb-3">
                                <label for="discountRate" class="form-label">割引率</label>
                                <div class="input-group">
                                    <input type="number" class="form-control" id="discountRate" 
                                           name="discountRate" value="${param.discountRate}" 
                                           maxLength="2">
                                    <span class="input-group-text">%</span>
                                </div>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-group mb-3">
                                <label for="address" class="form-label">住所</label>
                                <input type="text" class="form-control" id="address" 
                                       name="address" value="${param.address}" 
                                       maxLength="30">
                            </div>
                        </div>
                    </div>

                    <div class="d-flex justify-content-end mt-4">
                        <button type="submit" name="buttonId" value="V202_02" 
                                class="btn btn-primary">
                            登録
                        </button>
                    </div>
                </div>
            </div>
        </form>

        <!-- エラーメッセージ -->
        <div class="mt-3">
            <c:if test="${not empty requestScope.errorMessage || not empty requestScope.errorMessageList}">
                <!-- alert メッセージ周りに余白や枠線がつく alert-danger赤色のスタイルを適用-->
                <div class="alert alert-danger">
                    <c:out value="${requestScope.errorMessage}" />
                    <c:forEach var="message" items="${requestScope.errorMessageList}">
                        <c:out value="${message}" /><br>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>