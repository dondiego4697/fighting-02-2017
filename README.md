# fighting-02-2017
## Игра "StepFight"
## Пошаговая стратегия, в которой игроки на каждом ходу выбирают стратегию из 5-ти действий и сражаются!
## Команда:
* **Валитов Ришат**
* **Степанов Денис**
* **Фомичев Егор**
* **Чернов Андрей**

## API
### /api/user
#### Авторизация пользователя  
>* /login 
>   * Request: 
        { "login":"test1",
          "password":"pass2" }
>   * Responce:
        { "status": "200 OK", "user":{UserObject}}        
         200 OK - удачный вход  
         404 Not Found - пользователь не найден  
         "user" - Json объект пользователя 
          
#### Регистрация пользователя  
>* /signup
>    * Request: 
        { "login":"test1",
          "password":"pass2"}
>    * Responce:
        { "status": "200 OK"}        
         200 OK - удачная регистрация  
         403 Forbiden - Не удалось зарегистрироваться  

#### Получение пользователя текущей сессии  
>* /get
>    * Request: 
            
>    * Responce:
            { "status": "200 OK", "user":{UserObject}}        
             200 OK - удачная операция  
             401 Unauthorized - Пользователя с таким ключем нет в сессии  
             400 Bad Request - запрос не понят  

#### Обновление информации о пользователе  
>* /update
>    * Request: 
            {"login":"test1", "newlogin":"test2"}  
>    * Responce:
            { "status": "200 OK"}        
             200 OK - удачная операция  
             401 Unauthorized - Пользователя с таким ключем нет в сессии  
             400 Bad Request - запрос не понят 
               
#### Изменение пароля пользователя  
>* /changepass
>    * Request: 
            {  
                {"login":"login1", "password":"prevpass","newpassword":"passnew"},  
            }  
>    * Responce:
            { "status": "200 OK"}        
             200 OK - удачная операция  
             403 Forbidden - предыдущий пароль не совпадает  
             400 Bad Request - запрос не понят  
             401 Unauthorized - Пользователя с таким ключем нет в сессии  

             
#### Выход пользователя  
>* /logout
>     * Request: 

>     * Responce:
             { "status": "200 OK"}        
              200 OK - удачная операция  
              400 Bad Request - запрос не понят



##<https://tp-server-java.herokuapp.com>

test