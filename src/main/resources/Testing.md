## Testing
Deben respetar el principio de la programacion orienta a objetos de **OPEN-CLOSE**:
- *OPEN*: abierto a la extension.
- *CLOSE*: cerrado a la modificacion.

Existen varios tipos de pruebas/test:
- *Unitarias*: funciones cuyo objetivo es probar componentes especificos. Dentro de estas podemos encontar 3 tipos de enfoques:
  - *TDD*: primero se escribe la prueba para que esta falle y despues se codifica lo minimo e indispensable para que esta pase; para luego poder seguir refactorizando el codigo.
  - *BDD*: es similar al TDD, pero este se basa en porque se debe escribir el codigo que pase la prueba y como se deberia comportar dicho codigo.
  - *ATDD*: este extiende el alcance del BDD, ya que profundiza mas en la busqueda de lo que se esta haciendo y no solo en hacerlo en forma correcta (todo el equipo discute en colaboracion los criterios de aceptacion y luego se dividen las reponsabilidades).
- *Integracion*: verifican que los distintos componentes de un sistema funcionen juntos correctamente.
- *End To End*: Simulan la interaccion que hace el usuario con la aplicacion (se ingresan INPUTS y se esperan/comparan los OUTPUTS)

----------------------------------------------------------------

### JUnit 5
**JUnit test** es una libreria de java para escribir y ejecutar repetibles pruebas unitarias, permite escribir nuestras pruebas mucho mas facil, ya que dentro de una clase podemos tener varios tests de una clase.

La arquitectura de *JUnit* se compone de:
- *JUnit Platform*: es el CORE del framework el cual recibe las solicitudes de los tests (solo los IDE´s se comunican con el).
- *JUnit Jupiter*: el encargado de mandar solicitudes de tests escritos en JUnit 5.
- *JUnit Vintage*: el encargado de mandar solicitudes de tests escritos en JUnit 4, 3 o posteriores.

Ademas el JUnit Platform puede recibir solicitudes de testing que provengan de otros Frameworks.

#### Dependencia
Debemos agregar en el **pom.xml** lo siguiente:

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>SEGUN_CORRESPONDA</version>
        </dependency>
    </dependencies>

Las clases de testeo tienen que ir en el package: *src>test>java>"NOMBRE_DEL_PAQUETE_PRINCIPAL">"NomClaseTest"*.

Para generar cada clase de test podemos crearla desde la clase de desarrollo con **"alt+insert">generate>Test...**.

