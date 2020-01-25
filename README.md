# FOODBABY
An Application by fluffy-bear.

##### Project members
* Johanna Reiting (jr087)
* Erik Großkopf (eg034)
* Leonhard Lie (ll040)
* Manuela Müller (mm243)

## Abstract
FOODBABY is a recipe recommender application for people who don’t know what to eat, is it for breakfast, lunch or dinner. Because most recipe applications recommend a ton of recipes when looking for a suitable one, FOODBABY is designed to make the decision of what to eat as easy as possible. One single recipe is all the user gets recommended. Additionally, the user can filter the search according to preparation time, diet and type of meal. Furthermore the recipe suggestion can be adjusted by specifying ingredients. 
The frontend technologies used for FOODBABY are React.js, Babel and Webpack. Sass in combination with material UI provide good usability and a fresh look. The Spring Boot Backend makes use of a mysql database for data storage. For running the application in different environments, FOODBABY is using a Docker setup. Every part of the application is wrapped in a separate Docker Container and the containers are connected through an Nginx reverse proxy that serves as the intermediate between the containers. 
The quality of the application is ensured by several UI and unit tests. Component and snapshot tests, which were created with the help of Jest and Enzyme, ensure a functioning frontend. A good coverage of the unit tests guarantees a functioning backend implementation.


# Mobile web application for browsing receipts
## Building the application
Fluffy-Bear is a single-page application which is deployed via Docker. These instructions will cover requirements and usage for running all services in docker containers.
Pull the latest commit of the master branch, navigate to the root folder of the project and run 'docker-compose up'.
This will create and start images/containers for:
1. MYSQL database
2. Spring Boot application
3. React frontend Nginx server
4. Nginx reverse-proxy

## Frontend
Fluffy-bear's frontend is written with the help of the JavaScript library ReactJS as well as the React Library Material UI.
The frontend is deployed via Docker. Instructions on how to deploy the service can be found below.

### Structure
Every frontend-relevant file is located in the directiory 'frontend'. 
All component files are .jsx files and are located under 'frontend > src'.
The frontend-team has decided to separate every class and function into a respective file. This way components are ensured to be reusable throughout the entire project.

Styling for the components are done using Sass. The style sheet files have the same name as the component they are designed for.
For example the JavaScript code for the Header component is located in it's designated file 'Header.jsx'. The corresponding style sheet is named 'Header.scss'.

Tests can be found under '__test__'. The frontend is snapshot-tested. For this purpose the files containing the tests are named (component).spec.jsx.
For the creating of the tests the testing frameworks Jest and Enzyme are used.
When running a snapshot test for the first time, the corresponding snapshot is automatically created and stored in the folder '__snapshots__'.

Any images needed for the application are stored in the folder called 'images'.

### Navigation
The main class of the application is called 'App.jsx'.
Eventhough you can 'navigate' to details, fluffy-bear still is a single-page application. What changes is the content displayed between the Header and Footer.
This content is set in the render() method inside App.jsx. The state has a variable 'pageNumber', which is set to 0 initially. 
As soon as the user triggers "goToDetails()" by clicking on the image, the pageNumber is set to 1. 
If the user clicks on the 'back' button, the method 'handleGoBack()' is triggered, which sets pageNumber to 0.
If the pageNumber is 0 the Filter, Recommender and AddRecipe components are rendered, if the pageNumber is 1 the Details Component is rendered.

## Backend
The fluffy-bear backend is deployed as spring boot application with mysql database access - both running in docker containers.

#### Usage
##### Requesting filtered recipes
`GET localhost/api/recipes?breakfast=false&lunch=false&dinner=false&vegetarian=false&vegan=false&longTime=false&shortTime=false&ingredients=Zucker,Mehl`

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
