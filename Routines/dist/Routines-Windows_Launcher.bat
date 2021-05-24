@ECHO OFF


java --module-path %cd%\javafx-sdk-11.0.2\lib --add-modules javafx.controls,javafx.fxml,javafx.web --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED --add-exports javafx.base/com.sun.javafx.event=ALL-UNNAMED -jar Routines.jar

