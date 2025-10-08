# Informe Mockito - Ejercicio 2 TP1
## Universidad Nacional de Lanús - Licenciatura en Sistemas
## Prueba de Software - TP Nº 1 - Año: 2025

**Estudiante:** Luca
**Grupo:** GRUPOXX - UNLA - Prueba de Software - TP1

---

## Instalación de Mockito en Proyecto Eclipse

### Método 1: Maven (Recomendado)
Agregar al `pom.xml`:
```xml
<properties>
    <junit.version>5.9.2</junit.version>
    <mockito.version>4.11.0</mockito.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>${mockito.version}</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Método 2: Gradle
```gradle
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.2'
    testImplementation 'org.mockito:mockito-core:4.11.0'
}
```

### Método 3: JARs Manuales
Descargar y agregar al Build Path:
- `mockito-core-4.11.0.jar`
- `junit-jupiter-engine-5.9.2.jar`

---

## Uso Básico de Mockito en Tests Unitarios

### Estructura Básica de un Test
```java
@ExtendWith(MockitoExtension.class)
public class MiClaseTest {

    @Mock
    private Dependencia mock;

    @InjectMocks
    private ClaseBajoPrueba objetoPrueba;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void miTest() {
        // Configurar comportamiento
        when(mock.metodo()).thenReturn("valor");

        // Ejecutar lógica
        String resultado = objetoPrueba.metodo();

        // Verificar resultado
        assertEquals("valor", resultado);

        // Verificar interacciones
        verify(mock).metodo();
    }
}
```

### Anotaciones Principales
- **`@Mock`**: Crea objetos simulados
- **`@InjectMocks`**: Inyecta automáticamente los mocks
- **`@BeforeEach`**: Se ejecuta antes de cada test
- **`@ExtendWith(MockitoExtension.class)`**: Habilita Mockito

---

## Métodos Más Utilizados

### Configuración de Comportamiento

#### when().thenReturn()
```java
// Retorno simple
when(objetoMock.metodo()).thenReturn("valor");

// Múltiples retornos
when(listaMock.size()).thenReturn(5).thenReturn(10);

// Con excepciones
when(objetoMock.metodo()).thenThrow(new Exception("Error"));
```

#### doReturn().when()
```java
// Para métodos void
doReturn("valor").when(objetoMock).metodo();
```

### Verificación de Interacciones

#### verify()
```java
// Verificar llamada
verify(objetoMock).metodo();

// Con parámetros específicos
verify(objetoMock).metodo(eq("parametro"));

// Cantidad de llamadas
verify(objetoMock, times(3)).metodo();
verify(objetoMock, never()).metodo();
```

### Argument Matchers
```java
// Tipos básicos
anyString(), anyInt(), anyFloat()

// Valores específicos
eq("valor"), eq(123)

// Nulos
isNull(), isNotNull()
```

---

## Aplicación Práctica en el Proyecto

### ClienteUnitTest
**Aplicación de Mockito:** Se utilizan mocks para aislar la clase Cliente de dependencias externas como el Sistema de ventas, permitiendo probar el comportamiento del cliente de forma independiente mediante configuración de respuestas simuladas.

### ProductoUnitTest
**Aplicación de Mockito:** Los mocks permiten probar el comportamiento del Producto con dependencias como Sucursal sin necesidad de objetos reales, configurando respuestas específicas para diferentes escenarios de testing.

### SistemaVentasIntegracionTest
**Aplicación de Mockito:** Para pruebas de integración se utilizan múltiples mocks que representan diferentes componentes del sistema, permitiendo verificar la interacción completa entre clases sin dependencias reales.

### Tests Pendientes por Implementar

**Ejercicio 1 requiere crear:**
- `VentaUnitTest` - Mocks de dependencias como Cliente, Producto, Vendedor
- `SucursalUnitTest` - Mocks de Direccion, Gerente, Lote
- `PedidoUnitTest` - Mocks de Venta, Cliente, Producto

**Ejercicio 3 requiere completar:**
- `SistemaVentasIntegracionTest` - Mocks de todas las clases del modelo para flujo completo

---

## Conclusión

Mockito es una herramienta esencial para pruebas unitarias efectivas en Java. Permite aislar componentes, controlar comportamiento y verificar interacciones. Con la configuración adecuada y el uso correcto de sus anotaciones y métodos, facilita la creación de tests robustos y mantenibles.

**Versión Mockito utilizada:** 4.11.0
**Compatible con:** JUnit 5.9.2
**Fecha:** Octubre 2025
