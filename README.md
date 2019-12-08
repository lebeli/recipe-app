# Mobile web application for browsing receipts
[UML-SQL-Diagramm](https://drive.google.com/file/d/1NH8oqzhDk3qjIZlYgTxwlgJiOX7kkEN6/view?usp=sharing)

## Backend

### Getting Started

The fluffy-bear backend is deployed as spring boot application with mysql database access - both running in docker containers. These instructions will cover requirements and usage for running both services in docker containers.

#### Prerequisites

Install:
* [JDK 13.0.1](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html)
* [Maven](https://maven.apache.org/download.cgi)
* Docker ([Windows](https://download.docker.com/win/stable/Docker%20for%20Windows%20Installer.exe) / [Mac](https://download.docker.com/mac/stable/Docker.dmg))

#### Set up

The following steps refers to the fluffy-bear backend project root folder.

1. Build the application
```
mvn clean install
```
2. Build application and database images and run services in docker containers
```
docker-compose up
```

#### Usage

Recipes have following JSON format:
```
{
	"name":"Spaghetti",
	"description":"Tasty noodle recipe.",
	"yield":2,"ingredients":[{"name":"Noodles","amount":150},{"name":"Sauce","amount":85}],
	"instructions":[
		{"instruction":"Boil noodles for 9-11 minutes."},
		{"instruction":"Cook vegetables, add  chopped tomatoes (with juice) and tomato paste. Let it simmer for 15 minutes."}
		]
}
```

1. Add a recipe using `POST localhost:8080/recipes/add`

2. Retrieve a list of all recipes using `GET localhost:8080/recipes`