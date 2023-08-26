# 概要
遊戯王のフレーバーテキストでクイズを作りたい

# 使用ライブラリ選定
 - Room
 -- SQLite使うなら、楽かつ使うらしいので
 - Koin
 --DIが使えるらしい、Springかじってるし行けるやろ精神
その他随時追加、テスト系をおそらく追加する

# 以下書きなぐり
// https://codeforfun.jp/android-studio-quiz-game-with-kotlin-4/
// とりあえず構造を真似する
// ダメそうなので一から考える
// 軽い設計
// DBからランダムに10問文のデータ取得⇒4つの中から正解を作成（正解flgをつける）⇒画面に1問目表示⇒成否判定画面（出来ればカードURL）⇒次の問題⇒ラスト終わったら正解数画面⇒トップページへ

// DB作成
// テーブル定義をもう少しだけ詰める
//　sqliteのテーブル作成
// testcsv作成
//　インポートが成功したらcsv作成クロールべた書きする
// カラム名

// roomでイニシャライズ方法調べる
// name, text, type, attribute, correct_count
// https://pgmemo.tokyo/data/archives/908.html
// roomでイニシャライズ方法調べる


// https://hirauchi-genta.com/kotlin-room-init/
// 収集するデータを確定する
// 悲しいけどクロールする

//　必要メソッド
//DBアクセスメソッド
//問題作成メソッド（出題回数インサート、DTO作成、リストで持つ）
//画面へセットするメソッド
//成否判定表示メソッド同時に正解数加算（問題を押したとき）
//最後の問題か判定するメソッド（成否表をとじたとき）
//次の問題を取り出すメソッド（上の次）

//https://developer.android.com/training/data-storage/room?hl=ja#kotlin
//データの永続化に使う