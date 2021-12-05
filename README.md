# IP Subnet Calculator
**IP Subnet Calculator** is an Android application allowing the users to calculate some parameters useful for the design of communication networks.

Users have to insert an IP address and a net mask, and the application will calculate:
- the "parent" net address;
- the IP address in binary format;
- the net mask in decimal format (e.g. 255.255.255.0);
- the broadcast address;
- the first and the last address available for hosts;
- the number of available addresses;
- the wildcard mask.

The IP address and the net mask that users have to insert must be in a `a.b.c.d/x` format ([CIDR](https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing#CIDR_notation)), where `x` is the subnet mask and `a`, `b`, `c`, `d` are the four bytes that make up the IP address.
