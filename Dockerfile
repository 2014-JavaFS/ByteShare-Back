# FROM is building the base image with all the required language kits for java specifically here
FROM openjdk:17-alpine

# stores all of our app data by default, its common practice
WORKDIR app/

# COPY - periods(.) mean everything from the local directory (to the dockerfile location) will be copied in the work dir
# in the image which is the second period
# Always pair this with a docker ignore file
COPY . .

RUN chmod +x mvnw

ENV PORT=8080

ENTRYPOINT ["./mvnw", "spring-boot:run"]