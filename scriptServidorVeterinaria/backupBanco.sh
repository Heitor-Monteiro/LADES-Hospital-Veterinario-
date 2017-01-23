#!/bin/bash

#O script sh será utilizado para criar 
#o arquivo de backup do banco de dados “bd_sihv”.

echo "Makvet-script ----> Iniciando backupBanco !!!";

passe=$"ssxq9d9rssx";
data=$(date);
caminho=$"/home/thiberius/BackupsSIHV"; #Variável que determina o caminho a ser utilizado pelo script.


#Identificando o nome do arquivo de backup.
arquivo=$(ls "$caminho");


#Excluindo o arquivo de backup caso exista.
if [ "$arquivo" != "" ];then
    rm "$caminho"/"$arquivo";
fi


#Criando o arquivo de backup.
mysqldump -u root -p"$passe" bd_sihv > "$caminho"/"backupSihv($data).sql";

echo "Makvet-script ----> Fim backupBanco !!!";

exit 0;
