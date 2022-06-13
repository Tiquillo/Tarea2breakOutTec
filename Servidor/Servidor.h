//
// Created by david on 12/06/22.
//

#ifndef SERVIDOR_SERVIDOR_H
#define SERVIDOR_SERVIDOR_H


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>

#include "SocketServidor.h"
#include "Socket.h"
#include "Constantes.h"

void nuevoCliente(int servidor, int *clientes, int *nClientes);

int obtenerMaximo(int *tabla, int n);

void compactaClaves(int * tabla, int *n);

iniciarServidor();

#endif //SERVIDOR_SERVIDOR_H
