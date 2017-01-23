#!/bin/bash

#O script sh será utilizado para iniciar o servidor Glassfish,
#caso o mesmo já esteja iniciado, o  software Glassfish
#não sofrerá “start” novamente.

portGlassfish=$"5552                 ";

result=$(netstat -t -l -p --numeric-ports | grep "$portGlassfish" | cut -d ":" -f 4 );

if [ "$result" == "$portGlassfish" ];then
    echo "Makvet-script ----> O servidor já está inicializado !!!";
else
    echo "Makvet-script ----> Iniciando o Glassfish !!!";
    /opt/glassfish4/bin/asadmin start-domain;
fi

echo "Makvet-script ----> Fim de StartGlassfish !!!";

exit 0;
