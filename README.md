# API Productos

## Descripción

Este proyecto es una API para la gestión de productos. Utiliza Spring Boot con Jersey para manejar las solicitudes HTTP y proporciona varios endpoints para la creación, lectura, actualización y eliminación de productos. Además, se implementa seguridad utilizando Spring Security, y se incluyen pruebas unitarias para garantizar el correcto funcionamiento del código.

## Endpoints

A continuación se detallan los endpoints disponibles en la API:

### **GET** `/api/products`

Obtiene todos los productos.

**Respuesta:**

- **Código 200 OK:** Lista de todos los productos.

### **POST** `/api/products`

Crea un nuevo producto.

**Cuerpo de la Solicitud:**

    {
      "name": "Product Name",
      "description": "Product Description",
      "price": 100.0
    }

**Respuesta:**

- **Código 201 Created:** El producto creado.

### **GET** `/api/products/{id}`

Obtiene un producto por su ID.

**Parámetros de Ruta:**

- `id`: ID del producto.

**Respuesta:**

- **Código 200 OK:** El producto con el ID especificado.
- **Código 404 Not Found:** Si el producto no existe.

### **PUT** `/api/products/{id}`

Actualiza un producto existente.

**Parámetros de Ruta:**

- `id`: ID del producto.

**Cuerpo de la Solicitud:**

    {
      "name": "Updated Product Name",
      "description": "Updated Product Description",
      "price": 150.0
    }

**Respuesta:**

- **Código 200 OK:** El producto actualizado.
- **Código 404 Not Found:** Si el producto no existe.

### **DELETE** `/api/products/{id}`

Elimina un producto por su ID.

**Parámetros de Ruta:**

- `id`: ID del producto.

**Respuesta:**

- **Código 204 No Content:** Si el producto fue eliminado exitosamente.
- **Código 404 Not Found:** Si el producto no existe.

## Uso de Pruebas Unitarias

El proyecto incluye pruebas unitarias para asegurar que la lógica del negocio y los endpoints funcionan correctamente. Las pruebas están ubicadas en el directorio `src/test/java` y utilizan JUnit y Mockito para realizar las pruebas y simulaciones.

**Ejecutar Pruebas:**

    ./mvnw test

## Uso de Spring Security

La API utiliza Spring Security para proteger los endpoints y garantizar que solo los usuarios autorizados puedan acceder a ciertos recursos. La configuración de seguridad se realiza en la clase `SecurityConfig`:

- **Autenticación**: Se utiliza autenticación basada en usuario y contraseña.
- **Roles**: Los roles se asignan para restringir el acceso a diferentes partes de la API.

**Ejemplo de configuración de seguridad:**

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                .withUser("productApi")
                .password("{noop}123456789")
                .roles("read");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                .antMatchers("/api/products/**").hasRole("read")
                .and()
                .formLogin();
        }
    }

## Dependencias

El proyecto utiliza las siguientes dependencias:

- **Spring Boot**: Framework principal para la construcción de la aplicación.
- **Jersey**: Implementación de JAX-RS para manejar solicitudes HTTP.
- **Spring Security**: Proporciona seguridad y autenticación.
- **JUnit**: Framework para pruebas unitarias.
- **Mockito**: Framework para crear mocks en las pruebas unitarias.

El archivo `pom.xml` incluye todas las dependencias necesarias para el proyecto.

## Uso de Jersey

Jersey se utiliza para manejar las solicitudes REST en la API. La configuración de Jersey se realiza en la clase `JerseyConfig`:

    @Configuration
    public class JerseyConfig extends ResourceConfig {

        public JerseyConfig() {
            register(ProductController.class);
        }
    }

## Cómo Ejecutar el Proyecto

Para ejecutar el proyecto, utiliza el siguiente comando:

    ./mvnw spring-boot:run

El proyecto estará disponible en `http://localhost:8080`.

## Contribuir

Si deseas contribuir al proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama para tus cambios (`git checkout -b feature/your-feature`).
3. Realiza tus cambios y haz commit (`git commit -am 'Add new feature'`).
4. Empuja tus cambios (`git push origin feature/your-feature`).
5. Abre un pull request en GitHub.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
