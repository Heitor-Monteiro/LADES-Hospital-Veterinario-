#!/bin/bash

#O script sh será utilizado para criar 
#o arquivo de backup do banco de dados “bd_sihv”.

nomeUse=$"thiberius";
passe=$"ssxq9d9rssx";

mysqldump -u root -p"$passe" bd_sihv > /home/"$nomeUse"/BackupsSIHV/backupSihv.sql;
