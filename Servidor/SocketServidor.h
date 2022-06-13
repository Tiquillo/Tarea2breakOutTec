//
// Created by david on 12/06/22.
//

#ifndef SERVIDORC_SOCKETSERVIDOR_H
#define SERVIDORC_SOCKETSERVIDOR_H

#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>
#include <netinet/in.h>
#include <netdb.h>
#include <unistd.h>
#include <errno.h>

int Acepta_Conexion_Cliente (int Descriptor);
int Abre_Socket_Inet (int PORT);


#endif //SERVIDORC_SOCKETSERVIDOR_H
