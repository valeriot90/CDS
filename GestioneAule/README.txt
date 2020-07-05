*****************************
*							*
*	GlassfishServer:		*
*							*
*****************************

Tab service (a sinistra) -> glassfish -> add -> local -> "./gestioneAule/glassfish5" come path locale

*********************************
*								*
*	MYSQL-CONNECTOR-JAVA:		*
*								*
*********************************
VERSIONE 5.1.20-bin

Deve essere posizionato in:
./gestioneAule/web/WEB-INF/lib/mysql-connector-java-5.1.20-bin.jar

Insert a library "mysql-connector-java-5.1.34-bin.jar" into a server folder (in this case Glassfish):
/user/Glassfish_server/glassfish/domains/domain1/lib

*****************************************************
*													*
*	In caso di ulteriori problemi, da netbeans:		*
*													*
*****************************************************
tasto destro sul progetto -> risolvi problemi (penultimo in basso)
e modificare i path in maniera opportuna

*************************************************************************
*																		*
*	PATH:																*
*	./gestioneAule/web/WEB-INF/lib/mysql-connector-java-5.1.20-bin.jar  *
*	./gestioneAule/web/WEB-INF/lib/mysql-connector-java-5.1.20/			*
*	./gestioneAule/glassfish5/		(da mettere)						*
*																		*
*************************************************************************

DB password: root