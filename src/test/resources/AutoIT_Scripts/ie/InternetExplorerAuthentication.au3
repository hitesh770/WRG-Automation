$username=$CmdLine[1]
$password=$CmdLine[2]
sleep(2000)
WinWaitActive("Windows Security","",60)
Send($username)
Send("{TAB}")
sleep(500)
Send($password)
sleep(500)
Send("{ENTER}")