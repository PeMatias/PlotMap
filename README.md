# PlotMap &mdash; Android Application
Aplicativo para Android que consome a seguinte API: https://restcountries.eu/rest/v1/all​ . 


## Instalação
Clone este repositório e importe no **Android Studio**
```bash
git clone git://github.com/PeMatias/PlotMap.git
```

## Configuração
### Keystores:
Create `app/keystore.gradle` with the following info:
```gradle
ext.key_alias='...'
ext.key_password='...'
ext.store_password='...'
```
And place both keystores under `app/keystores/` directory:
- `playstore.keystore`
- `stage.keystore`


## Build variants
Use the Android Studio *Build Variants* button to choose between **production** and **staging** flavors combined with debug and release build types


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
