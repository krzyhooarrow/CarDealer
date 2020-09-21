pkill -9 java

cd ServiceDiscovery
mvn package
cd target
nohup java -jar ServiceDiscovery-1.0-SNAPSHOT.jar &

cd ../..
cd TransactionService
mvn package
cd target
nohup java -jar TransactionService-0.0.1-SNAPSHOT.jar < ./../deploy.properties &

cd ../..
cd SubscriptionService
mvn package
cd target
nohup java -jar SubscriptionService-1.0-SNAPSHOT.jar &

cd ../..
cd ServiceProxy
mvn package
cd target
nohup java -jar  ServiceProxy-1.0-SNAPSHOT.jar &
