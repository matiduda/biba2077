FROM adoptopenjdk:11-hotspot-bionic
RUN apt-get update
RUN apt-get -y install xorg gtk2-engines libasound2 libgtk2.0-0
RUN apt-get -y install unzip
WORKDIR /app
COPY . /app/
RUN ./gradlew jproRelease
RUN ls build/distributions/
RUN unzip build/distributions/app-jpro
RUN ls .
RUN ls build/distributions/
# CMD ls build/distributions/
RUN cp start2.sh ./app-jpro/bin/start.sh
RUN chmod +x ./app-jpro/bin/start.sh
RUN ["sh", "-c", "echo  -Dhttp.port=$PORT"]
CMD ["sh", "-c", "./app-jpro/bin/start.sh  -Dhttp.port=$PORT"]