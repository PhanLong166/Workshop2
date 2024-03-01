CREATE DATABASE WS2_Database
GO

USE WS2_Database

CREATE TABLE tbl_User(
	username nvarchar(50),
	password int,
	fullname nvarchar(50),
	role int
)

INSERT INTO tbl_User VALUES (N'admin',1,N'Shirogane Toru',0)
INSERT INTO tbl_User VALUES (N'user',2,N'Long Phan',1)

DELETE FROM tbl_User WHERE username = 'admin'

SELECT * FROM tbl_User

CREATE TABLE tbl_MusicLibrary(
	musicID int primary key identity,
	musicName nvarchar(100),
	artist nvarchar(50),
	src nvarchar(200)
)

INSERT INTO tbl_MusicLibrary VALUES (N'Idol', N'Yoasobi', N'music/Idol.mp3')
INSERT INTO tbl_MusicLibrary VALUES (N'No1', N'Kobasolo', N'music/No1.mp3') 
INSERT INTO tbl_MusicLibrary VALUES (N'Juunana sai', N'Mona', N'music/Juunana sai.mp3') 
INSERT INTO tbl_MusicLibrary VALUES (N'Fansa', N'Mona', N'music/Fansa.mp3') 
INSERT INTO tbl_MusicLibrary VALUES (N'Kawaikute gomen', N'Saori Hayami', N'music/Kawaikute gomen.mp3') 

SELECT * FROM tbl_MusicLibrary
SELECT musicID, musicName, artist, src FROM tbl_MusicLibrary WHERE [musicName] LIKE '%%' OR [artist] LIKE '%%'

SELECT * FROM tbl_MusicLibrary WHERE musicId = 1

CREATE TABLE tbl_Inventory(
	productID nvarchar(10) primary key,
	productName nvarchar(50),
	type nvarchar(20),
	quantity int,
	price int
)

INSERT INTO tbl_Inventory VALUES (N'M001',N'Milk',N'Drink',100,10000)
INSERT INTO tbl_Inventory VALUES (N'B001',N'Bread',N'Food',10,3000)
INSERT INTO tbl_Inventory VALUES (N'Y001',N'Yaourt',N'Drink',2,8000)
INSERT INTO tbl_Inventory VALUES (N'K001',N'Knife',N'Tools',50,30000)
INSERT INTO tbl_Inventory VALUES (N'W001',N'Water',N'Drink',7,3000)

SELECT * FROM tbl_Inventory
