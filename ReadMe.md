#Weather API

A spring boot application which exposes one RestEndpoints to fetch weather forecast for the current and next 7 days

### <U>There are below 3 functionalities implemented as part of this task</U>
```
1. For the given lat / long, parse the API data and find the warmest day over the next 7 days.

2. If multiple days have the same highest temperature, then go for the day that has the warmest temperature and the lowest humidity.

3. If multiple days have both the warmest temperature and lowest humidity, then display the first of those days.
```

### <U>Notes</U>:-
```
Connect to the OpenWeather API found here (https://openweathermap.org/api/one-call-api). You can use one of the API keys provided.
If none of these work you can register for free with your email address

Your task is to connect to the forecast API above, to parse the response and display a result.

Allow the end user to specify a given latitude and longitude. The parameters can be specified via a web form, RESTful interface or other method you deem appropriate to receive the user input.
[Inshur lat/long: 50.824955973889, -0.13878781625840952]

The output can be textual or graphical, html or json as you see fit. There is no right or wrong way to approach this.
You should be able to show that your code would work under different values returned from the API.
```

### <U>Installation</U>

The application is built using gradle and all the dependencies will be added automatically

####Build the application:
```
gradle build
```

For Running Application:
```
$ gradle bootrun

```

#### Use PostMan to test the application using the below API endpoint:

```
http://localhost:8080/current-weather?lat=50.824955973889&lon=-0.13878781625840952
```