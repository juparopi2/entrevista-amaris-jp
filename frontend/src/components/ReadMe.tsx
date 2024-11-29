export default function ReadMe() {
  return (
    <article>
      <h1>Java Software Engineer - Developer Test</h1>

      <h2>Estructura del Proyecto</h2>
      <ul className="ul-readme">
        <li>backend</li>
        <li>frontend</li>
      </ul>

      <h2>Requisitos</h2>

      <h3>Backend</h3>
      <p>
        Para correr el Backend es necesario tener instalado{" "}
        <strong>maven</strong>. Los tests del Backend se encuentran en la
        carpeta <code>backend/src/test</code>.
      </p>
      <p>Para correr el Backend, utiliza el siguiente comando:</p>
      <pre>
        <code>mvn spring-boot:run</code>
      </pre>
      <p>
        Una vez compilado el Backend, el archivo <code>.war</code> se encontrará
        en la carpeta <code>target</code>.
      </p>
      <p>Para compilar utilizar el siguiente comando:</p>
      <pre>
        <code>mvn clean package</code>
      </pre>

      <h3>Frontend</h3>
      <p>
        Para correr el Frontend es necesario tener instalado{" "}
        <strong>Node.js</strong> y <strong>npm</strong>.
      </p>
      <p>
        En el archivo <code>frontend/src/components/EmployeeList.tsx</code> hay
        una constante llamada <code>BASE_URL</code>. En caso de hacer deploy del
        backend hay que asegurarse que este URL corresponde al URL donde se está
        desplegando.
      </p>
      <p>Para ejecutar el front se puede usar el comando:</p>
      <pre>
        <code>npm run dev</code>
      </pre>

      <h3>APIs Utilizadas</h3>
      <p>
        Debido a problemas con la API propuesta (por el rate limit configurado),
        se decidió utilizar otra API que provee información similar. La única
        diferencia es que no asignaba un valor numérico para asociar al salario,
        por lo que se tomó el código postal (zip code). Además, se utilizó una
        API diferente para extraer las imágenes de cada usuario.
      </p>
      <ul className="ul-readme">
        <li>
          API usada:{" "}
          <a href="https://www.freetestapi.com/api/v1/users">
            https://www.freetestapi.com/api/v1/users
          </a>
        </li>
        <li>
          API imágenes:{" "}
          <a href="https://randomuser.me/api/?results=10">
            https://randomuser.me/api/?results=10
          </a>
        </li>
      </ul>
    </article>
  );
}
