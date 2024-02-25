# Build Image
./mvnw package -Dnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true

# Create Docker Image
docker build -f src/main/docker/Dockerfile.native-micro -t buemura/rinha-backend-2024-q1-quarkus:XXXX .

# Create Tag
docker tag buemura/rinha-backend-2024-q1-quarkus:XXXX buemura/rinha-backend-2024-q1-quarkus:latest

# Push
docker push buemura/rinha-backend-2024-q1-quarkus:XXXX
docker push buemura/rinha-backend-2024-q1-quarkus:latest