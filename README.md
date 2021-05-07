DATA1600 Programuvtikling - semesteroppgave
Ferdigopprettet bruker med brukernavn og passord.

Vi har utviklet et registreringssystem for gårsdsprodukter. 
I dette programmet kan man registrere produkter på to nivåer. Enten som kategori eller som et spesifikt produkt under denne kategorien.
Programmet er laget slik at det skal være enkelt for brukeren å navigere seg fram i det grafiske brukergrensesnittet, registrere ønskede produkter.

Når man går inn i programmet ser man først en meny for å logge inn, men vi har også lagt til funkjsonalitet for å logge inn uten brukernavn 
og passord. For å registrere en ny kategori kan man skrive inne hva man vil denn kategorien skal hete. Programmet vil håndtere duplikat data,
og det vil ikke bli dobbelt opp med en kategori.

For å registrere et nytt spesifikt produkt skriver man inn navn og antall, mens egenskaper er frivillig. Dette gjør at man kan ha god oversikt
over gårdens produkter. Det er ikke mulig å registrere mindre enn én av hvert produkt. Det er heller ikke mulig å registrere et navn som ikke
passer med det regulere uttrykket. Vi har dog valgt et mindre strengt regex-uttrykket fordi det fins så mange ulike kategorier.

Brukeren får listet påå registrerte elementer lest fra fil og dette kan redigeres på.