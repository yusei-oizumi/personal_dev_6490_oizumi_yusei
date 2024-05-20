-- categories テーブルにデータを挿入
INSERT INTO categories ( name) VALUES
( 'sample');

INSERT INTO users (name, email, password ) VALUES
('大泉裕靖', 'tanaka@aaa.com', 'himitu');

-- blogs テーブルにデータを挿入
INSERT INTO blogs (category_id, word, body,remember) VALUES
( 1, 'Sample', '例',1);

-- books テーブルにデータを挿入
--INSERT INTO books (name) VALUES('sample');
;