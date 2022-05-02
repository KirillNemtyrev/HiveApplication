# Приложение для использования сервисом [HiveOS](https://the.hiveos.farm/)
## _Версия API [2.1-beta](https://app.swaggerhub.com/apis/HiveOS/public/2.1-beta#/)_
### Для работы скачать Java FX, далее экспортировать библиотеку
> File → Project Structure → Libraries
### Далее добавить VM Options для запуска
> Run → Edit configurations → VM Options
### Параметры VM Options
```--module-path "Путь к JavaFX\lib" --add-modules javafx.controls,javafx.fxml```
# Собрать exe файл
## Утилита Launch4j
### Создаём *artifact*
> File → Project Structure → Artifacts
### Делаем build проекта
> Build → Build Project (Ctrl + F9)
### После в Launch4j указываем путь к JAR файлу
### И добавляем VM Options
```--module-path "Путь к JavaFX\lib" --add-modules javafx.controls,javafx.fxml```
# Готово
