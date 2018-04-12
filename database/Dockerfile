FROM postgres:9.5

# Copy the database initialize script: 
# Contents of /docker-entrypoint-initdb.d are run on postgres startup
ADD  docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/

# Default values for passwords and database name. Can be overridden on docker run
# ENV MYSQL_ROOT_PASSWORD=my-secret-pw # Not defaulted for security reasons!
ENV POSTGRES_DB=ggal1701
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
