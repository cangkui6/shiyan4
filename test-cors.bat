@echo off
echo Testing CORS configuration...

echo.
echo 1. Testing public API (no authentication required)
curl -v -H "Origin: http://localhost:3000" http://localhost:8080/cors-test/public-api

echo.
echo 2. Testing login API
curl -v -H "Origin: http://localhost:3000" -X POST -d "username=admin&password=password" -H "Content-Type: application/x-www-form-urlencoded" http://localhost:8080/cors-test/login

echo.
echo 3. Testing protected API with token
curl -v -H "Origin: http://localhost:3000" -H "X-Auth-Token: valid-token-123456" http://localhost:8080/provider/hello

echo.
echo Testing complete. Check for Access-Control-Allow-Origin headers in the responses.
pause 