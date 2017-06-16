Afin de lancer l'application, il vous faut :
1 - un environnement Eclipse muni de JavaFX et des librairies Sarl

Etape à suivre : 
1 - Importer le projets Boids-Application dans Eclipse
2 - Importer le projets boids-projet_sarl dans Eclipse
3 - Dans le build-path du projet Boids-Application, ajouter le projet boids-projet_sarl
4 - Dans le build-path du projet boids-projet_sarl, ajouter le projet Boids-Application

5 - Si des problèmes de librairie persiste, ajouter au projet boids-projet_sarl les jars suivant :
com.google.guava_18.0.0.jar
io.sarl.core_0.4.3.jar
io.sarl.core_0.4.3.jar
io.sarl.lang.core_0.4.3.jar
io.sarl.util_0.4.3.jar
javax.inject_1.0.0.v20091030.jar
org.eclipse.osgi.compatibility.state_1.0.200.v20160504-1419.jar
org.eclipse.osgi_3.11.1.v20160708-1632.jar
org.eclipse.xtext.xbase.lib_2.10.0.v201605250459.jar

et dans le projet Boids-Application les jars présent dans un projet Janus.
