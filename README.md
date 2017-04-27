# Server
Cloud controller written in java & spring using Gradle

## Installation

### Eclipse or Spring (under windows or linux)[Download page](https://www.eclipse.org/downloads/)
- install eclipse neon or STS (Spring Tool Suite)
- go to help/markeplace & install Buildship Gradle then Restart Eclipse
- go to help/markeplace & Spring IDE then Restart Eclipse (if eclipse neon installed)
- go to Window/Perspective/Open Perspective/Other.../Spring then click OK
- Open the server/ folder into eclipse
- Or Follow Usage to import the project (development environment)

### Gradle (in commande line under linux)
- install gradle 3.4.1 : (Not by apt-get)
	1. curl -s "https://get.sdkman.io" | bash
	2. sudo apt install unzip
	3. source "$HOME/.sdkman/bin/sdkman-init.sh"
	4. sdk install gradle 3.4.1
Done !

## Usage 

### Eclipse or Spring (under windows or linux)
Equivalent of gradle build :
- File / import / Gradle / Existing  Gradle Project / Next / Add root directory server / Finish
- To build a new version in build.gradle change 
```
jar {
    baseName = 'cherry-rest-service'
    version =  '0.1.2'
}
```
- And in Window / Show View / Gradle / Gradle Tasks
- In Gradle Task View / server / build / right click on build / Run Gralde Tasks
- And the new version is in build/libs
To run the project : 
- right click on project 
- run as java application 
- choose Cherry.application(NOT tomcat !)

Be careful to stop the server to avoid address already in use
Done !!

### Run in command prompt under windows without Eclipse or STS
- Install bash for windows 10 [Tutorial page](http://www.windowsfun.fr/tutoriel/2016/04/10931_tuto-windows-10-comment-installer-linux-bash)
- cd to server/
- java -jar build/libs/cherry-rest-service-X.X.X.jar

### Gradle (Creating a runnable jar then running it)(under linux)

- cd to server/
- gradle clean
- gradle build
- java -jar build/libs/cherry-rest-service-X.X.X.jar
- Ctrl+C to stop
Done 

- Open your favorite web browser ( not Internet Explorer plz... )
- go to localhost:8080

### Add mongodb to spring project with gradle [Tutorial Page](https://examples.javacodegeeks.com/enterprise-java/spring/spring-data-mongodb-example/)

##### Launch MongoDB

- [Install MongoDB](https://www.mongodb.com/download-center) in C:\MongoDB 
- Create a directory C:\MongoDB\data
- Launch MongoDB 
```
"C:\MongoDB\Server\3.4\bin\mongod.exe" --dbpath "C:\MongoDB\data"
```

###### Gradle

- Add in in buildscript / depedencies
```
classpath("org.springframework.boot:spring-boot-starter-data-mongodb:1.5.2.RELEASE")
```
The 1.5.2 version must maybe be the same version as the gradle plugin or maybe not, don't know, don't care.
```
classpath("org.springframework.boot:spring-boot-gradle-plugin:1.5.2.RELEASE")
```


- Add in depedencies
```	
compile("org.springframework.boot:spring-boot-starter-data-mongodb")
testCompile("org.springframework.boot:spring-boot-starter-test")
```

##### srping-config.xml

- Right click on the server project / New / Other / Spring / Spring Bean Configuration File
- Add this lines in this file 
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:mongo="http://www.springframework.org/schema/data/mongo"
          xsi:schemaLocation=
          "http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context-3.0.xsd
          http://www.springframework.org/schema/data/mongo
          http://www.springframework.org/schema/data/mongo/spring-mongo-1.2.xsd
          http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
          http://www.springframework.org/schema/mvc 
          http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd">
       
    <!-- I think this line is not necessary because we don't do any scan -->
    <context:component-scan base-package="cherry.apphandlers.service" />
    
    <!-- Configure the Mongo Server -->    
     <bean id="mongo" class="org.springframework.data.mongodb.core.MongoFactoryBean">
		<property name="host" value="localhost" />
		<property name="port" value="27017"/>
		<!-- Same ip and port as mongobd running app -->
	 </bean>
	  <bean id="mongoTemplate" class="org.springframework.data.mongodb.core.MongoTemplate">
	    <constructor-arg ref="mongo"/>
	    <constructor-arg name="databaseName" value="your-database-name"/>
	  </bean>
    <mongo:repositories base-package="package-where-user.java-&-userCollection.java-is"></mongo:repositories>
</beans>
```

##### MongoDB Syntaxe to add to the Application.java

- Just add 
```
@SpringBootApplication
public class Application {
...
}

```


##### MongoDB Syntaxe to add to User.java

- Add 
```
@Document(collection="user")
public class User {
	@Id
	protected Long userId;
	@PersistenceConstructor
    public User(Long userId, ...){
    	...
    }
}
```

##### MongoDB Syntaxe to add to UserCollection.java

- Add
```
@Component
public interface UserCollection extends CrudRepository<User, Long> {
//public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	@Query("{'lastname' : ?0}")
	public Iterable<User> searchByLastName(String userLastName);

	//Any type of search in the dataBase can be implements here

}
```

##### MongoDB Syntaxe usage

```
ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("file:spring-conf.xml");
UserCollection userCollection = context.getBean(UserCollection.class);
			
Iterable<User> userList = userCollection.findAll();
String out = "Users List :\n";
for (User u : userList){
        out = out + u + "\n";
}
context.close();
System.out.println(out);
```


That's it !!

### END POINTS

COMMING SOON !!! 