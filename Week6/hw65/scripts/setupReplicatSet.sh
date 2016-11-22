#!/bin/bash

echo "5";
sleep 1;
echo "4";
sleep 1;
echo "3";
sleep 1;
echo "2";
sleep 1;
echo "1";
sleep 1;
echo "0";
sleep 1;

mongo --host mongodb_secondary_1 --port 27018 < /scripts/init_replica.js

mongod