//
// Created by david on 13/06/22.
//

#include "AccionesServidor.h"

int velocidad = 1;

/**
 * Da la accion del servidor
 * @param Datos char*
 * @return int
 */
int AccionesServidor( char *Datos) {

    cJSON *json = cJSON_Parse(Datos);

    int n = cJSON_GetObjectItem(json, "accion")->valueint;

    if(n == 1) {

        return 1;

    } else if(n == 2) {

        return 2;

    } else if(n == 3) {

        return 1;
    }

    return 0;


}

/**
 * Da la orden de crear los ladrillos
 * @param accion int
 * @return chat*
 */
char *iniciarBricks(int accion) {

    cJSON *json = NULL;

    if(accion == 1) {

        ListaLadrillos *lista = malloc(sizeof(ListaLadrillos));
        iniciar(lista);
        LlenarLista(lista);

        json = convertirALista(lista,velocidad);

        char *json_string = cJSON_PrintUnformatted(json);

        free(lista);
        free(json);

        return json_string;

    } else if(accion == 3) {

        velocidad++;

        ListaLadrillos *lista = malloc(sizeof(ListaLadrillos));
        iniciar(lista);

        cambiarVelocidad(lista, velocidad);

        LlenarLista(lista);

        json = convertirALista(lista, velocidad);

        char *json_string = cJSON_PrintUnformatted(json);

        free(lista);
        free(json);

        return json_string;
    }

}