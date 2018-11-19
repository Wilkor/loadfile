@echo off
set MOU_SECRETPATH=%HOMEDRIVE%%HOMEPATH%\Documents\workspace-sts-3.9.1.RELEASE\LoadFile\target\secret\
set MOU_URL_DB=jdbc:oracle:thin:@ORADS447.wt.isbanbr.corp:1529/ORADS447
set MOU_DATA_USER=%MOU_SECRETPATH%\username
set MOU_DATA_PSW=%MOU_SECRETPATH%\password
set MOU_JDBC_DRIVER=oracle.jdbc.OracleDriver
set MOU_PATCH_ARCHIVE_CSV=C:\Users\t697609\Desktop\Arq. Desk\arquivos que funcionam\

set SECRETPATH=%HOMEDRIVE%%HOMEPATH%\Desktop\Compartilhamento\secret
set URL_DB=jdbc:oracle:thin:@ORADS447.wt.isbanbr.corp:1529/ORADS447
set DATA_USER=%MOU_SECRETPATH%\username
set DATA_PSW=%MOU_SECRETPATH%\password
set JDBC_DRIVER=oracle.jdbc.OracleDriver
set PATCH_ARCHIVE_CSV=C:\Users\t697609\Desktop\Arq. Desk\arquivos que funcionam\

del log_* /Q
java -jar LoadFile-jar-with-dependencies.jar
pause