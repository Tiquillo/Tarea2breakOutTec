//
// Created by david on 13/06/22.
//

#ifndef SERVIDOR_ACCIONESSERVIDOR_H
#define SERVIDOR_ACCIONESSERVIDOR_H

#include <stdio.h>
#include <stdlib.h>
#include "Constantes.h"
#include <unistd.h>
#include "cJSON.h"
#include "ListaLadrillos.h"
#include <string.h>


int AccionesServidor( char *Datos);

char* iniciarBricks(int accion);

#endif //SERVIDOR_ACCIONESSERVIDOR_H
