## Speaking Clock

	The Speaking Clock project is a simple application that converts the current time into 	words. It allows users to input the time in a 24-hour clock format and receive the 	corresponding time in words.

## Technologies Used

	- Java 8
	- Spring Boot
	- Maven
	- Swagger

## Prerequisites

	- Java 8 or higher installed on your machine
	- Maven included in the project

## Getting Started

Follow the steps below to get started with the Speaking Clock project:

	1. Clone the repository:
   		git clone https://github.com/bikash-hutait/SPEAKING-CLOCK.git
  
	2. Navigate to the project directory:

   		cd speaking-clock


3. Build the project using the Maven:
	
		mvn clean install

4. Run the application:
	
		mvn spring-boot:run or go to speaking-clock\speakingclock\target 
		and use command "java -jar speakingclock-0.0.1-SNAPSHOT.jar"

5. Access the application:

		Open a web browser and go to [http://localhost:8080](http://localhost:8080)

## API Endpoints

The Speaking Clock project provides the following API endpoints:

	- GET /convert: Converts the given time in a 24-hour clock format to words.
  		- Request Parameter:
  		  - time: The time to convert (e.g., "08:34")
  		  
 	- Example: `GET /convert?time=08:34

## Documentation

	The project uses Swagger for API documentation. You can access the Swagger documentation 	by opening the following URL in your web browser: [http://localhost:8080/swagger-ui/index.html]	(http://localhost:8080/swagger-ui/index.html)


## Authors

	-Maheshwar Prasad


## Acknowledgments

	We would like to acknowledge the following resources and libraries that were used in the 	development of the Speaking Clock project:

	- [Spring Boot](https://spring.io/projects/spring-boot)
	- [Swagger](https://swagger.io/)
