# IPSubnetCalculator
IP Subnet Calculator è un’app che permette di calcolare alcuni parametri per la progettazione di reti.

Partendo da un indirizzo IP e una maschera di rete inseriti dall’utente, calcola:
- l’indirizzo di rete di appartenenza;
- l’indirizzo IP in binario;
- la maschera di rete in decimali separati da punti (255.255.255.0);
- l’indirizzo di broadcast;
- il primo e l’ultimo indirizzo disponibile gli host;
- il numero di indirizzi disponibili;
- la wildcard mask.

Viene richiesto l’inserimento di IP e Subnet Mask nel formato a.b.c.d/x (CIDR) dove x è la subnet mask e a, b, c, d sono i quattro byte che compongono l’indirizzo IP.
