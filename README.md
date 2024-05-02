# spring-security-jwt
Practicas

El proyecto se puede levantar ejecutando el script Install.sh el cual realiza los siguientes comandos 

    mvn clean install -DskipTests
    docker-compose build
    docker-compose up -D 
    
demora un poco en lo que termina de configurar la BD una vez levantado los endpoinjts publicados son 



http://localhost:9091/api/accounts/{id}    GET          Obtiene la cuanta con el id 
http://localhost:9091/api/accounts/{id}    DELETE       Elimina la cuanta con el id
http://localhost:9091/api/accounts/{id}    PUT          Modifica la cuenta con el id
http://localhost:9091/api/accounts         GET          Regresa un lista con las cuantas existentes
http://localhost:9091/api/accounts         POST         Crea una nueva cuenta


http://localhost:9091/Auth/sign?name=Leonard&password=123&rol=Admin      GET        Crea una cuenta con la cual puedes generar el JWT
http://localhost:9091/Auth/login?name=Leonard&password=123               GET        Genera el JWT con la cuenta creada anteriormente

los dos ultimos metodos no requieren del so del cabecero "Authentication"
