@ECHO OFF

IF NOT EXIST javafx\ (
	echo Need to download some dependencies first...
	curl https://download2.gluonhq.com/openjfx/11.0.2/openjfx-11.0.2_windows-x64_bin-sdk.zip --output javafx.zip


	Powershell.exe Expand-Archive javafx.zip
)

echo Opening Routines Application...
java --module-path "%cd%\javafx\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml,javafx.web --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED --add-exports javafx.base/com.sun.javafx.event=ALL-UNNAMED -jar Routines.jar
