//
// Created by david on 13/06/22.
//

#ifndef SERVIDOR_LISTALADRILLOS_H
#define SERVIDOR_LISTALADRILLOS_H

#include <stdio.h>
#include <stdlib.h>
#include "Constantes.h"
#include <unistd.h>
#include "cJSON.h"

typedef struct ladrillo {
    int puntuacion;
    int x;
    int y;
    char *tipo;
    int poder;
    struct ladrillo *siguiente;
} Ladrillos;

typedef struct ListaLadrillos {
    struct ladrillo *primero;
    struct ladrillo *ultimo;
    int n;
} ListaLadrillos;

void iniciar();
void insertar(ListaLadrillos *lista,int puntuacion, int x, int y, char *tipo, int poder);
struct ladrillo obtenerPrimero(ListaLadrillos *lista);
void Imprimir(ListaLadrillos *lista);
int aleatorio(int min, int max);
void LlenarLista(ListaLadrillos *lista);
cJSON *convertirALista(ListaLadrillos *lista);

#endif //SERVIDOR_LISTALADRILLOS_H