#### Anotaciones
Algunas de las anotaciones que existen en JUnit 5 son:
- **@Test**: indicamos al framework que el metodo corresponde a una clase de test. `@Test void nomMetodoTest() {}`.
- **@DisplayName("Mensaje")**: permite mostrar por terminal un mensaje que brinde informacion mas detallada del metodo de testeo que se realizo y/o va a realizar.
- **@Disable**: indicamos que el metodo siguiente va a ser ignorado (no se va a ejecutar) e indica esta cuestion por consola.
- **@BeforeAll**: indicamos que el proximo metodo se va a ejecutar *ANTES de todos los metodos* test.
- **@BeforeEach**: indicamos que el proximo metodo se va a ejecutar *SIEMPRE ANTES de cada metodo* test.
- **@AfterEach**: indicamos que el proximo metodo se va a ejecutar *SIEMPRE DESPUES de cada metodo* test.
- **@AfterAll**: indicamos que el proximo metodo se va a ejecutar *DESPUES de todos los metodos* test.
- **@Enabled···(Condicion)**: todas las posibilidades que posee la anotacion, permiten definir cuando realizar un metodo segun la *Condicion* que se le indique dentro de los parentesis.
- **@Disabled···(Condicion)**: todas las posibilidades que posee la anotacion, permiten definir cuando no realizar un metodo segun la *Condicion* que se le indique dentro de los parentesis.
- **@Nested**: indicamos que la siguiente clase es una clase anidada.
- **@RepeatedTest(value=Cantidad_de_veces, name="Mensaje_Personalizado")**: permite repetir la cantidad de veces que se indique el test (se recomiendo si poseemos un metodos o un dato que puede llegar a ser aleatorio).
- **@ParameterizedTest(name = "Mensaje_Personalizado")**: sirve para realizar el test con un parametro indicado, el cual puede ir variando.
- **@ValueSource(Tipo_De_Dato = {Datos})**: le indica al **ParameterizedTest()** los parametros que tiene que utilizar para el metodo.
- **@CsvSource({Indice,Valor})**: le indica al **ParameterizedTest()** los parametros que tiene que utilizar para el metodo.
- **@CsvFileSource(resources = "/NomArchivo.csv")**: le indica al **ParameterizedTest()** el archivo de donde tiene que sacar los parametros para utilizar el metodo.
- **@MethodSource("nombreLista)**: lee los valores definidos en un atributo privado estatico de tipo lista.
- **@Tag("Nom_Etiqueta")**: se puede utilizar para asignarle una etiqueta tanto a clases anidadas como a metodos; esto sirve si quisieramos que corriera los test's de con cierta etiqueta (puede haber la cantidad de etiquetas que se necesiten).
- **@TimeOut(value = Cantidad, unit = TimeUnit.Unidad)**: define que cantidad de tiempo tiene el metodo realizar el test.

Una cuestion a tener en cuenta en el ciclo de vida de las anotaciones **BEFORE...** y **AFTER...**, es que los metodos **...ALL** se ejecutan una sola vez dentro de la clase test, y los metodos **...EACH** se van a ejecutar la cantidad de veces que sean necesarias para cada metodos definido dentro de la clase test. 

#### Metodos
Algunos metodos que existen en JUnit 5 son:
- **assertEquals(valor_esperado, valor_actual)**: devuelve TRUE si los valores que se pasan son iguales.
- **assertNotEquals(valor_esperado, valor_actual)**: devuelve TRUE si los valores que se pasan son distintos.
- **assertTrue(expresion_booleana)**: devuelve TRUE si la expresion que le pasamos es TRUE.
- **assertFalse(expresion_booleana)**: devuelve TRUE si la expresion que le pasamos es FALSE.
- **assertNotNull(valor_actual)**: devuelve TRUE si el valor_actual no es nulo.
- **assertThrows(NomClaseExcepttion.class, expresion lambda, MENSAJE_RESPUESTA_EXCEPCION)**: devuelve el tipo de exception que les pasamos por parametro.
- **assertAll(Expresiones_Lambdas)**: recibe varias expresiones lambdas (donde en cada una el metodo realizado, dentro de llaves si son necesarios, son distintos "assert...") y permite desplegarlas a todas e indicar puntualmente cual falla, en el caso que lo haga.
- **assertTimeOut(Duration.Tipo_Unidad(Cantidad), Expresion_Lambda)**: define que tiempo tiene el test para realizar la Expresion_Lambda.
- **assumeTrue(Condicion)**: verifica la *Condicion* booleana, si esta es TRUE permite ejecutar el test.
- **assumeFalse(Condicion)**: verifica la *Condicion* booleana, si esta es FALSE permite ejecutar el test.
- **assumingThat(Condicion, Expresiones_Lambdas)**: verifica la *Condicion* booleana, si esta es TRUE permite ejecutar la Expresiones_Lambdas.

La Expresiones_Lambas poseen la siguiente forma: (expresiones lambdas: `() -> {}, () -> {}, () -> {}, ···`).

Para el **assertThrows()**, la expresion lambda es la siguiente: `() -> {metodo_que_arroja_la_exception()}`).

A todos los metodos `assert...()` le podemos agregar como ultimo parametro un mensaje personalizado que va a mostrarse si el metodo llegara a fallar, esto genera que el sistema consuma mas memoria por instanciar siempre dicho mensaje; podemos solventar esta desventaja mediante el uso de expresiones lambda: `() -> "MENSAJE"`.

Dentro de la clase de test's podemos tener clases anidadas (INNERCLASS) donde podemos sub-agrupar metodos que poseean caracteristicas comunes o tipos de pruebas similares. A su vez tambien puede utilizar la anotacion `@DisplayName()` para indicar un texto mas descriptivo de la clase anidada.

Para que el test con las anotaciones **@ParameterizedTest()** y **@···Source()** funcione, este debe recibir una variable con el tipo de dato que se indica en el **@···Source()** correspondiente.

Para el **@CsvFileSource()** el archivo tiene que estar en la package resources: *src>main>resources>NomArchivo.csv*

