//
// Created by david on 13/06/22.
//

#include "ListaLadrillos.h"

void iniciar() {
    ListaLadrillos *lista = malloc(sizeof(ListaLadrillos));
    lista->primero = NULL;
    lista->ultimo = NULL;
    lista->n = 0;
    lista->velocidad = 1;
}

void insertar(ListaLadrillos *lista,int puntuacion, int x, int y, char *tipo, int poder) {
    Ladrillos *nuevo = malloc(sizeof(Ladrillos));
    nuevo->puntuacion = puntuacion;
    nuevo->x = x;
    nuevo->y = y;
    nuevo->tipo = tipo;
    nuevo->poder = poder;
    nuevo->siguiente = NULL;
    if (lista->primero == NULL) {
        lista->primero = nuevo;
        lista->ultimo = nuevo;
    } else {
        lista->ultimo->siguiente = nuevo;
        lista->ultimo = nuevo;
    }
    lista->n++;
}

struct ladrillo obtenerPrimero(ListaLadrillos *lista) {
    return *lista->primero;
}

void Imprimir(ListaLadrillos *lista) {
    Ladrillos *aux = lista->primero;
    while (aux != NULL) {
        printf("%d %d %d %s %d\n", aux->puntuacion, aux->x, aux->y, aux->tipo, aux->poder);
        aux = aux->siguiente;
    }
}

void LlenarLista(ListaLadrillos *lista) {

    srand(getpid());
    int poderAleatorio;

    int x = 12;
    int y = 55;

    for (int i = 0; i < 56; i++) {

        poderAleatorio = aleatorio(0, 6);

        if (i == 7 || i == 21 || i == 35 || i == 49 || i == 14 || i == 28 || i == 42 ){
            x = 12;
            y += 20;
        }

        if(i >= 0 && i < 15) {
            insertar(lista, 400, x,y, "rojo", poderAleatorio);
            x += 112;
        } else if (i >= 15 && i < 29) {
            insertar(lista, 300, x,y, "naranja", poderAleatorio);
            x += 112;
        } else if (i >= 29 && i < 43) {
            insertar(lista, 200, x,y, "amarillo", poderAleatorio);
            x += 112;
        } else if (i >= 43 && i < 57) {
            insertar(lista, 100, x,y, "verde", poderAleatorio);
            x += 112;
        }

    }

}

int aleatorio(int min, int max) {
    return min + rand() / (RAND_MAX / (max - min + 1) + 1);
}

cJSON *convertirALista(ListaLadrillos *lista) {

    cJSON *json = NULL;
    json = cJSON_CreateObject();

    cJSON_AddNumberToObject(json, "accion", 1);
    cJSON_AddStringToObject(json, "largo", "55");

    Ladrillos *aux = lista->primero;

    int i = 0;
    char mystr[10];

    while (aux != NULL) {

        cJSON *head = NULL;
        head = cJSON_CreateObject();
        sprintf(mystr, "%d", i);

        cJSON_AddNumberToObject(head, "pts", aux->puntuacion);
        cJSON_AddNumberToObject(head, "x", aux->x);
        cJSON_AddNumberToObject(head, "y", aux->y);
        cJSON_AddNumberToObject(head, "poder", aux->poder);
        cJSON_AddItemToObject(json, mystr, head);

        aux = aux->siguiente;
        i++;

    }

    return json;
}

void cambiarVelocidad(ListaLadrillos *lista, int velocidad) {
    lista->velocidad = velocidad;
}