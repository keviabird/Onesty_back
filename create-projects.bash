#!/usr/bin/env bash

mkdir services
cd services

spring init \
--boot-version=3.3.0 \
--build=gradle \
--java-version=21 \
--packaging=jar \
--name=file-service \
--package-name=com.onesty.services.file \
--groupId=com.onesty.services.file \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
--type=gradle-project \
file-service

spring init \
--boot-version=3.3.0 \
--build=gradle \
--java-version=21 \
--packaging=jar \
--name=user-service \
--package-name=com.onesty.services.user \
--groupId=com.onesty.services.user \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
--type=gradle-project \
user-service

spring init \
--boot-version=3.3.0 \
--build=gradle \
--java-version=21 \
--packaging=jar \
--name=user-service \
--package-name=com.onesty.services.user \
--groupId=com.onesty.services.user \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
--type=gradle-project \
search-service

cd ..