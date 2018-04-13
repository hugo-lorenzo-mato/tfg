#!/usr/bin/env bash

echo ""
echo ""
echo ""
echo "Eliminando contenedores e imágenes docker... "
echo ""
echo ""
echo ""
docker rm $(docker ps -a -q) -f
echo ""
echo ""
echo ""
docker rmi $(docker images -q) -f
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
