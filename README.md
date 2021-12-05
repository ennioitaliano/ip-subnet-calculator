**Disclaimer**
- I wrote this code in 2019 for a school project, and this was my very first experience with mobile apps developement.
  - I did not know anything about design patterns, algorithms, data structures or good practices in this field.
- The code and the text you can find in the Wiki were originally written in Italian (my main language). I translated the wiki quickly and without so much attention, so it could be not so clear.
- The app was originally published to the Play Store with some ads, privacy policy and other complications; I removed them all to publish it here in an open source form.
- I'd like to rewrite the app from scratch, but maybe using Flutter (which I'm learning right now) just for fun and to implement better some algorithms and structure, although this remains a very simple project.
***
# IP Subnet Calculator
**IP Subnet Calculator** is an Android application allowing users to calculate some parameters useful for the design of communication networks.

Users have to insert an IP address and a net mask, and the application will calculate:
- the "parent" net address;
- the IP address in binary format;
- the net mask in decimal format (e.g. 255.255.255.0);
- the broadcast address;
- the first and the last address available for hosts;
- the number of available addresses;
- the wildcard mask.

The IP address and the net mask that users have to insert must be in a `a.b.c.d/x` format ([CIDR](https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing#CIDR_notation)), where `x` is the subnet mask and `a`, `b`, `c`, `d` are the four bytes that make up the IP address.
