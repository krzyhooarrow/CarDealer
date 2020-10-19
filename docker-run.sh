sudo docker stop $(sudo docker ps -aq)

sudo docker run --name zoo --network host wurstmeister/zookeeper:latest
sudo docker run --network host wurstmeister/kafka:1.0.1

cd ServiceDiscovery
sudo docker build -t service-discovery . 
sudo docker run -d --network host -it service-discovery

cd ../ServiceProxy
sudo docker build -t service-proxy . 
sudo docker run -d --network host -it service-proxy

cd ../TransactionService
sudo docker build -t transaction-service . 
sudo docker run -d --network host -it transaction-service $(cat ../../deploy.properties)

cd ../TransactionServiceLoadBalancer
sudo docker build -t transaction-service-load-balancer . 
sudo docker run -d --network host -it transaction-service-load-balancer

cd ../ScrapperService
sudo docker build -t scrapper-service . 
sudo docker run -d --network host -it scrapper-service

cd ../SubscriptionService
sudo docker build -t subscription-service . 
sudo docker run -d --network host -it subscription-service

cd ../PricePredictionService
sudo docker build -t price-prediction-service . 
sudo docker run -d --network host -it price-prediction-service

cd service
sudo docker build -t price-prediction-app-service . 
sudo docker run -d --network host -it price-prediction-app-service

