FROM openjdk:latest
VOLUME /tmp

# PORT and ENVIRONMENT VARIABLES
# Recommended port
#EXPOSE 8080
ENV PORT value

# Database info
ENV DATABASE_URI value

# Jwt info
ENV JWT_SECRET value

ADD target/BTD6-API.jar BTD6-API.jar
ENTRYPOINT ["java","-jar","/BTD6-API.jar"]