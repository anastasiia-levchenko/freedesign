## FREE DESIGN PROJECT

デモビデオリンク/Demo video link：https://youtu.be/Cw4Q3EyOJjI

--------
日本語
--------

JDK バーション： 17.0.11

## 説明書

データベースに使用するツール: mySQL Workbench

実行：

1. 'freedesign'データベーススキーマを作る。
2. データベースユーザーを作った上で、最適な権利を与える（ユーザーの認証情報は'application.properties'ファイルにある）。
3. Githubから、プロジェクトのプルをし、プロジェクトの実行し、'src/main/resources/sql/testartworkdata'にあるデータをインポートする。
4. 'public/images backup'にあるイメージを'public/images'に手動に移動する。
5. プロジェクトを実行してから、'http://localhost:8080/' へ

下記の認証情報を使う：

ユーザーネーム：testUser
パスワード：SecurePassword

================================

## 使った技術：

- Spring framework
- Spring Boot
- Spring Security
- Spring MVC
- Spring Data JPA (Jakarta EE)
- Hibernate
- Lombok
- Thymeleaf
- mySQL JDBC Driver
- Maven
- Spring Boot DevTools
- Bootstrap
  Tools:
- JUnit
- Mockito

## ツール:

IDE: IntelliJ Idea
SQL: MySQL Workbench

## デザインパターン:

Factory, Strategy

## プロジェクトのアーキテクチャ:

コントローラ -> サービス -> DAO (リポジトリ) -> データベース

--------
ENGLISH
--------

JDK version: 17.0.11

## Instructions

Tool used for DB: mySQL Workbench

Actions:

1) DB schema 'freedesign' should be created
2) DB User (creds in properties files) should be created as well and privileges given.
3) After pulling the project, please run it and import all the sql data placed in 'src/main/resources/sql/testartworkdata'
4) Move the images from 'public/images backup' folder into 'public/images' manually
5) After launching the project, go to 'http://localhost:8080/'.

Use these test user creds to log in:

username: testUser
password: SecurePassword
================================

## Technologies used:

- Spring framework
- Spring Boot
- Spring Security
- Spring MVC
- Spring Data JPA (Jakarta EE)
- Hibernate
- Lombok
- Thymeleaf
- mySQL JDBC Driver
- Maven
- Spring Boot DevTools
- Bootstrap
  Tools:
- JUnit
- Mockito

## Tools:

IDE: IntelliJ Idea
SQL: MySQL Workbench

## Design Patterns:

Factory, Strategy

## Project Architecture:

Controller -> Service -> DAO (Repository) -> DB
