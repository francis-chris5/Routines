#! /bin/bash


if [ ! -d javafx-sdk-11.0.2 ] 
then
	wget https://download2.gluonhq.com/openjfx/11.0.2/openjfx-11.0.2_linux-x64_bin-sdk.zip
	unzip openjfx-11.0.2_linux-x64_bin-sdk.zip
	rm openjfx-11.0.2_linux-x64_bin-sdk.zip
fi



java --module-path javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml,javafx.web -jar Routines.jar
