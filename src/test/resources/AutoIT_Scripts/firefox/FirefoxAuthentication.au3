$username=$CmdLine[1]
$password=$CmdLine[2]
WinWaitActive("Authentication Required")
Send($username)
Send("{TAB}")
Send($password)
Send("{ENTER}")