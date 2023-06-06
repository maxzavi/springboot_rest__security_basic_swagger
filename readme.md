# Spring Boot 3.0 Api - Security - with OpenApi Swagger 

Create spring boot maven project: spring boot 3.0.6, java 17: with dependencies:

- Spring Web
- Lombok
- Spring Security
- Sring Boot DevTools

Using Spring security, username: user, password is generated in deploy.

For set username and password, in application.yml

```yml
spring:
  security:
    user:
      name: user
      password: clave
```

## Springdoc OpenAPI

Add dependency maven, by spring boot 3, use https://springdoc.org/v2/

```xml
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.1.0</version>
		</dependency>
```

view in http://localhost:8080/swagger-ui/index.html

In main class, use **OpenAPIDefinition** annotation

```java
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Product API with security", version = "1.0.0"))

public class Demo01Application {
```

In RestController, use **Tag** annotation

```java
@Tag(name = "Product", description = "Product API")
```

Add configuration SecurityConfig class, add beans: csrf: read and write access

```java
@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/swagger-ui/**","/javainuse-openapi/**", "/v3/api-docs/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .and()
        .build();
    }
 }
```

Create schema "demoapi" and add annotation in main

```java
@SecurityScheme(name = "demoapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)

public class Demo01Application {
```

add schema in RestControllers

```java
@SecurityRequirement(name = "demoapi")
```

Now, button is visible

## Docker

Create dockerfile, using image base **eclipse-temurin:17-jre-alpine**

```docker
FROM --platform=linux/x86_64 eclipse-temurin:17-jre-alpine
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/demo01-0.0.1-SNAPSHOT.jar demo01.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar demo01.jar
```

Compile spring boot project using maven

```sh
 mvn clean package
```

docker build -t mzavaletav/springboot-security-basic-demo:1 .

docker push  mzavaletav/springboot-security-basic-demo:1

## Demo full

Create namespace **springboot-demo**, if exists, remove:

Check:

```sh
kubectl get ns
```
Exits? remove:

```sh
kubectl delete ns springboot-demo
```

Create namespaces

```sh
 kubectl apply -f k8s/01-namespace.yml
```

Set default namespace

```sh
kubectl config set-context --current --namespace=springboot-demo
```
Create service:
```sh
kubectl apply -f k8s/02-deployment.yml 
```
Get port number type nodeport:

```cmd
kubectl get services
```
Using port number in PORT_NUMBER, access 

http://localhost:{PORT_NUMBER}/swagger-ui/index.html

Credentials:

user/p4$$w0rd


Create request, copying curl in url or using Import


