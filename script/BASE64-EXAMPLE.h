#include "base64"

data = "4ra1n";
enc = base64::encode(data);
print(enc);

dec = base64::decode(enc);
printBytes(dec);









