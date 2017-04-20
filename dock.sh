cd common/employee/
./mvnw clean package
sudo docker build -t employee:latest .
cd ../location/
./mvnw clean package
sudo docker build -t location:latest .
cd ../../complaint/complaint-registration/
./mvnw clean package
sudo docker build -t complaint-registration:latest .
cd ../crn-generation/
./mvnw clean package
sudo docker build -t crn-generation:latest .
cd ../custom-crn-generation/
./mvnw clean package
sudo docker build -t custom-crn-generation:latest .
cd ../employee-enrichment/
./mvnw clean package
sudo docker build -t employee-enrichment:latest .
cd ../location-enrichment/
./mvnw clean package
sudo docker build -t location-enrichment:latest .
cd ../../
sudo docker-compose build
sudo docker-compose up
