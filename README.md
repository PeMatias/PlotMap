# PlotMap &mdash; Android Application
Aplicativo desenvolvido para a disciplina de Sistemas Distribuídos ofertada pelo Instituto de Computação do Amazonas na Universidade Federal do Amazonas (UFAM). 

## Objetivo da atividade
O objetivo dessa atividade é fazer uma aplicação Android que consuma a seguinte API: https://restcountries.eu/rest/v1/all​  e salvar os dados obtidos em uma banco de dados. A Api fornece uma lista com vários várias informações dos países do mundo, incluindo dados de localização como a latitude e longitude. 

## Funcionalidades do PlotMap

1. Listar os nomes dos países e suas respectivas capitais. Além de fornecer o continente que está inserido.

2. Plotar em um mapa a geolocalização de cada país. 

3. Através de um menu de opções, o usuário pode escolher plotar todos os países de um determinado continente.


## Instalação
Clone este repositório e importe no **Android Studio**
```bash
git clone git://github.com/PeMatias/PlotMap.git
```
## Gerando assinaturas para o APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

## Mantenedores
Este projeto é mantido por:
* [Pedro Matias](http://github.com/PeMatias)
* [Larissa Pessoa](http://github.com/larissapessoa)



## Contribuição

1. Faça um Fork do repositório
2. Crie seu ramo de recursos (git checkout -b my-new-feature)
3. Confirme suas alterações (git commit -m 'Adicionar algum recurso')
4. Execute o linter (ruby lint.rb ').
5. Pressione seu ramo (git push origin my-new-feature)
6. Criar uma nova solicitação pull
