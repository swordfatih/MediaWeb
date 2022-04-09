# School Project - MediaWeb

# Build
mvn clean package && docker build -t fr.mediaweb/MediaWeb .

# RUN

docker rm -f MediaWeb || true && docker run -d -p 8080:8080 -p 4848:4848 --name MediaWeb fr.mediaweb/MediaWeb 
