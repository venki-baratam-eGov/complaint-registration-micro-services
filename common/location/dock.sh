./mvnw clean package
sudo docker build -t location:latest .
sudo docker-compose build
sudo docker-compose up
