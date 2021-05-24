#! /bin/zsh


if [ ! -d javafx-sdk-11.0.2 ] 
then
	curl https://download2.gluonhq.com/openjfx/11.0.2/openjfx-11.0.2_osx-x64_bin-sdk.zip --output javafx.zip
	unzip javafx.zip
	rm javafx.zip
fi

current=$(pwd)
java --module-path $current/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml,javafx.web -jar Routines.jar 