FROM airhacks/glassfish
COPY ./target/TP-Servlet.war ${DEPLOYMENT_DIR}
