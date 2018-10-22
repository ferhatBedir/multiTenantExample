# MultiTenant with Mongo Db Example

<h3>Where use Multi Tenant ?</h3>

If you are having a structure like the following in your mongodb it is pretty bad to handle, since spring-data-mongodb does not support multi tenancy.

        CUSTOMER_DB_A #
          - collectionA
          - collectionB
          - collectionC
        CUSTOMER_DB_B #
          - collectionA
          - collectionB
          - collectionC
  
the data structure in these collections is the same, every customer does just have its own database in one mongodb instance.

<hr/>

<h3>How to run the application ?</h3>

   * Run App
   
      You must have mongoDb installed on the computer.
      
      Application is runing in localhost and port 8888 and default database name is ' default '.
      
      You can change the information from application.properties.

   * Send Request
   
      First, a few databases must be added to the application. (http://localhost:8888/database/add)
      
      You should add Users to Databases. (http://localhost:8888/user/add)
   
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
  
  
