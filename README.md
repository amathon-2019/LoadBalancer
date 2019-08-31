# Load Balancer

simple load balancer



# Architecture

![image-20190831234222407](/Users/hwan/Library/Application Support/typora-user-images/image-20190831234222407.png)

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

  http://localhost:8080/?mode=leastConnection ["http://localhost:8080/?mode=leastConnection"]



Stress test -> test case

