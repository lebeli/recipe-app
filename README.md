# Mobile web application for browsing receipts
## Backend
### Getting Started
The fluffy-bear backend is deployed as spring boot application with mysql database access - both running in docker containers. These instructions will cover requirements and usage for running both services in docker containers.

#### Deploying the application
Inside the root project folder, simply execute "docker-compose up".
This will create and start images/containers for:
1. MYSQL database
2. Spring Boot application
3. React frontend dev server

#### Usage
##### Requesting filtered recipes
`GET localhost/api/recipes`

Inside body specify filtering json object:
```json
{
    "breakfast": true,
    "lunch": true,
    "dinner": true,
    "vegetarian": false,
    "vegan": false,
    "longTime": true,
    "shortTime": true
}
```

##### Adding a recipe
`POST localhost/api/recipes/add`

Inside body specify recipe json object (You can get a custom image url with adding-image-request in advance, shown next):
```json
{
    "name": "Butter sandwich",
    "image": "example.com/images/sandwich",
    "totalTime": "5",
    "category": "breakfast",
    "vegetarian": true,
    "vegan": false,
    "ingredients": [
      {
        "name": "toast",
        "typeAmount": "1"
      },
      {
        "name": "butter",
        "typeAmount": "20 g"
      }
    ],
    "instructions": [
      "Put butter on your toast.",
      "Eat the toast."
    ]
}
```

##### Requesting an image
`GET localhost/api/images/1.jpg`

##### Adding an image
`POST localhost/api/images/add`

Send image as form data in body.
Key must be "file". Value the image. (Max file size: 1024KB)
The response contains the image url:
```json
{"url": "localhost:8080/images/321c860f-3d1d-4c22-b313-5f7d989d4e58"}
```

##### Requesting all ingredients
`GET localhost/api/ingredients`

##### Adding an ingredients
`POST localhost/api/ingredients/adding-image-request

Inside body specify ingredient json object:
```json
{"name": "salt"}
```

