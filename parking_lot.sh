#!/bin/bash

curr_dir=$(pwd)
dir="${curr_dir}/target"
jar_name="parkinglot-1.0-SNAPSHOT.jar" 
echo $dir
mvn clean install 
java -cp $dir/$jar_name flipcart.App