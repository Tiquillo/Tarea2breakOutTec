#include "Servidor.h"

#include "cJSON.h"
#include "ListaLadrillos.h"

int main (){

    //iniciarServidor();

    cJSON *json = NULL;
    char* a = iniciarBricks(1);
    printf("%s\n", a);

    printf("%d\n", strlen(a) + 1);

    int b = htonl(a);

    printf("%d\n", b);



}