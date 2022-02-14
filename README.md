# Build
mvn clean package && docker build -t com.fatih/TP-Servlet .

# RUN

docker rm -f TP-Servlet || true && docker run -d -p 8080:8080 -p 4848:4848 --name TP-Servlet com.fatih/TP-Servlet 