Para el **@CsvSource()** en vez de pasarle un indice y un valor, le podemos pasar los valores que deseemos; ej: `@CsvSource({Valor1, Valor2, Valor3, ···}) void testEjemplo(Tipo_Dato_1 var1, Tipo_Dato_2 var2, Tipo_Dato_3 var3, ···){}`. Lo mismo se puede hacer para el **@CsvFileSource()** para no pasarle un solo valor a la vez.

Para ejecutar los test's con cierto **@Tag()** debemos configurar el tipo de test's que se van a ejecutar.

JUnit5 nos brinda dos clases que permiten agregar/mostrar mas informacion acerca de los test's que estamos realizando, estos son **TestInfo** y **TestReporter**.

#### Plugin extra
Podemos agregar el siguiente plugin de *MAVEN* para realizar los test de *JUnit* por consola, brindando la posibilidad de no correr cada clase o tag de test:

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId
                <version>Segun_Corresponda</version>
                <configuration>
                    <groups>Etiqueta_Que_Queramos_Ejecutar</groups>
                </configuration>
            </plugin>
        </plugins>
    </build>

----------------------------------------------------------------

### Mockito
**Mockito** es un framework de pruebas que nos permite crear objetos simulados (*mock*) en un entorno controlado y determinado, a los mismos les podemos dar un comportamiento deseado.
A su vez tambien podemos tener un *spike* el cual son un hibrido entre el objeto real y un mock (parte del comportamiento puede ser del metodo real y la otra parte necesaria puede ser mockeadas).

Lo fuerte de **Mockito** es que nos va a permitir mediante los *mock's* definir en cada metodo de test una variable que tenga instanciada datos para la prueba, haciendo no tengamos que leer desde algun otro metodo o lugar estos datos (los mock's suele definirse en clases/interfaces de tipo *Repository*).
Para reutilizar la misma instancia de datos lo que podemos hacer es definir una clase "Datos" en *src>test>java>org...>Datos*, la cual tenga como `public final static` variables con distintos tipos de datos (Constantes); de esta manera en la clase test invocamos `Datos.CONSTANTE`.

Algo a tener en cuenta es que no se puede hacer un *mock* de cualquier metodo, estos deben ser de tipo *public* o *default*.

Una buena practica y/o patrones a seguir en el testeo unitario con Mockito y JUnit, es seguir la estructura del *Arrange|Act|Assert*:
- **Arrange**: definimos todo lo necesario para el test (variables, atributos, metodos, etc); por ejemplo: `when(EntityMock.METODO()).thenReturn(VALOR);`
- **Act**: definimos la accion que queremos hacer y/o corroborar; por ejemplo:  `TIPO_DATO variable = EntityMock.METODO();`.
- **Assert**: evaluamos el valor definido con el valor esperado; por ejemplo: `assertEquals(VALOR,variable);`.

O *Given|When|Then* (este se suele utilizar cuando estamos realizando test orientados con BDD):
- **Given**: definimos todo lo necesario para el test (variables, atributos, metodos, etc); por ejemplo: `given(EntityMock.METODO()).willReturn(VALOR);`
- **When**: definimos la accion que queremos hacer y/o corroborar; por ejemplo:  `TIPO_DATO variable = EntityMock.METODO();`.
- **Then**: evaluamos el valor definido con el valor esperado; por ejemplo: `assertEquals(VALOR,variable);`.

#### Dependencia
Ademas de la dependencia de *JUnit 5* hay que agregar dos dependencias de *Mockito*:

     <dependencies>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>SEGUN_CORRESPONDA</version>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>SEGUN_CORRESPONDA</version>
        </dependency>
    </dependencies>

Para ambas dependencias se debe utilizar la misma version.

#### Funcionamiento
- Para simular un *mock* debemos definir la variable de prueba con un valor de tipo *Mockito*: `ClassEntity entity = Mockito.mock(ClassEntity.class);`, de esta manera *Mockito* va a generar una instancia simulada de la clase/interfaz que este entre parentesis.
- Una vez instancia la variable simulada, debemos darle un comportamiento al mock: `when(entity.METODO()).thenReturn(Variable_Local_Con_Datos);`.
  - En el caso que querramos verificar si el metodo nos devuelve una excepcion, debemos usar: `when(entity.METODO()).thenThrow(EXCEPTION);`.
  - Otro caso es cuando queremos llamar al *metodo real* para que nos devuelva el valor/comportamiento definido, esto lo hacemos con: `when(entity.METODO()).thenCallRealMethod();`
