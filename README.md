#MenuApp

This is a voting system for deciding where to have lunch.

##Setup and startup

###PostgreSQL Setup

    CREATE USER "menuapp" PASSWORD 'menuapp';
    CREATE DATABASE "menuapp" WITH OWNER "menuapp" ENCODING 'UTF-8' LC_COLLATE 'Russian_Russia.1251' LC_CTYPE 'Russian_Russia.1251';
    GRANT ALL ON DATABASE "menuapp" TO "menuapp";

###Build

    mvn package

###Startup

Launch menuapp-web\target\menuapp-web-0.0.1-SNAPSHOT.war using your app server (I've used tomcat 8.0.30)

##API Usage. Users

API protected with `Basic Authentication`. There are two default users in database created for you: admin/123 и user/123.

###Users

**Register** new user:

    curl -H "Content-Type: application/json" -d '{"username": "newuser", "password": "123"}' http://localhost:8080/users

###Restaurants

**Retrieve** restaurants with their votes:

    curl --user user:123 http://localhost:8080/restaurants

###Menus

**Retrieve** today's menu of certain restaurant:

    curl --user user:123 http://localhost:8080/restaurants/1/menus/today

###Votes

**Vote** for a certain restaurant (restaurant entity with updated votes will be returned):

    curl --user user:123 -X POST http://localhost:8080/restaurants/1/votes

If it is after 11:00 and user has already voted then 422 status will be returned with "It's too late to change your mind" message.

##API Usage. Admins

###Users

**Delete** user:

    $ curl --user admin:123 -X DELETE http://localhost:8080/users/1

###Restaurants

**Create** new restaurant:

    curl --user admin:123 -H "Content-Type: application/json" -d '{"name": "McDonalds"}' http://localhost:8080/restaurants

**Update** restaurant with id=1:

    curl --user admin:123 -X PUT -H "Content-Type: application/json" -d '{"name": "McDonalds Edited"}' http://localhost:8080/restaurants/1

**Delete** restaurant with id=1 (restaurants are not deleted physically, they are marked as deleted):

    curl --user admin:123 -X DELETE http://localhost:8080/restaurants/1

###Menus

**Create** new menu for restaurant with id=1:

    curl --user admin:123 -H "Content-Type: application/json" -d \
    '{"dishes": [{ "name": "First", "price": 12.23 }, { "name": "Second", "price": 22.23 }, { "name": "Third", "price": 32.23 }]}'\
     http://localhost:8080/restaurants/1/menus

**Update** menu with id=1:

    curl --user admin:123 -X PUT -H "Content-Type: application/json" -d \
    '{"dishes": [{"id": 1, "name": "First", "price": 12.23 }, {"id": 3, "name": "Third", "price": 32.23 }, {"name": "New One", "price": 66.23 }]}'\
     http://localhost:8080/restaurants/1/menus/1

**Delete** menu with id=1:

    curl --user admin:123 -X DELETE http://localhost:8080/restaurants/1/menus/1