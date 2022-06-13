cmake_minimum_required(VERSION 3.22)
project(Servidor C)

set(CMAKE_C_STANDARD 23)

add_executable(Servidor main.c Socket.c Socket.h SocketServidor.c SocketServidor.h Servidor.c Servidor.h cJSON.h cJSON.c ListaLadrillos.c ListaLadrillos.h AccionesServidor.c AccionesServidor.h)