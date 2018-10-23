# MultiTenant with Mongo Db Example

<h3>Where use Multi Tenant ?</h3>

If you are having a structure like the following in your mongodb it is pretty bad to handle, since spring-data-mongodb does not support multi tenancy.

        CUSTOMER_DB_ORGANISATION1 #
          - collectionX
          - collectionY
          - collectionZ
        CUSTOMER_DB_ORGANISATION2
          - collectionX
          - collectionY
          - collectionZ
  
the data structure in these collections is the same, every customer does just have its own database in one mongodb instance.

The software designed according to this architecture aims to provide services to many firms or organizations (tenant-tenants) 

using a single software code. The main challenge here is that these softwares can disrupt the virtual processes for each 

organization they serve and allow them to make custom adaptations to these organizations.


The database name is sent in message in each request message. 

The application takes the name of the database and connects to that database.

See the MultiTenancyService class.


<hr/>

<h3>How to run the application ?</h3>

   * Run App
   
      You must have mongoDb installed on the computer.
      
      Application is runing in localhost and port 8888 and default database name is ' default '.
      
      You can change the information from application.properties.

   * Send Request
   
      When register user, you send database name. For Example : (http://localhost:8888/user/add)
      
               * {
	                "userName" :"firstNameUser",
	                "userLastName" : "LastNameUser",
	                "databaseName" : "dbname"
                }
   
      Swagger address : http://localhost:8888/swagger-ui.html
      
      Try the methods by changing the database name.

      Examine the operation of the application.
      

<hr/>

<h3>Used Technologies</h3>

  * Java 8
  * Spring Boot
  * Mongo Db
  * Gradle
  * Lombok
  * Swagger
  * Commons-Lang3
  * Beanutils
  
  <hr/>
  
  <h3>Used Resources</h3>
  
  * https://github.com/PavanMane/SingleMongoInstance-multidatabase-springdata-mongodb
  * https://github.com/Loki-Afro/multi-tenant-spring-mongodb
  
  <hr/>
  
  
