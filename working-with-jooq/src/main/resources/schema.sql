DROP TABLE IF EXISTS COMMENTS;
DROP TABLE IF EXISTS POSTS; 

CREATE TABLE POSTS(ID int(11) NOT NULL AUTO_INCREMENT
	,TITLE VARCHAR(200) NOT NULL
	,CONTENT LONGTEXT DEFAULT NULL
	,CREATED_ON DATETIME DEFAULT NULL
	,PRIMARY KEY (ID)
);

CREATE TABLE COMMENTS(ID INT(11) NOT NULL AUTO_INCREMENT
	,POST_ID INT(11) NOT NULL,NAME VARCHAR(200) NOT NULL
	,EMAIL VARCHAR(200) NOT NULL
	,CONTENT LONGTEXT DEFAULT NULL
	,CREATED_ON DATETIME DEFAULT NULL,PRIMARY KEY (ID)
	,FOREIGN KEY (POST_ID) REFERENCES POSTS(ID)
);