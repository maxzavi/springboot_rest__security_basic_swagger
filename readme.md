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

## OpenAPIDefinition

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
@OpenAPIDefinition(info = @Info(title = "Product API with security JWT", version = "1.0.0"))

public class Demo01Application {
```

In RestController, use **Tag** annotation

```java
@Tag(name = "Product", description = "Product API")
``

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