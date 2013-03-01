namespace java com.dianziq.test

struct s1
{
        1:bool filed1
        2:byte filed2
        3:i16  filed3	
        4:i32  filed4
        5:i64  filed5	
        6:double  filed6	
        7:string  filed7	
}

struct s2
{
        1:list<s1> s1List
        2:map<string,string> filedMap
        3:set<string> filedSet
}

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

