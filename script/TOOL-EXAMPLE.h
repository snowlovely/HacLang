#include "tool"

payload = tool::getBashCommand("calc.exe");
print(payload);

payload = tool::getStringCommand("calc.exe");
print(payload);

payload = tool::getPowershellCommand("calc.exe");
print(payload);

tool::exec(payload);
