-etape 1 
--- télécharger et installer le sgbd mysql
-etape 2
--- télécharger wildfly et a jouter un serveur à eclipse
-etape 3 
--- creer la base données: le script est fournit dans l'annexe du rapport
-etape 4
---ajouter le datasource du projet à wildfly :
------ajouter le driver de mysql à wildfly en suivant les instructions sur le lien suivant http://giordanomaestro.blogspot.com/2015/02/install-jdbc-driver-on-wildfly.html 
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
--- importer gestion_bibliotheque_server_side dans eclipse et le déployer sur wildfly
--- importer gestion_bibliotheque_client_V1 dans eclipse et le déployer sur wildfly


-----------------------
dans l'utilisation, une adresse sous format String a ce format : code zip , rue, ville, pays // le code zip doit être un entier

une date a le format yyyy-mm-dd
