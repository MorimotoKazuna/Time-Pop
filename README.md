# Time-Pop

"Time-Pop"は従来のタイムカード・タイムレコーダーに代わる就労継続支援A型・就労移行支援事業を行われてい会社にて使用されることを想定した出退勤管理アプリです

# DEMO

出退勤時間の記録をデータベースへ保存することにより、指定した任意の期間中の実働時間が一見して分かり、毎月の出勤簿作成の時間短縮に役立ちます

# Features

事業所利用者様の操作は
1.ご自身のお名前をクリック
2."出勤"または"退勤"ボタンをクリック
という1度に2ステップでの簡単なクリック入力だけで完結することが特徴です

# Requirement

* java 23.0.2 
* Eclipce2025
* H2 Database

# Installation

Requirementで列挙したライブラリなどのインストール方法を説明する

```bash
pip install huga_package
```

# Usage

DEMOの実行方法など、"quux"の基本的な使い方を説明する

```bash
git clone https://github.com/quux/~
cd examples
python demo.py
```

# Note
初回アプリケーション起動・実行前に、データベースへ下記のCREATE文2件、INSERT文1件の実行をお願いします。

CREATE TABLE USERS(
id INT PRIMARY KEY UNIQUE_ID UNIQUE (ID);,
name VARCHAR(100) NOT NULL,
name_furigana VARCHAR(100) DEFAULT '不明' NOT NULL
email VARCHAR(255),
password VARCHAR(255),
role VARCHAR(20) NOT NULL CHECK (role IN ('admin', 'user')),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
state VARCHAR(20) NOT NULL DEFAULT 'active' CHECK (state IN ('active', 'disable'))
);

CREATE TABLE ATTENDANCE(
id IDENTITY PRIMARY KEY,
user_id INT NOT NULL,
date DATE NOT NULL,
clock_in TIME,
clock_out TIME,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
UNIQUE (user_id, date)
);

INSERT INTO USERS (ID, NAME, EMAIL, PASSWORD, ROLE, CREATED_AT) VALUES (0000, '管理者', 'admin@example.com', '0000', 'admin', '2025-5-22')
※ROLE以外の値は変更頂いても問題ございませんが、初回ログイン時にデータベースへ登録したEMAILおよびPASSWORDが必要となります。
# Author

作成情報を列挙する

* 作成者
* 所属
* E-mail

# License
ライセンスを明示する

"quux" is under [MIT license](https://quux/◆◆◆◆◆◆◆/MIT_License).

社内向けなら社外秘であることを明示してる

"quux" is Confidential.
