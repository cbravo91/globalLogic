Manual de Uso de la API

Para registrar a un usuario deberán consumir el siguiente endpoint.
El nombre es opcional, la contraseña deberá tener una longitud entre 8 y 12 caracteres, una sola mayuscula, dos numeros y solo caracteres alfanuméricos.
El email, deberá tener el nombre de su correo, el @ y el dominio seguido de .com o la terminación que corresponda al mismo.
Endpoint : localhost:9292/sign-up

curl de ejemplo : 

curl --location 'localhost:9292/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "pal123",
"email": "asdfasgh@papa.com",
"password": "2Af7sdfghh",
"phones": [
{
"number": 1234564,
"citycode": 54,
"contrycode": "1023"
},
{
"number": 85466,
"citycode": 98,
"contrycode": "234"
}
]
}'

El mismo devolverá la siguiente respuesta en caso de que se haya registrado correctamente, con el código de respuesta HTTP 201:

{
"id": "0bb28c2b-cf67-4482-93eb-fec54fb6f2db",
"created": "Jan 06, 2025 10:13:23 AM",
"lastLogin": null,
"token": "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjBiYjI4YzJiLWNmNjctNDQ4Mi05M2ViLWZlYzU0ZmI2ZjJkYiIsIm5hbWUiOiJwYWwxMjMiLCJwYXNzd29yZCI6IjJBZjdzZGZnaGgiLCJlbWFpbCI6ImFzZGZhc2doQHBhcGEuY29tIiwiY3JlYXRlZCI6Ik1vbiBKYW4gMDYgMTA6MTM6MjMgQVJUIDIwMjUiLCJsYXN0TG9naW4iOiJNb24gSmFuIDA2IDEwOjEzOjIzIEFSVCAyMDI1IiwiYWN0aXZlIjp0cnVlLCJwaG9uZXMiOlt7Im51bWJlciI6MTIzNDU2NCwiY2l0eWNvZGUiOjU0LCJjb250cnljb2RlIjoiMTAyMyJ9LHsibnVtYmVyIjo4NTQ2NiwiY2l0eWNvZGUiOjk4LCJjb250cnljb2RlIjoiMjM0In1dfQ.UosV85CttFTY0DHosUB2na1lRelsRjX-eSDJrqu-7u4",
"active": true
}

Ese usuario quedará registrado en la base de datos h2 en la tabla de usuarios (user) y la tabla de teléfonos (phone)

Endpoint de login

Para registrarse será necesario usar el token por lo tanto debemos copiar el token obtenido en el endpoint sign-up y copiarlo en el header del mismo sin Bearer sin  el token:, solo el contenido entre las comillas.

curl de ejemplo 

curl --location --request POST 'localhost:9292/login' \
--header 'token: eyJhbGciOiJIUzI1NiJ9.eyJpZCI6ImUwMzY0YWU3LTRjZjktNDhlNC1iMGNhLTFjOTkyNWUyMmRmOCIsIm5hbWUiOiJwYWwxMjMiLCJwYXNzd29yZCI6IjJBZjdzZGZnaGgiLCJlbWFpbCI6ImFzZGZhc2doQHBhcGEuY29tIiwiY3JlYXRlZCI6IkZyaSBKYW4gMDMgMjE6NDE6MTMgQVJUIDIwMjUiLCJsYXN0TG9naW4iOiJGcmkgSmFuIDAzIDIxOjQxOjEzIEFSVCAyMDI1IiwiYWN0aXZlIjp0cnVlLCJwaG9uZXMiOlt7Im51bWJlciI6MTIzNDU2NCwiY2l0eWNvZGUiOjU0LCJjb250cnljb2RlIjoiMTAyMysibnVtYmVyIjo4NTQ2NiwiY2l0eWNvZGUiOjk4LCJjb250cnljb2RlIjoiMjM0In1dfQ.Ggyl4yWVH7fgPrkpY9tEH57NNWW11l9XgtxLTStfxBQ' \
--data ''

La respuesta devolverá el siguiente JSON con los datos del usuario y su token correspondiente, con el código http 200

{
"id": "0bb28c2b-cf67-4482-93eb-fec54fb6f2db",
"created": "Mon Jan 06 10:13:23 ART 2025",
"lastLogin": "Jan 06, 2025 10:26:16 AM",
"token": "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjBiYjI4YzJiLWNmNjctNDQ4Mi05M2ViLWZlYzU0ZmI2ZjJkYiIsIm5hbWUiOiJwYWwxMjMiLCJwYXNzd29yZCI6IjJBZjdzZGZnaGgiLCJlbWFpbCI6ImFzZGZhc2doQHBhcGEuY29tIiwiY3JlYXRlZCI6Ik1vbiBKYW4gMDYgMTA6MTM6MjMgQVJUIDIwMjUiLCJsYXN0TG9naW4iOiJNb24gSmFuIDA2IDEwOjEzOjIzIEFSVCAyMDI1IiwiYWN0aXZlIjp0cnVlLCJwaG9uZXMiOlt7Im51bWJlciI6MTIzNDU2NCwiY2l0eWNvZGUiOjU0LCJjb250cnljb2RlIjoiMTAyMyJ9LHsibnVtYmVyIjo4NTQ2NiwiY2l0eWNvZGUiOjk4LCJjb250cnljb2RlIjoiMjM0In1dfQ.UosV85CttFTY0DHosUB2na1lRelsRjX-eSDJrqu-7u4",
"name": "pal123",
"email": "asdfasgh@papa.com",
"password": "2Af7sdfghh",
"phones": [
{
"number": 1234564,
"citycode": 54,
"contrycode": "1023"
},
{
"number": 85466,
"citycode": 98,
"contrycode": "234"
}
],
"active": true
}


En caso de error devolverá esta respuesta con el código HTTP 412
{
"error": [
{
"timestamp": "2025-01-06T13:24:50.341+00:00",
"codigo": 412,
"detail": "Unnable to read Token"
}
]
}

