# spring cassandra integration example

1) Please create a keyspace in cassandra and mention it in src/main/resources/db-config.properties file along with host information. <br />
2) Generate a war using "gradle clean assemble". Deploy the same in tomcat. <br />
3) start tomcat. Please watch console for any errors.<br />
4) on successful start of tomcat. Go to http://localhost:8080/spring-cassandra-example should return {"status":"SUCCESS","message":"Hello!! Welcome to Spring Cassandra Demo"}<br />
5) GET http://localhost:8080/spring-cassandra-example/users to view existing users<br />
6) GET http://localhost:8080/spring-cassandra-example/user?email={emailId} to view user with this email id<br />
7) POST http://localhost:8080/spring-cassandra-example/user with json payload {"firstName":"test","lastName":"test","email":"test@gmail.com"}<br />

In this example we used @Accessor (com.datastax.driver.mapping.annotations.Accessor), @Query (com.datastax.driver.mapping.annotations.Query), com.datastax.driver.mapping.MappingManager, com.datastax.driver.core.Session. Integrated cassandra driver mapping with spring beans.<br />

