# Refactory Mobile Task 1
[![API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=21)

Android app to show profile and photos from Jsontypicode and OAuth with Github

SubTask 1:

- [x] halaman foto + nama + batch nya dan ada button masuk

- [x] ketika klik masuk langsung get data dari api faker(pake retrofit)

SubTask 2:

- [x] Login with github

- [x] Detail Photo from List photo

- [x] Data profile from Github

## Results

**SubTask 1**

With Network

![With Network](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1518162648/refactorymobiletask1-network.gif)

No Network

![No Network](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1518163034/refactorymobiletask1-nonetwork.gif)

**SubTask 2**

Login With Github

![Github OAuth](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1518531763/refactorymobiletask1-github-oauth.gif)

Photo Detail

![Photo Detail](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1518532731/refactorymobiletask1-detail-photo.gif)

Logout

![Logout](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1518532947/refactorymobiletask1-logout.gif)

## Info
Target SDK : 26

## Getting Started

* Clone project : `https://github.com/hyuwah/refactory-mobile-task-1.git`
* Sync gradle
* Ganti client_id, client_secret & redirect / callback url di file `LoginActivity.java` dengan yang sudah didaftarkan di OAuth Apps Github
* Rebuild project, run

## 3rd Party Library Used

**CircleImageView** for Round profile image

`compile 'de.hdodenhof:circleimageview:2.2.0'`

**Retrofit** untuk networking / consume API

`compile 'com.squareup.retrofit2:retrofit:2.3.0'`

**GSON converter** untuk convert Json ke POJO

`compile 'com.squareup.retrofit2:converter-gson:2.3.0'`

**Picasso** untuk ambil gambar

`compile 'com.squareup.picasso:picasso:2.5.2'`

**MaterialFancyButton** & **fontawesome icon** untuk button login

`compile 'com.rilixtech:materialfancybuttons:1.8.7'`

`compile 'com.rilixtech:fontawesome-typeface:4.7.0.4'`

## License
MIT