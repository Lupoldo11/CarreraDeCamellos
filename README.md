## Proyecto: Carrera de Camello ##

Carrera de Camello es un sistema cliente-servidor que agrupa automaticamente 4 clientes, les asigna una direccion 
IP multicast y gestiona las diferentes carreras mediante intercambio de objetos serializados. El servidor permanecera 
activo aceptando clientes y creando grupos hasta un maximo de 3 grupos en paralelo.

---

### Requisito funcionales
RF1 - Gestion de conexion y formacion de grupos
(los clientes se conectan y se les asigna un grupo) 

RF2 - Asignacion de grupos
(recibe ip multicast en rango y puerto unico por grupo)

RF3 - Asignacion de id grupo mediante semaforo
(dar identificaro unico a cada grupo mediante semaforo o exclusion mutua)

RF4 - Protocolo de comunicacion basada en objetos
(estandarizar los eventos y enviar objetos serializados)

RF5 - Simulacion de la carrera
(permitir que cada cliente participe activamente en la carrera)

RF6 - Sincronizacion de finalizacion
(garantizar al finalizar que los datos del ranking sean iguales y haya solo una finalizacion)

RF7 - Gestion de fallos y desconexiones
(mantener la estabilidad del sistema ante incidencias -> gestionar timeouts y desconexiones)

RF8 - Arquitectura de transporte
(Comunicar mediante TCP para el control y UDP multicast para la carrera)

### Requisito no funcionales
RNF1 - Logs claros en servidor y cliente

RNF2 - Configuracion por fichero o parametros

RNF3 - Seguidad y validacion de objetos

RNF4 - Soporte para multiples clientes y grupos simultaneos

RNF5 - Portabilidad (ejecucion en distintos equipos o en red local)

---
### Casos de Uso
CU1 - Conectarse al servidor: permite que un jugador se una al sistema 
    1) Jugador inicia cliente
    2) Cliente envia solicutudConexion
    3) Servidor recibe solicitud
    4) Servidor registra jugador
    5) Jugador queda en espera de asignacion

CU2 - Formar grupo de carrera: crear grupo para competir
    1) servidor detecta suficientes jugadores
    2) servidor genera un grupo (asigna un idGrupo)
    3) servidor reserva IP multicast y puerto
    4) servidor envia AsignacionGrupo a cada jugador

CU3 - Preparar carrera: comprobar todos los jugadores listos
    1) jugador confirma esta preparado
    2) cliente envia EstadoJugador(listo)
    3) servidor recibe estado todos los jugadores
    4) servidor autoriza el inicio

CU4 - Participar en la carrera: simular avance 
    1) cliente pusa el boton y se calcula avance
    2) genera evento EventoCarrera(Paso)
    3) envia evento por el multicast
    4) recibe evento de los demas
    5) actualiza la UI

CU5 - Detectar desconexiones: gestionar fallos durante carrera
    1) Servidor espera evento heartbeats de los clientes
    2) Jugador deja de enviar heartbeats
    3) se supera timeout establecido
    4) el servidor marca al jugador como desconectado
    5) se aplica politica de suspension (si siguen estando 2 jugadores activos se continua la carrera)

CU6 - Finalizar carrera: cerrar la carrera de forma sincronizada
    1) geneta evento EventoCarrera(Paso)
    2) servidor valida posicion del evento si esta en meta
    3) servidor calcula el ranking
    4) servidor genera FinCarrera
    5) Todos los cliente reciben evento
    6) clientes muestra la clasificacion
    7) carrera termina

CU7 - Gestion error de protocolo: manejar mensajes invalidos
    1) servidor valida objeto recibido
    2) detecta formato incorrecto
    3) genera ErrorProtocolo
    4) envia error al cliente