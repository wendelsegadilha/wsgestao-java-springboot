FROM openjdk:11

EXPOSE 8080

RUN apt-get update && \
 apt-get install -y netcat;

COPY /target/GestaoDeVendas-1.0.0.jar /app/getsao_vendas.jar
COPY /wait-for-mysql.sh /app/wait-for-mysql.sh

WORKDIR /app

# ENTRYPOINT ["java", "-jar", "/app/getsao_vendas.jar"]