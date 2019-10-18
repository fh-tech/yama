FROM jboss/wildfly:17.0.1.Final

ENV SETUP_HOME="/tmp"

ADD setup.cli mysql-connector-java-8.0.17.jar \
    ${SETUP_HOME}/

RUN ${JBOSS_HOME}/bin/add-user.sh admin admin -s

WORKDIR ${SETUP_HOME}
RUN ${JBOSS_HOME}/bin/jboss-cli.sh --file=${SETUP_HOME}/setup.cli

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "--debug"]
