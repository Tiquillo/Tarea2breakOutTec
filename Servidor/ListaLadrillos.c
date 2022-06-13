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

    for (int i = 0; i < 120; i++) {

        poderAleatorio = aleatorio(0, 6);

        if(i >= 0 && i < 30) {
            insertar(lista, 400, 0,0, "rojo", poderAleatorio);
        } else if (i >= 30 && i < 60) {
            insertar(lista, 300, 0,0, "naranja", poderAleatorio);
        } else if (i >= 60 && i < 90) {
            insertar(lista, 200, 0,0, "amarillo", poderAleatorio);
        } else if (i >= 90 && i < 120) {
            insertar(lista, 100, 0,0, "verde", poderAleatorio);
        }

    }

}

int aleatorio(int min, int max) {
    return min + rand() / (RAND_MAX / (max - min + 1) + 1);
}

cJSON *convertirALista(ListaLadrillos *lista) {

    cJSON *json = NULL;
    json = cJSON_CreateObject();

    cJSON *bola = NULL;
    bola = cJSON_CreateObject();

    cJSON_AddNumberToObject(bola, "x", 0);
    cJSON_AddNumberToObject(bola, "y", 0);
    cJSON_AddNumberToObject(bola, "velocidad", lista->velocidad);
    cJSON_AddNullToObject(bola, "next");

    cJSON *raqueta = NULL;
    raqueta = cJSON_CreateObject();

    cJSON_AddNumberToObject(raqueta, "x", 0);
    cJSON_AddNumberToObject(raqueta, "y", 0);
    cJSON_AddNumberToObject(raqueta, "largo", 0);

    cJSON_AddNumberToObject(json, "accion", 1);
    cJSON_AddNumberToObject(json, "vida", 0);
    cJSON_AddNumberToObject(json, "puntos", 0);
    cJSON_AddItemToObjectCS(json, "bola", bola);
    cJSON_AddItemToObjectCS(json, "raqueta", raqueta);

    Ladrillos *aux = lista->primero;

    cJSON *ultimo = NULL;

    while (aux->siguiente != NULL) {

        if(ultimo == NULL) {

            cJSON *head = NULL;
            head = cJSON_CreateObject();

            cJSON_AddNumberToObject(head, "puntuacion", aux->puntuacion);
            cJSON_AddNumberToObject(head, "x", aux->x);
            cJSON_AddNumberToObject(head, "y", aux->y);
            cJSON_AddNumberToObject(head, "poder", aux->poder);
            cJSON_AddItemToObject(json, "head", head);

            ultimo = head;

        } else{

            cJSON *head = NULL;
            head = cJSON_CreateObject();

            cJSON_AddNumberToObject(head, "puntuacion", aux->puntuacion);
            cJSON_AddNumberToObject(head, "x", aux->x);
            cJSON_AddNumberToObject(head, "y", aux->y);
            cJSON_AddNumberToObject(head, "poder", aux->poder);
            cJSON_AddItemToObject(ultimo, "next", head);

            ultimo = head;

        }

        aux = aux->siguiente;

    }

    cJSON *head = NULL;
    head = cJSON_CreateObject();

    cJSON_AddNumberToObject(head, "puntuacion", aux->puntuacion);
    cJSON_AddNumberToObject(head, "x", aux->x);
    cJSON_AddNumberToObject(head, "y", aux->y);
    cJSON_AddNumberToObject(head, "poder", aux->poder);
    cJSON_AddItemToObject(ultimo, "next", head);
    cJSON_AddNullToObject(head, "next");

    return json;
}

void cambiarVelocidad(ListaLadrillos *lista, int velocidad) {
    lista->velocidad = velocidad;
}