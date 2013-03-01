namespace java com.dianziq.test

struct LoginResponse
{
	1:string code
	2:string msg
	3:string token
}

service UserService
{
	LoginResponse login(1:string username,2:string password)
}

