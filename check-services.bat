@echo off
echo Checking service status...

echo.
echo 1. Checking Eureka registry status
curl -s http://localhost:8761/eureka/apps | findstr "service-provider"
curl -s http://localhost:8761/eureka/apps | findstr "service-gateway"

echo.
echo 2. Testing service provider directly
curl -s http://localhost:8081/provider/hello

echo.
echo 3. Testing gateway routing
curl -s -H "X-Auth-Token: valid-token-123456" http://localhost:8080/provider/hello

echo.
echo 4. Checking CORS configuration
curl -v -H "Origin: http://localhost:3000" http://localhost:8080/cors-test/public-api

echo.
echo Service check complete.
pause 