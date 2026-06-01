package org.lupoldo.ui.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.lupoldo.common.exception.FalloCargaEscenaException;

import java.io.IOException;

public class Podium implements Escena{

    @Override
    public Scene getScene() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            return new Scene(loader.load());
        }catch (IOException exception){
            throw new FalloCargaEscenaException("La escena Track no se ha podido cargar");
        }
    }
}
