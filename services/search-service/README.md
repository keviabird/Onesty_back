# Search and March service

## Requirements

* MongoDB
* Apache Kafka

## Load database dump to MongoDB

* Install MongoDB database tools according to the [instruction](https://www.mongodb.com/docs/database-tools/installation/installation-macos/)
* Load dump using command

```shell
mongorestore -h="localhost:27017" --archive=search_dump
```