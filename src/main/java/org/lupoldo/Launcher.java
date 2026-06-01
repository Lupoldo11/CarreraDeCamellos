package org.lupoldo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) { //IP clase: 192.168.113.1
        //Iniciar navegador de escenas y cargar primera escena
        //Iniciar hilo principal de UI y hilos secundarios de logica
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("recurso"));
        Scene scene = new Scene(loader.load(),200,200);
        stage.setScene(scene);
        stage.show();
    }
}
//Tengo que hacer los diagramas para plasmar la idea
//Hacer la repository

/*
---
Para actualizar la UI desde otro hilo

Platform.runLater(() -> {
    label.setText("Actualizado");
});

---
* Tabajar en segundo plano

Task<String> tarea = new Task<>() {
    @Override
    protected String call() throws Exception {
        Thread.sleep(3000); // trabajo pesado
        return "Completado";
    }
};
tarea.setOnSucceeded(e -> {
    label.setText(tarea.getValue());
});
new Thread(tarea).start();

---
Estructura:
    ui (parte del UI para desarrollar el visual y las ventanas)
    viewmodel (conecta UI - service)
    service (funciones que hagan que se muevan los camellos y hagan peticiones)
    model (user, camello, conexion)

Importantes:
0. ajustar server, usuarios y parametros de inicio
1. conectar TDP para pasar los datos desde el server al cliente
2. conectar los clientes al udp multicast de la carrera
3. conectar el server al udp multicast de la carrera para controllar los eventos de la carrera
4. enviar eventos entre todos
5. actualizar UI de cada 1 a la misma
6. salirse de la carrera
7. controllar diferentes carreras desde el mismo servidor (Hilos?????)


* */