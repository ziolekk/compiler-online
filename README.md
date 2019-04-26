# Compiler online

**Gotowy kontener z aplikacją: [DockerHub](https://cloud.docker.com/u/ziolekk/repository/docker/ziolekk/compileronline)**


## Co to jest?
Compiler online to prosty serwis umożliwiający kompilowanie kodu c++ na serwerze za pomocą API.
Po wysłaniu kodu i danych wejściowych zwórci nam JSON z danymi wyjściowymi oraz ewentualnymi informacjami na temat błedów.


## Instalacja
- Pobierz obraz z [DockerHub](https://cloud.docker.com/u/ziolekk/repository/docker/ziolekk/compileronline)
- Utwórz kontener i uruchom w nim serwer tomcat
```
sudo docker run -ti -d --name CompilerOnline --restart unless-stopped -p 8888:8080 ziolekk/compileronline bash
sudo docker exec CompilerOnline sh /opt/tomcat/bin/startup.sh
```


## Uruchamianie
*Po instalacji aplikacja jest już uruchomiona*
```
sudo docker start CompilerOnline
sudo docker exec CompilerOnline sh /opt/tomcat/bin/startup.sh
```


### Jak korzystać?
Aby wykonać program trzeba wysłać na adres http://localhost:8888/co/compile następujący JSON
```
{
"id": 1,
"code": "",
"inputs": [ ],
"maxExecutionTimeForTestInSeconds": [ ]
}

gdzie:
    id - aktualnie może być obojętne
    code - nasz kod do skompilowania jako ciąg znaków
    inputs - tablica typu string na dane wejściowe, program zostanie odpalony dla
        każdego elementu tablicy osobno i zwórci ich wynik)
    maxExecutionTimeForTestInSeconds - maksymalny czas (w sekundach) wykonania dla każdego programu
```
W odopowiedzi serwe zwórci JSON:
```
{
"id": 1,
"isGoodCompilation": (true/false),
"outputs": [],
"outputsStatus": []
}

gdzie:
    isGoodCompilation - pokazuje czy udało się skompilować program
    outputs - tablica z danymi wyjściowymi, i-ty wynik odpowiada i-tej pozycji w tablicy inputs
    outputsStatus - zawiera opis wykonania programów dla poszczególnch danych wejściowych: 
        - OK - kompilacja bez problemu
        - TIMEEXCEPTION - przekroczono limit czasu
        - ERROR - błąd w trakcie wykonywania kodu
```