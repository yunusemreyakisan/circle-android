# 🔗 Spring API Android
> Android application where you can perform CRUD tests with API that you can run locally.

## ⚙️ API Reference

#### GET Method

```http
  GET /games
```
> Tüm oyunların bir listesini JSON formatında geri döner.

#### POST Method

```http
  POST /add
```
> Tüm oyun listesine oyun eklemenize olanak sağlar.
JSON formatında body bekler.

Örnek body: 
```json
  {
        "name": "Revelation Online",
        "description": "A free-to-play fantasy MMO developed by NetEase and published by My.com.",
        "imageUrl": "https://www.freetogame.com/g/77/thumbnail.jpg",
        "genre": "MMORPG"
  }
```

#### DELETE Method
> Tüm oyun listesinden id'si ile istenilen oyun silinir.
```http
  DELETE /delete/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required** — Oyunun id'si istenir. |


#### PUT Method
> Tüm oyun listesinden id'si ile istenilen oyun güncellenir.

```http
  PUT /update/{id}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required** — Oyunun id'si istenir. |

## ▶️ Demo
https://user-images.githubusercontent.com/116274664/230771276-1505a481-2ae2-4574-bc8a-fd08495cffcd.mov

