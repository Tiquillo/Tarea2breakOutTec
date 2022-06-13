//
// Created by david on 12/06/22.
//

#ifndef SERVIDORC_SOCKET_H
#define SERVIDORC_SOCKET_H

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <sys/un.h>
#include <netdb.h>
#include <unistd.h>
#include <errno.h>
#include <stdio.h>


int Lee_Socket (int fd, char *Datos, int Longitud);
int Escribe_Socket (int fd, char *Datos, int Longitud);


#endif //SERVIDORC_SOCKET_H
