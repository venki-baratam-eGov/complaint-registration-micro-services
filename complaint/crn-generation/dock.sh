./mvnw clean package
sudo docker build -t crn-generation:latest .
sudo docker-compose build
sudo docker-compose up
