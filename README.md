# OneBox Stock Manager API Test
-----------------------

## Introducción

Esta aplicación gestiona el stock de un almacén de productos genéricos (pensado en productos electrónicos principalmente).

El modelo consta de Manufacturer (fabricante) y Product (producto). Cada producto está asociado a un fabricante y un fabricante puede estar asociado a varios productos.

La API Rest implementa las operaciones CRUDS (Create, Read, Update, Delete, Search). 

A continuación se describen las principales funcionalidades de la API.


## Manufacturer API
-----------------------
### Agregar Nuevo Manufacturer

- **HTTP Method**: `POST`
- **PATH**: `/api/manufacturer`
- **Content-Type**: `application/json`

##### Ejemplo: JSON para crear un Manufacturer (cuerpo del POST)
```
#!json
{
    "manufacturer_id":10,
    "manufacturer_name":"Sony"
}
```

### Obtener Un Manufacturer Existente

- **HTTP Method**: `GET`
- **PATH**: `/api/manufacturer/{id}`

> {id} representa el código (PK) del Manufacturer.

##### Ejemplo: JSON de respuesta
```
#!json
{
    "manufacturer_id":10,
    "manufacturer_name":"Sony"
}
```

### Obtener Todos Los Manufacturer Existentes

- **HTTP Method**: `GET`
- **PATH**: `/api/manufacturer/all`

##### Ejemplo: JSON de respuesta
```
#!json
[
    {
        "manufacturer_id": 10,
        "manufacturer_name": "Sony"
    },
    {
        "manufacturer_id": 20,
        "manufacturer_name": "Apple"
    },
    {
        "manufacturer_id": 30,
        "manufacturer_name": "Samsung"
    }
]
```

### Eliminar Un Manufacturer

- **HTTP Method**: `DELETE`
- **PATH**: `/api/manufacturer/{id}`

> {id} representa el código (PK) del Manufacturer.

### Actualiza Un Manufacturer

- **HTTP Method**: `PATCH`
- **PATH**: `/api/manufacturer`
- **Content-Type**: `application/json`

##### Ejemplo: JSON para actualizar un Manufacturer (cuerpo del POST)
```
#!json
{
    "manufacturer_id":10,
    "manufacturer_name":"Sony 2"
}
```

## Product API
-----------------------

### Agregar Nuevo Product

- **HTTP Method**: `POST`
- **PATH**: `/api/product`
- **Content-Type**: `application/json`

##### Ejemplo: JSON para crear un nuevo Product (cuerpo del POST)
```
#!json
{
    "product_code": 1000,
    "name": "iPhone 5S",
    "price": 799.99,
    "manufacturer_id": 10,
    "type": "CELL_PHONE",
    "stock": 876
}
```

### Obtener Un Product Ya Existente

- **HTTP Method**: `GET`
- **PATH**: `/api/product/{id}`

> {id} representa el código (PK) del Producto.

##### Ejemplo: JSON de respuesta
```
#!json
{
    "product_code": 1000,
    "name": "iPhone 5S",
    "price": 799.99,
    "manufacturer_id": 10,
    "manufacturer_name": "Sony",
    "type": "CELL_PHONE",
    "stock": 876
}
```

### Obtener La Lista De Todos Los Productos Existentes

- **HTTP Method**: `GET`
- **PATH**: `/api/product/all`

##### Ejemplo: JSON de respuesta
```
#!json
[
    {
        "product_code": 1000,
        "name": "iPhone 5S",
        "price": 799.99,
        "manufacturer_id": 10,
        "manufacturer_name": "Sony",
        "type": "CELL_PHONE",
        "stock": 876
    },
    {
        "product_code": 1001,
        "name": "iPhone 6 32GB",
        "price": 799.99,
        "manufacturer_id": 20,
        "manufacturer_name": "Apple",
        "type": "CELL_PHONE",
        "stock": 100
    }
]
```

### Elimina Un Producto Existente

- **HTTP Method**: `DELETE`
- **PATH**: `/api/product/{id}`

> {id} representa el código (PK) del Producto a eliminar.


### Actualiza Un Producto Existente

- **HTTP Method**: `PATCH`
- **PATH**: `/api/product`
- **Content-Type**: `application/json`

##### Ejemplo: JSON para crear un nuevo Product (cuerpo del POST)
```
#!json
{
    "product_code": 1000,
    "name": "iPhone 5S 64GB",
    "price": 799.99,
    "manufacturer_id": 10,
    "type": "CELL_PHONE",
    "stock": 876
}
```

### Agregar Stock a un Producto

- **HTTP Method**: `GET`
- **PATH**: `/api/product/stock_add/{id}/{amount}`

> {id} representa el código (PK) del Producto.

> {amount} el total de stock a agregar.


### Quitar Stock a un Producto

- **HTTP Method**: `GET`
- **PATH**: `/api/product/stock_subtract/{id}/{amount}`

> {id} representa el código (PK) del Producto.

> {amount} el total de stock a reducir.


### Obtener Productos Con Bajo Stock

- **HTTP Method**: `GET`
- **PATH**: `/api/product/under_stock/{amount}`

> {amount} retorna todos los Productos con stock igual o inferior a amount.

##### Ejemplo: JSON con listado de bajo stock.
```
#!json
[
    {
        "product_code": 1000,
        "name": "iPhone 5S",
        "price": 799.99,
        "manufacturer_id": 10,
        "manufacturer_name": "Sony",
        "type": "CELL_PHONE",
        "stock": 10
    },
    {
        "product_code": 1001,
        "name": "iPhone 6 32GB",
        "price": 799.99,
        "manufacturer_id": 20,
        "manufacturer_name": "Apple",
        "type": "CELL_PHONE",
        "stock": 5
    }
]
```


## Mensajes de Error
-----------------------
Si se presenta algún error con los datos de entrada el sistema retornará un mensaje de error.

##### Ejemplo: mensaje de error.
```
#!json
{
    "responseCode": 404,
    "message": "'id' debe ser mayor a 0. 'name' es requerido. "
}
```

## Compilacioón y Distribución
-----------------------

El proyecto utiliza Maven para la administración de dependencias y de compilación. Una vez teniendo Maven instalado para compilarlo hay que ejecutar el siguiente comando desde la raíz del proyecto:

~~~
mvn package
~~~

Este comando compila, ejecuta las pruebas unitarias y crea el paquete .war `/target/OneBoxStockAPI.war`.

El war puede ser instalado en un Tomcat y no es necesario la configuración de un BD ya que utiliza una en memoria (HSQLDB).


###### Oreste Luci



