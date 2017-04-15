./mvnw clean package
sudo docker build -t complaint-registration:latest .
sudo docker-compose build
sudo docker-compose up
