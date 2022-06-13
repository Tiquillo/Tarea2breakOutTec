//
// Created by david on 13/06/22.
//

#include "AccionesServidor.h"

int AccionesServidor( char *Datos) {

    cJSON *json = cJSON_Parse(Datos);

    int n = cJSON_GetObjectItem(json, "accion")->valueint;

    if(n == 1) {

        ListaLadrillos *lista = malloc(sizeof(ListaLadrillos));
        iniciar(lista);
        LlenarLista(lista);

        json = convertirALista(lista);

        char *json_string = cJSON_Print(json);

        strcpy(Datos, json_string);

        return 1;

    } else if(n == 2) {

        return 2;

    } else if(n == 3) {

        velocidad += 1;

        ListaLadrillos *lista = malloc(sizeof(ListaLadrillos));
        iniciar(lista);

        cambiarVelocidad(lista, velocidad);

        LlenarLista(lista);

        json = convertirALista(lista);

        char *json_string = cJSON_Print(json);

        strcpy(Datos, json_string);

        return 1;
    }

    return 0;


}