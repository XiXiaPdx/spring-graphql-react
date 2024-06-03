# Spring GraphQL + React

This project has a Spring Boot GraphQL backend with a React frontend. 
The quickest way to boot it up is with docker. There is a `start` script in the root of the project.

From the root of the project, execute the start script to build the server and start up both containers.
```dbn-psql
./start 
```

For the Graphiql interface on the server:
```dbn-psql
http://localhost:8080/graphiql
```
For the GraphQL endpoint on the server:
```dbn-psql
http://localhost:8080/graphql
```
For the WIP bare bones React frontend:
```dbn-psql
http://localhost:3000/
```
The server uses H2 embedded database and is preloaded with dummy data. The server integration tests depend on the dummy data. The sort dropdown displays this dummy data as the result. 

## ToDo

This was an interesting coding assignment. There are many improvements to make, especially on the frontend side! 