# Refactory Mobile Task 1
[![API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=21)

Android app to show profile and photos from Jsontypicode

Task:
1. halaman foto + nama + batch nya dan ada button masuk
2. ketika klik masuk langsung get data dari api faker(pake retrofit)

## Results

**With Network**

![With Network](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1518162648/refactorymobiletask1-network.gif)

**No Network**

![No Network](https://res.cloudinary.com/hyuwah-github-io/image/upload/v1518163034/refactorymobiletask1-nonetwork.gif)


## Info
Target SDK : 26

## Getting Started

* Clone project : `https://github.com/hyuwah/refactory-mobile-task-1.git`
* Sync gradle, rebuild project, run

## 3rd Party Library Used

**CircleImageView** for Round profile image

`compile 'de.hdodenhof:circleimageview:2.2.0'`

**Retrofit** untuk networking / consume API

`compile 'com.squareup.retrofit2:retrofit:2.3.0'`

**GSON converter** untuk convert Json ke POJO

`compile 'com.squareup.retrofit2:converter-gson:2.3.0'`

**Picasso** untuk ambil gambar

`compile 'com.squareup.picasso:picasso:2.5.2'`

## License
MIT