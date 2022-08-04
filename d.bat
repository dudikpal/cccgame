call cd src\front
call npm run ng build
call cd ..\..
call git add .
call @echo off
call set /p message=Please type commit message:
call git commit -m "%message%"
call git push heroku master