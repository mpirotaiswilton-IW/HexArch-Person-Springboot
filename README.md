# Solace-Person-SpringBoot
 A set of microservices built with Event-Driven Architecture and communicating with a Solace event broker

## Summary

[Solace-Person-SpringBoot](#solace-person-springboot)
* [Summary](#summary)
* [Setup and Pre-requisites](#setup-and-pre-requisites)
* [Running the Microservice](#running-the-microservice)
    * [Cleaning up Exited Containers](#cleaning-up-exited-containers)
* [Verifying our solution](#verifying-our-solution)
    * [Using the API with Postman](#using-the-api-with-postman)
    * [Viewing our Database using pgAdmin](#viewing-our-database-using-pgadmin)

## Setup and Pre-requisites

1. If not already installed:

- Install the latest version of OpenJDK 17 on your device (The following page has a complete catalogue of OpenJDK downloads: [https://www.openlogic.com/openjdk-downloads](https://www.openlogic.com/openjdk-downloads))
- Install Docker on your device (you can use the following link for a guide: [https://docs.docker.com/get-docker/](https://docs.docker.com/get-docker/))
- Install Postman on your device

>If you are using Docker Desktop for Windows, make sure to use version **4.26.1** or lower. 
>
>The solution will not deploy with `docker-compose` if you are using version **4.27.1**. This is a known issue with this version of Docker Desktop for Windows.

2. Clone this repository or download the .zip file from GitHub (extract the downloaded zip file )

## Running the Microservices

1. Using a Command Line Interface of your choosing, change directory to the downloaded/cloned repository


2. To build both of our microservice, you will need to build a .jar file for each:  
    1. Build the Publisher
        
        Change directory to 
        `<Path to repo>/Solace-Person-Springboot/person_publisher`
        and run the command below.

        ```
        <# Linux/MacOs #>
        ./mvnw clean package

        <# Windows #>
        .\mvnw clean package
        ```

    2. Build the Subscriber
    
        Change directory to 
        `<Path to repo>/Solace-Person-Springboot/person_subscriber`
        then run the same command as in the previous step.

3. Once both builds are successful, run this command to deploy them along with an event broker:

    ```
    docker-compose up -d --build
    ```

4. 6 docker containers should now be running:
    * `publisher-microservice`: where a spring-boot api image, built using a Dockerfile, is containerized. This container is responsible for sending events contaning personal information to the event broker with an exposed API.
    * `subscriber-microservice`: where a spring-boot api image, built using a Dockerfile, is containerized. This container is responsible for consuming events contaning personal information from the event broker to store them as entries into a database.
    * `db`: where a Postgres database is containerized and used by the `person-subscriber` application.
    * `pgadmin`: where a pgAdmin container is used to access the containerized Postgres database.
    * `solace`: where a Solace event broker is containerized.
    * `solace-init`: where a python script runs to set up our `solace` container with all the queues and subscribed topics needed for our microservices to communicate.

5. After a few minutes, the repository's set of microservice will be ready for use

#### Cleaning up Exited Containers

After a successful deployment, there should be a container which is no longer running: `solace-init`. This containers has run to set-up our Solace event-broker. To remove this exited container, run the following command in a seperate CLI window: 
```
docker container prune -f
```
You can verify that 5 containers are running by using the following command in your CLI:
```
docker ps
```

## Verifying our Solution

To verify our solution, we need to send a request to the API exposed by the `publisher-microservice`. Then, we can verify that our request has been published and received as an event by checking the database container with pgAdmin.

### Using the API with Postman

Using Postman:

1. Select `Import` on the `My Workspace` left-hand side window, then import the [HexArch-Person.postman_collection.json](https://github.com/mpirotaiswilton-IW/HexArch-Person-Springboot/blob/main/HexArch-Person.postman_collection.json)

2. Select the `Send Person` Post request. In the `Body` tab, there is a json object with 4 fields that would looks like this:
    ```json
    {
        "firstName":"Brad",
        "lastName":"Default",
        "age": 34,
        "sex": "Male"
    }
    ``` 

    You can change these fields as you see fit.

3. Send the request. You should receive a 200 OK response. 

### Viewing our Database using pgAdmin

After sending a successful request with Postman, the `subscriber-microservice` will have saved a new Person in the postgres database container.

Using a web browser of your choosing, head to <http://localhost:5050/>. You should see the pgAdmin login page. To verify both databases, make sure to sign in using the following credentials:

* Email : `admin@admin.com`
* Password : `admin`

You can change these credentials under the following `pgadmin` container environment variables in the "[docker-compose.yaml](https://github.com/mpirotaiswilton-IW/Solace-Person-Springboot/blob/main/docker-compose.yaml)" file: 

* `PGADMIN_DEFAULT_EMAIL`
* `PGADMIN_DEFAULT_PASSWORD`

After successfully logging into pgAdmin, click on `Add New Server` on the Dashboard Home Page:
1. In the General tab, name your server as you see fit
2. Navigate to the Connection tab
3. For the `host name/address`, use the name of the Postgres container `db`
4. Make sure the port field is `5432`
5. the `Username` field is defined by the `POSTGRES_USER` environment variable for the `db` container in the `docker-compose.yaml` file
6. the `Password` field is defined by the `POSTGRES_PASSWORD` environment variable for the `db` container in the `docker-compose.yaml` file
7. Click save and, in the Object explorer, under Servers you should see your newly saved server `db`. This is the database `subscriber-microservice` uses.
8. In the Object Explorer, under `db > Databases` select `admin`
9. In pgAdmin menu bar, select `Tools > Query Tool`. This will load a new window with a blank page to write an SQL query to our database.
10. Run the following query in the query tool: 
    ```
    SELECT * FROM people_table
    ```
11. This query should return 1 result, with the fields the same as the request body contents.
