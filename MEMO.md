_//https://qiita.com/kame_yang/items/b5e2297fdf489919d9be
//https://developer.android.com/training/data-storage/room/prepopulate?hl=ja
// databaseBuilderの使い方？
// koinとの流用？一度クエリを流す
// https://blog.ryskit.com/entry/2019/03/24/160822
// https://qiita.com/Slowhand0309/items/ece245e2c0e3656afe6b
//事前に作るファイルはroomのファイルである必要があるかも
// イニシャライズ方法をもう少し検討⇒おそらくCSVから取り込む

230704
// マイグレのメソッドは既存のSqliteの実装を変えるように作られているので微妙
// 初回起動でcsvをよみこんで、roomの機能でinsertすれば良い
// 初回起動サービス？csv読み込み、オブジェクトのリスト作成？、insert

230705
- csvの読み込みまで確認出来た
- Mapでヘッダーまで取得できたので、Map ⇒ RoomのEntityへコンバート、一括インサートを考える
- For文の処理を考えていたが、もしかしたらList<Entity>でいけるんじゃねーかな

230705
- Entityのリストを作成しRoomの機能はおそらく実装できたが、insertとのころで詰まっている
- 非同期で実行しないと怒られるらしく、insertとselectを同じ非同期内でやればテストとしては行けるかな？
- 非同期の実装、DaoのSingleton化（Koin）が目標

230706
- 非同期メソッドで苦戦、コルーチンを使い、suspend関数とlaunchかrunBlockingを使う
- 読み込み書き込みは出来た
- 明日は自動採番の戻し方、DB削除?koinでsingletonで作成する
- 以下参考
- https://developer.android.com/training/data-storage/room/async-queries?hl=ja

230707
- Koin失敗、自動採番はDB消せたので解決
- 書き方が悪い気がする
- 参考サイトを変える
- 現参考サイト
- https://qiita.com/Slowhand0309/items/ece245e2c0e3656afe6b#bomb-%E3%83%90%E3%83%83%E3%83%89%E3%83%8E%E3%82%A6%E3%83%8F%E3%82%A6

230711
- KOinの記述が様々すぎてよくわからない
- 共通している部分はプライマリコンストラクタにDIでクラスを渡してモックで依存関係緩和できるよ～んて感じ
- Applicationクラスを継承して作るところも共通しているので、一旦真似してみる
- 参考
- https://logmi.jp/tech/articles/325755
- https://tech.actindi.net/2018/08/17/124255

20230712
- Koin簡単かもしれんが、参考文献が雑であったり、微妙に感じたので仕様するライブラリを変更
- Hiltへ変更、ApplicationクラスやFragment等の理解が浅かったため、明日勉強する
- よき参考を見つけたためいかに記述する
- https://zenn.dev/log_suzaki/articles/f05ef4cccb9e5d
- https://qiita.com/MoriokaReimen/items/750644062b3f5bec18ed
- https://qiita.com/k__yamamoto/items/5354d1f71b9d51c12f20

20230714
- 途中中断
- 以下参考
- https://blog.mokelab.com/53/android_todo9.html

20230717
- Hiltに大苦戦、Module定義までは解ったがActivityでどうやって呼び出すかわからん
- どうしようって感じ
- 呼び出す場所が悪い可能性があるので、Androidの呼び出し方を調べる

20230718
- 寝落ちしてしまった
- 何かを勘違いしている
- 調べ方、失敗の仕方を振り返る

20240719
- 上手くいった、原因はプライマルコンストラクタの省略記述が不明だったこと
- あと疲れて全角スペースを入れていてコンパイルエラーをはいていた、バカでした
- これで画面に取り掛かれる気がする、一旦作業手順を整える

20230724
- ROOMでレコード取得方法変更
- 初回起動の判定方法が不明、SharedPreferencesは古いから使わないほうが良い？
- 初回起動、クイズデータ作成クラスを作成する（今は初回起動にべた書きしているため）

20230726
- compose入れてみた
- 先に進まないのでだれてきた
- viewModelとして答えとその他問題を格納するリポジトリを作る
- その前に画面を作らないと、composeで渡す値が解らないので表示を目標にする
- https://zenn.dev/ko2ic/articles/0a141f9e5a0d39#%E5%AF%BE%E8%B1%A1%E8%AA%AD%E8%80%85

20230727
- composeを使ってみて、イメージはわいてきた
- やはりバックエンドで準備しなければならない箇所は想定通り、問題、正解、正答数ぐらい
- 問題のリストを作る、viewModelで先にdataclassを作ってしまうか、convert?でentity変えられるなら変える
- compose用
- https://developer.android.com/jetpack/compose/tutorial?hl=ja

