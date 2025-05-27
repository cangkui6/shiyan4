@echo off
echo Starting all services for CORS testing...

REM Start Eureka service registry (port 8761)
start "Eureka Registry - 8761" cmd /k "cd service-register_8761 && mvn spring-boot:run"
echo Service Registry starting on port 8761...
echo Waiting for Eureka to start (20 seconds)...
timeout /t 20

REM Start Provider service (port 8081)
start "Provider - 8081" cmd /k "cd service-provider_8081 && mvn spring-boot:run"
echo Provider service starting on port 8081...
echo Waiting for Provider to register with Eureka (15 seconds)...
timeout /t 15

REM Start Gateway service (port 8080)
start "Gateway - 8080" cmd /k "cd service-gateway_8080 && mvn spring-boot:run"
echo Gateway service starting on port 8080...
echo Waiting for Gateway to start and connect to Eureka (15 seconds)...
timeout /t 15

echo All services should now be up and running.
echo.
echo CORS test URLs:
echo - Test page: http://localhost:8080/cors-test.html
echo - Public API: http://localhost:8080/cors-test/public-api
echo - Login API: http://localhost:8080/cors-test/login
echo - Provider Hello API (requires token): http://localhost:8080/provider/hello
echo.
echo If services are not responding, wait a few more seconds for registration to complete.

pause 