# Java Software Engineer - Developer Test

## Estructura del Proyecto

El proyecto se divide en dos carpetas principales:

- `backend`
- `frontend`

## Requisitos

### Backend

Para correr el Backend es necesario tener instalado **maven**. Los tests del Backend se encuentran en la carpeta `backend/src/test`.

Para correr el Backend, utiliza el siguiente comando:

```bash
mvn spring-boot:run
```

Una vez compilado el Backend, el archivo `.war` se encontrará en la carpeta `target`.

Para compilar utilizar el siguiente comando:

```bash
mvn clean package
```

### Frontend

Para correr el Frontend es necesario tener instalado **Node.js** y **npm**.

En el archivo `frontend/src/components/EmployeeList.tsx` hay una constante llamada `BASE_URL`. En caso de hacer deploy del back
hay que asegurarse que este URL corresponde al URL donde se está desplegando.

Para ejecutar el front se puede usar el comando:

```bash
npm run dev
```

### APIs Utilizadas

Debido a problemas con la API propuesta (por el rate limit configurado), se decidió utilizar otra API que provee información similar. La única diferencia es que no asignaba un valor numérico para asociar al salario, por lo que se tomó el código postal (zip code). Además, se utilizó una API diferente para extraer las imágenes de cada usuario.

- API usada: <https://www.freetestapi.com/api/v1/users>
- API imagenes: <https://randomuser.me/api/?results=10>
