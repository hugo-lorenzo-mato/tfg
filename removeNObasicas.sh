#!/usr/bin/env bash

echo ""
echo ""
echo ""
echo "Eliminando contenedores e imágenes docker a excepción básicos... "
echo ""
echo ""
echo ""
docker rm webserver -f
docker rmipostgresdb -f
echo ""
echo ""
echo ""
docker rmi webserver -f
docker rmi postgresdb -f
echo ""
echo ""
echo ""
echo "Finalizado"
echo ""
echo ""
echo ""
echo ""
echo "Comprobando estado de contenedores e imágenes: "
echo ""
docker ps
echo ""
echo ""
echo ""
docker ps -a
echo ""
echo ""
echo ""
echo ""
docker images
echo ""
echo ""
echo ""
echo ""
