# ONESTY back-end cluster

## How to build

### Requirments
openjdk 21
> brew install openjdk

docker
> brew install docker
### Build
compile java
> ./gradlew clean build

build docker
>docker-compose build

run
>docker-compose up -d

stop
>docker-compose down --remove-orphans

## Endpoints
general service endpoint
> {hostname}:80/{endpoint}
> example: localhost:80/users

swagger-ui:
>http://localhost/openapi/webjars/swagger-ui/index.html
