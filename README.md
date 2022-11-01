# eMovie

## DEPENDENCIAS
Las librerías que se utilizan en el proyecto se encuentran implementadas en el *build.gradle* del módulo de ***core***, ya que este hereda las dependencias a todos los módulos que lo implementan. Para esto se utiliza el *keyword* ***api***, en vez de ***implementation*** 

### Librerías externas de Android:
Glide: usada para mostrar imágenes provenientes de urls. 
Repositorio: https://github.com/bumptech/glide

Retrofit: Se utiliza para realizar peticiones REST
Repositorio: https://github.com/square/retrofit

### Librerías de Android:
Room: usada para gestionar las bases de datos locales de la app.

Hilt: usada para la inyección de dependencias dentro de la app.

## ARQUITECTURA
### Data
En arquitectura limpia, es la capa encargada de gestionar los datos de la aplicación por medio de servicios web o locales, la estructura es como sigue:

|Nombre|Descripción|
|:-:|:-:|
|Datasourceimplementations: | Contiene las clases que implementan los métodos de gestión de los datos.|
|Usecasesimplementations: |Contiene las clases que implementan los métodos de los casos de uso y la lógica de negocio conectándolos con la capa de datos. |
|Services: | Son las interfaces de Retrofit que se utilizan en el proyecto para hacer las peticiones al servidor. |
|Dao: | Concentra las interfaces de Room que se utilizan para administrar las operaciones de bases de datos. |

### Domain
Es la capa encargada de manejar la lógica de negocio, su estructa es la siguiente:

|Nombre|Descripción|
|:-:|:-:|
|Datasourcebstractions: | Son las interfaces con los métodos utilizados para acceder a los servicios (web y locales) y proporcionar los datos a la lógica de negocio y los casos de uso. |
|Usecasesabstractions: | Son las interfaces donde se definirán los métodos utilizados para proporcionar la información a los casos de uso. |
|Entities: | Son los objetos utilizados para los request y los response de los servicios, además de objetos auxiliares para agrupar diferentes datos necesarios para realizar alguna acción en los usecases. |
|Database: | Contiene la definición y configuración de la base de datos. |

### Presentation
La capa de presentación contiene las clases y objetos referentes a las vistas:

|Nombre|Descripción|
|:-:|:-:|
|Activities: |Paquete que contiene las clases que extienden de BaseActivity la cual extiende de AppCompatActivity.|
|Adapters: |Contiene los adaptadores de cada módulo.|
|Fragments: |Contiene las clases que heredan de Fragment.|
|Models: |Contiene las clases de datos requeridas para las vistas.|
|ViewModels: | Contiene las clases que heredan de ViewModel.