- Otro metodo que posee **Mockito** es el `verify(entity).METODO()`, este metodo lo que hace es verificar si el *METODO()* es invocado con exito (si se invoca con exito pasa el test).
  - En el caso que se quiera evalauar si el metodo se llamada cierta cantidad de veces, en vez de hace una llamada por cada vez necesitada, se puede simplificar utilizando lo siguiente: `verify(entity,times(CANTIDAD_VECES)).METODO();`.
    - En la cantidad de veces podemos especificar que cantidad minima, maxima o nunca se puede ejecutar: `atLeast(CANTIDAD)`, `atMost(CANTIDAD)` y `never()` respectivamente.
- Al momento de definir el comportamiento de un *mock*, le podemos dar mas complejidad, esto lo hacemos invocando la clase **Answer()**; por ejemplo: `when(classEntity.METODO()).then(new Answer<TipoDato>(){@Override public TipoDato answer(InvocationOnMock inv) throws Throwable{···}});`.
  - Una manera (objetivamente mas prolija) es definir primero el objeto de tipo *Answer* (`Answer<TIPO_DATO> answer = new Answer<TIPO_DATO>{···};`) y despues pasarlo como atributo a la llamada (`when(···).thenAnswer(answer);`).
-Luego de cada llamada **when()···**, tiene que ir la llamada a los *assert···*/*assume···*.

- Con **@Mock** instanciamos los atributos de tipo *Mockito* (los de tipo *Repository*).
- Con **@InjectMocks** injectamos los *mock's* instanciados anteriormente en el atributo seleccionado (el de tipo *Service*).
- Para poder usar las anotaciones de **Mockito** debemos habilitarlas, esto lo hacemos desde el metodo `@BeforeEach void setUp()` con la siguiente llamada: `MockitoAnnotations.openMocks(this);` o con `MockitoAnnotations.initMocks(this);`. La otra manera es usar la anotacion **@ExtendWith(MockitoExtension.class)** en la clase de test.
- Con **@Captor** podemos definir una variable que podemos utilizar para capturar los valores de los argumentos (son esecncialmente utiles cuando utilizamos *callback*): `@Captor private ArgumentCaptor<TIPO_DATO> captor;`.
  - Para capturar el valor debemos usar: `captor.capture()`
  - Para ver el valor capturado debemos usar: `captor.getValue().TIPO_DATOValue()`
- Con **@Spy** definimos al atributo como un *spike*.
- Con **@**

**Ejemplo de mock**:

    @InjectMocks
    private Suma suma;
    @Mock
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp() {
      MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void sumaTest() {
        // primero validamos que el tipo de dato de los valores sean aceptados por el metodo.
      when(validNumber.check(4)).thenReturn(true);
      when(validNumber.check(5)).thenReturn(true);
        // verificamos si se llamaron al metodo con exito (FORMA LARGA)
      verify(validNumber).check(4);
      verify(validNumber).check(5);
        // verificamos si se llamaron al metodo con exito (FORMA CORTA)
      verify(validNumber,times(2)).check(4);
        // llamamos al metodo con los valores verificados anteriormente
      int result = add.suma(4,5);
        // comparamos los resultados para verificar el funcionamiento correcto del metodo.
      assertEquals(9,result);
    }


### Baeldung Testing


#### JUnit5

Hay dos formas de realizar pruebas de excepciones en JUnit 5, las cuales podemos implementar usando el método **assertThrows()**, el primer ejemplo verifica los detalles de la excepción lanzada y el segundo valida el tipo de excepción:

    @Test
    void shouldThrowException() {
      Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
        throw new UnsupportedOperationException("Not supported");
      });
      assertEquals("Not supported", exception.getMessage());
    }
    
    @Test
    void assertThrowsException() {
      String str = null;
      assertThrows(IllegalArgumentException.class, () -> {
        Integer.valueOf(str);
      });
    }


---------------------------------------------------------------------------------------------------------------

Con **@DataJpaTest** indicamos que vamos a testear solo componentes de la parte de persistencia (Se utilizaria para testear las *Entities* y los *Repository*).