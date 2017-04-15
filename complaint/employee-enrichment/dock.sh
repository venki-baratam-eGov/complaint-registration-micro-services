./mvnw clean package
sudo docker build -t employee-enrichment:latest .
sudo docker-compose build
sudo docker-compose up
