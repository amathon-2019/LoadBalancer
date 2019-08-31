# Load Balancer

simple load balancer



# Architecture

![architecture](https://user-images.githubusercontent.com/21194094/64066694-ff5cbe80-cc57-11e9-8a36-6ea30c9bae47.png)

# Mode

- Round Robin
- Least Connection



# Config

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=MYSQL
    username: admin
    password: admin
    validationQuery: SELECT 1
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        web-allow-others: true

loadBalancer:
  mode: leastConnection
```



# Test

checkout features/view

- Round Robin Test

  http://localhost:8080?mode=roundRobin

- Least Connection Test

  http://localhost:8080/?mode=leastConnection



Stress test -> test case

