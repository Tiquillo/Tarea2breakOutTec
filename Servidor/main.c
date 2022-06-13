#include "Servidor.h"

#include "cJSON.h"
#include "ListaLadrillos.h"

int main (){

    //iniciarServidor();

    cJSON *json = NULL;
    json = cJSON_CreateObject();


    ListaLadrillos *lista = malloc(sizeof(ListaLadrillos));
    iniciar(lista);
    LlenarLista(lista);

    json = convertirALista(lista);

    char *json_string = cJSON_Print(json);

    printf("%s\n", json_string);


}