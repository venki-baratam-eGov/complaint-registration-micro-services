./mvnw clean package
sudo docker build -t location-enrichment:latest .
sudo docker-compose build
sudo docker-compose up
