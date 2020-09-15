pkill -9 java

cd ServiceDiscovery
mvn package
cd target
nohup java -jar ServiceDiscovery-1.0-SNAPSHOT.jar &

cd ..
cd TransactionService
mvn package
cd target
nohup spring-boot-0.0.1-SNAPSHOT.jar &

cd ..
cd CarService
mvn package
cd target
nohup CarService-1.0-SNAPSHOT.jar &

cd ..
cd ServiceProxy
mvn package
cd target
nohup java -jar  ServiceProxy-1.0-SNAPSHOT.jar &
