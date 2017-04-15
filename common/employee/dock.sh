./mvnw clean package
sudo docker build -t employee:latest .
sudo docker-compose build
sudo docker-compose up
