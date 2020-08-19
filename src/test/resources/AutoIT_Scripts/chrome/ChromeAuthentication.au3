$username=$CmdLine[1]
$password=$CmdLine[2]
WinWaitActive("https://testsso.wrg-ins.com/","",60)
WinActivate("https://testsso.wrg-ins.com/")
Send($username)
Send("{TAB}")
Send($password)
Send("{ENTER}")