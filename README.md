# Приложение для использования сервисом [HiveOS](https://the.hiveos.farm/)
## _Версия API 2.1-beta_
### Для работы скачать Java FX, далее экспортировать библиотеку
> File → Project Structure → Libraries
### Далее добавить VM Options для запуска
> Run → Edit configurations → VM Options
### Параметры VM Options
```sh--module-path "Путь к JavaFX\lib" --add-modules javafx.controls,javafx.fxml>```
# Собрать exe файл
## Утилита Launch4j
### Создаём *artifact*
> File → Project Structure → Artifacts
### Делаем build проекта
> Build → Build Project
### После в Launch4j указываем путь к JAR файлу
### И добавляем VM Options
```sh--module-path "Путь к JavaFX\lib" --add-modules javafx.controls,javafx.fxml>```
# Готово
