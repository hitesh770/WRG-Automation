$username=$CmdLine[1]
$password=$CmdLine[2]
Sleep(2000)
WinWaitActive("","Chrome Legacy Window",60)
Send($username)
Send("{TAB}")
Send($password)
Send("{ENTER}")