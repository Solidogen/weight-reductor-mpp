#!/bin/bash
cd ../..
psql -U postgres -d empty -c "drop database weightreductorunittests;"
psql -U postgres -d empty -c "create database weightreductorunittests;"
./gradlew backend:test
./gradlew backend:run