-- categories テーブルにデータを挿入
INSERT INTO categories (id, name) VALUES
(1, '英単語');
INSERT INTO categories (id, name) VALUES
(2, '基本情報');


-- users テーブルにデータを挿入
INSERT INTO users (id, email, name, password) VALUES
(1, 'tanaka@aaa.com', 'tanaka', 'himitu');

-- blogs テーブルにデータを挿入
INSERT INTO blogs (id, category_id, user_id, word, body,remember) VALUES
(1, 1, 1, 'Sample', '例',1);
