@echo off
chcp 65001 > nul
echo 开始测试Gateway的负载均衡效果...
echo 将连续发送10次请求到Gateway，观察请求是否分发到不同的Provider实例...
echo.

mkdir temp 2>nul

for /l %%i in (1, 1, 10) do (
    echo 发送第 %%i 次请求...
    curl -s http://localhost:8080/lb-test > temp\response.txt
    type temp\response.txt
    echo.
    timeout /t 1 > nul
)

rmdir /s /q temp 2>nul

echo.
echo 负载均衡测试完成。
pause 