# order-ms
Order Microservice Listener

## payload message 
```json
{
    "orderId": 1,
    "customerId": 101010,
    "items": [
        {
            "product": "produto X",
            "price": 10.1,
            "quantity": 2
        }
    ]
}
```
## tech
- Java
- Spring
- RabbitMq
- MongoDb
