-etape 1 
--- t�l�charger et installer le sgbd mysql
-etape 2
--- t�l�charger wildfly et a jouter un serveur � eclipse
-etape 3 
--- creer la base donn�es: le script est fournit dans l'annexe du rapport
-etape 4
---ajouter le datasource du projet � wildfly :
------ajouter le driver de mysql � wildfly en suivant les instructions sur le lien suivant http://giordanomaestro.blogspot.com/2015/02/install-jdbc-driver-on-wildfly.html 
------ajouter les lugnes suivante dans le standalone.xml 
				<datasource jndi-name="java:jboss/datasources/nomdatasource" pool-name="projetJEE" enabled="true" use-java-context="true">
                    <connection-url>jdbc:mysql://localhost:3306/nomDatabase?useSSL=false</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>mot de passe mysql</password>
                    </security>
                    <statement>
                        <prepared-statement-cache-size>100</prepared-statement-cache-size>
                        <share-prepared-statements>true</share-prepared-statements>
                    </statement>
                </datasource>
				
-etape 5
--- importer gestion_bibliotheque_server_side dans eclipse et le d�ployer sur wildfly
--- importer gestion_bibliotheque_client_V1 dans eclipse et le d�ployer sur wildfly


-----------------------
dans l'utilisation, une adresse sous format String a ce format : code zip , rue, ville, pays // le code zip doit �tre un entier

une date a le format yyyy-mm-dd
