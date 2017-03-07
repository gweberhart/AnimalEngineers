cd c:\gae
FOR /F "tokens=1-4 delims=/ " %%I IN ('DATE /t') DO SET mydate=%%J%%K%%L
set mytime=%TIME:~0,2%%TIME:~3,2%%TIME:~6,2%
C:\Python26\python.exe appcfg.py download_data --application=animalengineers --url=http://1.latest.animalengineers.appspot.com/remote_api --filename=C:\Users\happycat\workspace\AnimalEngineers\UnpublishedResources\backup\__Backup%mydate%%mytime%.txt --email=happycatmaddog@gmail.com --passin < c:\gae\ge.txt
@ECHO OFF
echo Verify backup successful! press enter to exit.
pause





