# Name

Exchange Rate of currency.

# Description

To implement a REST service to display exchange rates for our
customers. The service receives three parameters: two currencies and a date using the
URL path. The format of the URL path is

/api/exchange-rate/{date}/{baseCurrency}/{targetCurrency}

The REST service returns:
1. The exchange rate of the requested date.
2. The average of the five days before the requested date (excluding Saturday and
Sunday ) and the exchange rate trend.

The exchange rate trend is determined using following definition:
 . Descending: when the exchange rates in the last five days are in strictly descending order.
 . Ascending:  when the exchange rates in the last five days are in strictly ascending order
 . Constant:   when the exchange rates in the last five days are the same
 . Undefined:  In other cases.

Basic Features

 . Allow users to query the exchange rate using two currencies and a date using the API.
 . Only dates between 2000-01-01 and yesterday are allowed. 
 The API of https://exchangeratesapi.io/ should be used as data source to implement our REST service. 
 Only the currencies supported by https://exchangeratesapi.io/ can be used.

Return an error in case of incorrect input parameters the error should be returned using the format of http://jsonapi.org (status, title
and description are required, other fields are optional)

All successful queries should be persisted in the DB. 
The customer can get historical information using two API’s, one for the daily information and other for the monthly information.
. daily: /api/exchange-rate/history/daily/{yyyy}/{MM}/{dd}
. monthly: /api/exchange-rate/history/monthly/{yyyy}/{MM}

## Requirements
For building and running the application you need:
JDK 1.8
Maven 3

## Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.exchange.rates.RatesApplication class from your IDE.
Alternatively you can use below command [more information see](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins.html#build-tool-plugins-maven-plugin) .

```bash
mvn spring-boot:run
```

## Usage

```python
Spring Security will ask to to enter UserName & Password
User Name: admin
Password: password

To see Data in browser go to http://localhost:8085/h2-console

API document is written in swagger 
please go to http://localhost:8085/swagger-ui.html#/

How to Dockerize Spring Boot Application
Go to docker console inside project target directory run the following command 

Build Docker Image
$ docker build -t rates.jar .

Check Docker Image
$ docker image ls

Run Docker Image
$ docker run -p 9090:8085 rates.jar

In the run command, we have specified that the port 8085 on the container should be mapped to the port 9090 on the Host OS.
```

## Packaging & run  
```
mvn clean package

Default packaging is jar and save it in /target directory 
Copy the jar file. Put any directory go to that directory and run the command:  java -jar rates-0.0.1-SNAPSHOT.jar
Open the browser http://localhost:8085/api/exchange-rate/2020-05-28/EUR/INR
It will ask UserName: admin  & Password: password  then click Singin

```

## License
[Public](https://test.com/licenses/)