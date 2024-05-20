-- 各種テーブル削除
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS blogs;

-- categories（カテゴリー）
CREATE TABLE categories (
id SERIAL PRIMARY KEY,
name TEXT
);

-- users（ユーザー）
CREATE TABLE users (
id SERIAL PRIMARY KEY,
email TEXT,
name TEXT,
password TEXT
);

-- blogs（記事）
CREATE TABLE blogs (
id SERIAL PRIMARY KEY,
category_id INTEGER,
word TEXT,
body TEXT,
remember INTEGER
);
