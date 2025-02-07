FROM 725107496335.dkr.ecr.ap-northeast-2.amazonaws.com/heavenlygradle:8.7-jdk21 as build
WORKDIR /app

#ENV JAVA_OPTS='-Dkotlin.compiler.execution.strategy=in-process'
RUN cp -R /root/tmp/.gradle /home/gradle/
COPY . /app
RUN gradle build -x test --parallel


FROM 725107496335.dkr.ecr.ap-northeast-2.amazonaws.com/heavenlyubuntu:lts-24-oracle-jdk  as final


USER root
COPY --from=build /app/build/libs/demo-0.0.1-SNAPSHOT.jar /bin/

ADD default.conf /etc/nginx/sites-available/default
EXPOSE 80
# What the container should run when it is started.
ENTRYPOINT ["/bin/sh", "-c" , "/etc/init.d/nginx start && /bin/demo-0.0.1-SNAPSHOT.jar"]
