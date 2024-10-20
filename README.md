# SHIFTLabApplication

## Описание проекта

Данный проект был разработан в рамках тестового задания для ШифтЛАБ.

Техническое задание данного проекта: SHIFTLab_Java.pdf

### Примечание

У меня так и не получилось корректно сконфигурировать 
централизованный запуск тестов, поэтому для запуска тестов приходиться
запускать каждый класс отдельно.

## Развертывание приложения

Для запуска понадобиться Intellij Idea и PostgreSql.
Далле создаем новую базу данных в Postgres, и в ней выполняем 
команды из файла ShiftLAB/postgres/init.sql.

Далее в файле ShiftLAB/src/main/resources/application.properties 
прописываем данные подключения к базе данных.

Приложение готово к работе!

# API

Коллекция со всеми примерами запросов доступна в Postman, по ключу:

https://api.postman.com/collections/36847770-0598cdcd-1dfa-4bfb-ad5d-4c95bebf3152?access_key=PMAT-01JAM3KB840DB0702A1BVWVKGE

## Seller

### GetAllSellers

/seller GET
    
ResponseBody:

    [
        {
            "id": 1,
            "name": "Galichkin",
            "contactInfo": "s_galichkin@mail.ru",
            "registrationDate": "2024-10-17T18:27:20.416916"
        },
        {
            "id": 2,
            "name": "Stepan",
            "contactInfo": "+79130084886",
            "registrationDate": "2024-10-17T18:27:24.389248"
        }
        
        ...

    ]

### GetSellerById

/seller/{id} GET

ResponseBody:

    {
        "id": 1,
        "name": "Galichkin",
        "contactInfo": "s_galichkin@mail.ru",
        "registrationDate": "2024-10-17T18:27:20.416916"
    }

### CreateSeller

/seller POST

RequestBody:

    {
        "name" : "Galichkin",
        "contactInfo": "s_galichkin@mail.ru"
    }
ResponseBody:

    {
        "id": 1,
        "name": "Galichkin",
        "contactInfo": "s_galichkin@mail.ru",
        "registrationDate": "2024-10-17T18:27:20.416916"
    }

### UpdateSeller

/seller/{id} PUT

RequestBody:

    {
        "name" : "Galichkin",
        "contactInfo": "s_galichkin@mail.ru"
    }

/seller/{id} DELETE

## Transaction

### GetAllTransactions

/transaction GET

ResponseBody:
    
    [
        {
            "id": 1,
            "seller": {
                "id": 1,
                "name": "Pavel1",
                "contactInfo": "Pavel@gmail.com1",
                "registrationDate": "2024-10-17T18:25:38.81305"
            },
            "amount": 100,
            "paymentType": "CARD",
            "transactionDate": "2024-10-17T18:25:48.058699"
        },
        {
            "id": 2,
            "seller": {
                "id": 2,
                "name": "Galichkin",
                "contactInfo": "s_galichkin@mail.ru",
                "registrationDate": "2024-10-17T18:27:20.416916"
            },
            "amount": 100,
            "paymentType": "CARD",
            "transactionDate": "2024-10-17T18:27:37.92746"
        }

        ...

    ]

### GetTransactionById

/transaction/{id} GET

ResponseBody:

    {
        "id": 1,
        "seller": {
            "id": 1,
            "name": "Pavel1",
            "contactInfo": "Pavel@gmail.com1",
            "registrationDate": "2024-10-17T18:25:38.81305"
        },
        "amount": 100,
        "paymentType": "CARD",
        "transactionDate": "2024-10-17T18:25:48.058699"
    }

### GetAllTransactionsBySellerId

/transaction/forSeller/{idSeller} GET

ResponseBody:

    [
        {
            "id": 1,
            "seller": {
                "id": 1,
                "name": "Pavel1",
                "contactInfo": "Pavel@gmail.com1",
                "registrationDate": "2024-10-17T18:25:38.81305"
            },
            "amount": 100,
            "paymentType": "CARD",
            "transactionDate": "2024-10-17T18:25:48.058699"
        },
        {
            "id": 2,
            "seller": {
                "id": 1,
                "name": "Pavel1",
                "contactInfo": "Pavel@gmail.com1",
                "registrationDate": "2024-10-17T18:25:38.81305"
            },
            "amount": 100,
            "paymentType": "CARD",
            "transactionDate": "2024-10-17T18:27:37.92746"
        }

        ...

    ]

### CreateTransaction

/transaction POST

RequestBody:
    
    {
        "sellerId" : 1,
        "amount" : 222,
        "paymentType": "CASH"
    }

ResponseBody:

    {
        "id": 1,
        "seller": {
            "id": 1,
            "name": "Pavel1",
            "contactInfo": "Pavel@gmail.com1",
            "registrationDate": "2024-10-17T18:25:38.81305"
        },
        "amount": 100,
        "paymentType": "CARD",
        "transactionDate": "2024-10-17T18:25:48.058699"
    }

## Analytics

### GetBestSellerForDuration

/analytics/bestseller GET

RequestBody:

    {
        "startTime": "2024-10-16T18:25:48",
        "endTime": "2024-10-18T18:27:43"
    }

ResponseBody:

    {
        "id": 3,
        "name": "Stepan",
        "contactInfo": "+79130084886",
        "registrationDate": "2024-10-17T18:27:24.389248"
    }

### GetLowSumSellers

/analytics/lowsumsellers?boundaryAmount={amount}

RequestBody:

    {
        "startTime": "2024-10-16T18:25:48",
        "endTime": "2024-10-18T18:27:43"
    }

ResponseBody:

    [
        {
            "id": 1,
            "name": "Galichkin",
            "contactInfo": "s_galichkin@mail.ru",
            "registrationDate": "2024-10-17T18:27:20.416916"
        },
        {
            "id": 2,
            "name": "Stepan",
            "contactInfo": "+79130084886",
            "registrationDate": "2024-10-17T18:27:24.389248"
        }
        
        ...

    ]