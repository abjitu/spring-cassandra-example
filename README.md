# spring cassandra integration example

1) Please create a keyspace in cassandra and mention it in src/main/resources/db-config.properties file along with host information.
2) Generate a war using "gradle clean assemble". Deploy the same in tomcat.
3) start tomcat. Please watch console for any errors.
4) on successful start of tomcat. Go to http://localhost:2020/spring-cassandra-example should return {"status":"SUCCESS","message":"Hello!! Welcome to Spring Cassandra Demo"}
5) GET http://localhost:2020/spring-cassandra-example/users to view existing users
6) GET http://localhost:2020/spring-cassandra-example/user?email={emailId} to view user with this email id
7) POST http://localhost:2020/spring-cassandra-example/user with json payload {"firstName":"test","lastName":"test","email":"test@gmail.com"}

In this example we used @Accessor (com.datastax.driver.mapping.annotations.Accessor), @Query (com.datastax.driver.mapping.annotations.Query), com.datastax.driver.mapping.MappingManager, com.datastax.driver.core.Session. Integrated cassandra driver mapping with spring beans.