20230731
- gradleの依存関係諸々をいじって動くようにした
- 具体的な変更点はtargetSdk、compileSdkのver 33 ⇒ 34
- composeのチュートリアルを再開して、目的画面を作る

20230802
- composeの使い方はイメージ出来てきた（作るときはgoogle designのcomponent見る）
- デザインは後回しにして、バックエンド処理を作ってしまう
- 問題作成クラス作成（答え取得 ⇒ 他3択取得 ⇒ 名前リスト作成(データクラス(name, isAnswer)）

20230803
- 思い通りに動いた！クイズは想定通り作成、リストはShuffleメソッドで問題入れ替えられるらしい（表示前加工が良いかも）
- 問題表示画面を作る、タップとかはあと

20230804
- コード書けなかった
- 画面を作成するための知識がいない
- composeの動画を出してる人がいたりするか確認してみる

20240807
- とりあえずべた書きでCardで表示できた
- Themeの書き方？あと背景のCardの書き方
- そのあとはViewModelとFragment分け
- 一旦は背景Card, 効果文字列Card

20230815
- 正解不正解のダイアログは表示した
- あとはステータス管理方法と遷移方法
- 答えの有無と、何問目かも考える

20230817
- 選択肢クリック後にダイアログ表示まで完成
- レイアウトはすぐには間に合わないので、たたき台だけべた書き作成予定
- 問題文表示が目標、ステータス保持方法確認（現在問題数、正答数）

20230820
- 問題文表示出来た
- 次はステータスの保持方法検索する
- レイアウトの参考基盤はこの動画で行う（https://www.youtube.com/watch?v=xc8nAcVvpxY）

20240821
- viewModelを使うっぽい
- 変数で管理していたが、ViewModelクラスで管理し、アクション後に関数でインクリメントとかする
- 多分遷移必要なし、クイズデータと状態管理をViewModelにする？ViewModelの記事見直し
- https://zenn.dev/ko2ic/articles/3601dea3d35013

20230823
- 上手い具合に実現へ方法が調べられない
- viewModelはどちらかというと画面を移動しない状態管理に見える
- 一旦画面遷移の方法を調べて、そこでどのようにパラメータを管理しているか確認する

20230824
- ネットサーフィンで終わる
- 滞っているので、一旦実装したい
- ViewModelでそれっぽいの実装している企業があったため、そこを参考にする
- https://rni-dev.hatenablog.com/entry/2022/07/07/131334

20230826
- 取り合えずViewModel作れた
- HiltでDIした後に、状態管理、次の問題を表示するまでが目標
- 無理なら状態管理出来るかの確認のみでもよい

20230829
- viewModelなんか思ってたのと違いそう
- どちらかといえば状態管理はstateで行うようだ
- stateでの状態管理を理解する、その後にdataclassを作ってみる、Live何とかも見る必要あるかも

20230830
- 深堀していくとviewModelにstateのdataclassを作って管理する方法もある
- stateの思想は結構難しいらしく、初心者では解決できなさそう感
- ViewModelにdataclassを作る方法で試す、DIする

20230831
- 思い通りに動きました
- arryの場所がきたないのでもう少し考える、現在の問題数、レイアウト調整
- setcontextをこんなに連打してよいのかという話

20230904
- stateでクイズの問題も足せれば、再コンポジションでクイズ繰り返せるはず
- 何も考えずに作ったQuizのdataclassが上手くstateに適応出来ない
- 面倒だが、quizのdataclassを撤廃して新規作り直す

20230905
- viewModelを惜しいところまで修正した
- roomのEntityを空にしてStateに持つのが間違っているような気がする
- この辺の原因の切り分けさえすれば、再コンポーズで表示が可能なはず

20230906
- 再コンポーズで更新できた
- uiStateの扱いをもう少し変えられる気がするが、改良は全部できてからにする
- 残作業は現在の問題数表示と、問題終了後のif文処理、そのあとはトップページ画面
- https://qiita.com/yasukotelin/items/4e4d6217b6c3368aa4db

20230907
- とりあえずレイアウトを更新
- 問題回答のレイアウトを考える
- 配色も一緒に行う必要がある

20230913
- 答えと同じモンスターが検索出来てしまうバグ修正
- デザインに関しては、別の勉強が必要そうでどう対応するか検討がつかない
- マテリアルデザインの情報を収集しまくる、サンプルいっぱい見る
- あと問題のタップを禁止する_