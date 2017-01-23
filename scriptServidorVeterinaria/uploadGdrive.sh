#!/bin/bash

#O script sh será utilizado para
#realizar o upload do arquivo
#de backup do banco de dados “bd_sihv”.

echo "Makvet-script ----> inicio upload !!!";

confirmUpload=$"Failed";
caminho=$"/home/thiberius/BackupsSIHV"; #Variável que determina o caminho a ser utilizado pelo script.


#Identificando o nome do arquivo de backup.
arquivo=$(ls "$caminho");


arquivoID=$"";
confirmDelete=$"Failed";
while [ "$confirmUpload" == "Failed" ];do

    if [ "$confirmDelete" == "Failed" ];then
        arquivoID=$(gdrive list | grep "sql" | cut -d " " -f 1);
        echo "Makvet-script ----> ID encontrado: $arquivoID";

        if [ "$arquivoID" != "" ];then
           temp=$"";
           temp=$(gdrive delete "$arquivoID" | grep "Deleted" | cut -d " " -f 1 );

           if [ "$temp" == "Deleted" ];then
              confirmDelete="$temp";
              echo "Makvet-script ----> delete do ID($arquivoID) encontrado: $confirmDelete";
              arquivoID=$"";
           fi
        else
           echo "Makvet-script ----> delete do ID($arquivoID) encontrado: $confirmDelete";
        fi
    fi

    temp2=$(gdrive upload "$caminho"/"$arquivo" | grep "Uploaded" | cut -d " " -f 1);

    if [ "$temp2" != "" ];then
       confirmUpload="$temp2";
    fi

    echo "Makvet-script ----> resultado de upload: $confirmUpload";

    #Excluindo o arquivo de backup caso exista.
    if [ "$confirmUpload" == "Uploaded" ];then
        rm "$caminho"/"$arquivo";
    fi

    if [ "$confirmUpload" == "Failed" ];then
       echo "Makvet-script ----> falha de conexão, upload de backup será efetuado novamente em 2 minutos.";
       sleep 120;
    fi
done

echo "Makvet-script ----> fim upload !!!";

exit 0;
