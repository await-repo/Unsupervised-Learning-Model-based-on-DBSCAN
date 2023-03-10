## **Modelo de Aprendizaje no Supervisado basado en DBSCAN**

Weka es un Software dedicado al machine learning desarrollado en Java por la **universidad de Waikato**

### **Resumen**

El proposito de este proyecto fue utilzar la API de weka para diseñar un sistema capaz de encontrar un modelo de aprendizaje no supervisado basado en DBSCAN que optimice la generación de clusters orientado a la agrupación de pixeles asociados a imágenes de incendios forestales.

### **¿Cómo ejecutar este proyecto?**

1. Clona el repositorio en tu maquina local.

2. Instalar el Jar de Weka, esta parte es la más importante ya que **DBSCAN** y sus herramientas de entrenamiento estan incluidas en la libreria (Jar) de Weka. Para instalar Weka en nuestro proyecto, tenemos dos opciones:

    * Descargar manualmente Weka desde su sitio web y agregar el Jar manualmente al proyecto. Visite la página de Weka para más información https://www.cs.waikato.ac.nz/ml/weka/

    * Agregar la dependencia de Weka al **POM.xml** y posteriormente instalar la nueva dependencia. Puedes acceder desde el sitio web para copiar la dependencia.

        Visite el repositorio de maven para descargar la version más reciente de weka https://mvnrepository.com/artifact/nz.ac.waikato.cms.weka/weka-stable/3.8.0
    
        Actualmente (enero 2023) añadiendo el siguiente fragmento al POM.xml de tu proyecto deberías poder instalar Weka.

        ```xml
        <!-- https://mvnrepository.com/artifact/nz.ac.waikato.cms.weka/weka-stable -->
        <dependency>
            <groupId>nz.ac.waikato.cms.weka</groupId>
            <artifactId>weka-stable</artifactId>
            <version>3.8.0</version>
        </dependency>
        ```

3. Si hasta ahora tu editor de código o IDE no muestra errores de sintaxis significa que se ha instalado correctamente Weka dentro de tu proyecto. 

    Debemos observar principalmente la clase **`dbscan`** ya que aquí es donde se importan las librerias de Weka. Si observamos errores en las sigueientes lineas

    ```java
    import weka.clusterers.ClusterEvaluation;
    import weka.clusterers.DBSCAN;
    import weka.core.Instances;
    import weka.core.converters.ConverterUtils;
    ```
    Debemos de revisar de nuevo el proceso de instalación de la libreria Weka. En caso contrario, podemos continuar.

4. Con el proyecto descargado y la libreria de Weka dentro del proyecto, sin errores de importacion de liberias o errores de sintaxis. Ahora podemos configurar algunas lineas del proyecto. 

    * En la clase **`test`** del package **`DBSCAN`** vamos a modificar la siguiente linea.

        ```java
        String route = "Your Path\\Folder\\sample.arff";
        ```

        > #### **Nota: Archivo con nuestra muestra .arff**
        > La ruta debe contener la dirección hacía tu archivo .arff


    * En la clase **`Population`** del package **`GeneticAlgorithm`**, dentro del método **`saveGeneration`** vamos a modificar la siguiente linea.

        ```java
        String route = "Your Path\\Folder\\generations.txt";
        ```
        > #### **Nota: Archivo con generaciones .txt**
        > La ruta debe contener la dirección hacía el archivo que contiene las generaciones (en formato .txt)

    **Debera agregar la ruta absoluta de la carpeta donde se encuentren todos tus experimentos.** Más adelante explicare la extension y tipo de archivo para los experimentos, así como su nomemclatura.

5. Si todo a salido bien hasta ahora, **podemos iniciar con la parte de los experimentos**


### **Archivo sample.arff y generations.txt**

Archivo **generations.txt**

El archivo **generations.txt** contiene los datos de un individuo escrito como una cadena de texto en valor de bits (base 2) . Por ejemplo **`11111001111000100`**, existen 3 conjuntos de bits dentro de la cadena que indican lo siguiente:

* 4 bits de parte entera de la épsilon.
* 3 bits de parte decimal para la épsilon.
* 10 bits para representación de los puntos mínimos.

Cada cadena de bit esta compuesta por 17 bits, cada linea dentro del archivo debera representar a un único individuo.

Archivo **sample.arff**

El archivo **sample.arff** contiene el dataset completo con los datos de las propiedades recolectadas de una imagen, en este caso las metricas recolectadas fueron la escala **RGB (Red, Green, Blue)**, dentro del data set cada línea contiene tres atributos separados por coma indicando una cantidad númerica en escala de 0 a 255. Por ejemplo **`133,104,100`** en el orden Red, Green y Blue respectivamente.
