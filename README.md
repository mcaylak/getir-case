# ReadingIsGood Project For Getir


ReadingIsGood is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day. That is why stock consistency is the first priority for their
vision operations.

## Used technologies

- Spring Boot 
- Postgresql
- Docker
- Spring Security
- Lombok

## Database Design
- [**Customer Table**]
  I record customer data in this table.
- [**Order Table**]
  Here we keep all the data purchased by the user.
  Established a one-to-one relation with the **address** , **book** and **customer** table
- [**Order Entry Table**]
  Each entry that is ordered is kept in this table. Has one to many relation with order table
- [**Address Table**]
  Address table holds the address information of each order.
- [**Book Table**]
  The information of all the books is kept here, it has no relation with any table.
  
## Response Types 
- All endpoints have a single response type.
- Depending on the response type of the relevant endpoint, the model in the data may change.

```JSON
{
    "code": 200,
    "data": [
        {
        }
    ],
    "message": null,
    "timeStamp": 1665324335570
}
```


## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.8.1](https://maven.apache.org)
- [Docker](https://www.docker.com/products/docker-desktop/)
## Running the application locally

Run the following in order.

```shell
mvn clean package
```

```shell
docker-compose up --build
```

After completing the above steps, you can use the postman collection below when starting the project.

https://www.getpostman.com/collections/c9b8cce70ca499ba5c0a

- First, we perform user registration with customer create.
- Then we login and get **jwt token**.
- Since there is no book information in the system, let's add a **book** and then we can complete it by ordering.


## Created By Muhammed Recep Caylak


