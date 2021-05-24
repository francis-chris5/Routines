@ECHO OFF

IF NOT EXIST javafx\ (

	ECHO I have to download some dependencies here... Give me a minute, this is huge...

	Powershell.exe Invoke-WebRequest -uri https://download2.gluonhq.com/openjfx/11.0.2/openjfx-11.0.2_windows-x64_bin-sdk.zip -outfile javafx.zip


	Powershell.exe Expand-Archive javafx.zip
)


java --module-path "%cd%\javafx\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml,javafx.web --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED --add-exports javafx.base/com.sun.javafx.event=ALL-UNNAMED -jar Routines.jar

